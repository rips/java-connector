package com.ripstech.api.utils.validation;

import com.ripstech.api.entity.receive.Status;
import com.ripstech.api.utils.ApiUtils;
import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.queryparameter.Filter;
import org.apache.commons.validator.routines.UrlValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EndpointValidator {

	public static boolean compatibleWithApiVersion(@NotNull Api api, @NotNull ApiVersion supportedVersion) throws ApiException {
		ApiVersion apiVersion = ApiVersion.parse(api.status()
		                                            .get(new Filter().select("version"))
		                                            .orThrow(ApiException::new)
		                                            .getVersion());
		return apiVersion.compareTo(supportedVersion) >= 0;
	}

	@Nullable
	public static String api(@NotNull String url) throws MalformedURLException, ApiException {
		return new Api.Builder(new URL(url).toString())
				       .build()
				       .status()
				       .get(new Filter().select("version"))
				       .map(Status::getVersion)
				       .orElse(null);
	}

	public static boolean apiLogin(@NotNull String url,
	                               @NotNull String email,
	                               @NotNull String password) throws MalformedURLException, ApiException {
		return ApiUtils.getApi(url, email, password)
				       .status()
				       .get(new Filter().select("user"))
				       .orThrow(ApiException::new)
				       .getUser()
				       .getEmail()
				       .equalsIgnoreCase(email);
	}

	@Contract("null -> false")
	public static boolean url(@Nullable String url) {
		if (url == null) {
			return false;
		}
		String[] schemes = {"https", "http"};
		UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_LOCAL_URLS);
		return urlValidator.isValid(url);
	}

	@Nullable
	public static String ui(@NotNull String uiUrl) throws IOException {
		URL url = new URL(uiUrl + "/assets/version.json");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("charset", "utf-8");
		connection.connect();
		return new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))
				.lines()
				.filter(s -> s.contains("version"))
				.findFirst()
				.flatMap(s -> Arrays.stream(s.split(":")).skip(1).findFirst())
				.map(s -> s.replaceAll("\"", "").trim())
				.orElse(null);
	}

}
