package com.ripstech.api.utils;

import com.ripstech.api.entity.receive.application.scan.issue.Type;
import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.queryparameter.Filter;
import com.ripstech.api.utils.exception.InvalidApiCredentialsException;
import com.ripstech.api.utils.validation.ApiVersion;
import com.ripstech.api.utils.validation.EndpointValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class ApiUtils {

    // util class, needs no constructor
    private ApiUtils() {
        // if the constructor is somehow called, throw exception
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @NotNull
    public static Api getApiWithFallback(@NotNull String url,
                                         @NotNull String email,
                                         @NotNull String password) throws MalformedURLException, ApiException {
        return getApiWithFallback(url, email, password, builder -> { });
    }

    @Deprecated
    @NotNull
    public static Api getApiWithFallback(@NotNull String url,
                                         @NotNull String email,
                                         @NotNull String password,
                                         @Nullable Consumer<Api.Builder> apiConfig
                                        ) throws MalformedURLException, ApiException {
        Api api;
        try {
            Api.Builder builder = new Api.Builder(new URL(url).toString())
                    .withOAuthv2(email, password);
            if(apiConfig != null) {
                apiConfig.accept(builder);
            }
            return builder.build();
        } catch (ApiException e) {
            return getApiXPassword(url, email, password, apiConfig);
        }
	}

	@NotNull
    public static Api getApiWithFallback(@NotNull String url,
                                         @NotNull String email,
                                         @NotNull String password,
                                         @NotNull ApiVersion requiredVersion
                                        ) throws MalformedURLException, ApiException {
        return getApiWithFallback(url, email, password, requiredVersion,null);
    }

    @NotNull
    public static Api getApiWithFallback(@NotNull String url,
                                         @NotNull String email,
                                         @NotNull String password,
                                         @NotNull ApiVersion requiredVersion,
                                         @Nullable Consumer<Api.Builder> apiConfig
                                        ) throws MalformedURLException, ApiException {
        EndpointValidator.api(url, requiredVersion);
        return getApiWithFallback(url, email, password, apiConfig);
    }

    public static Api getApiXPassword(@NotNull String url,
                                      @NotNull String email,
                                      @NotNull String password
                                     ) throws MalformedURLException, ApiException {
        return getApiXPassword(url, email, password, null);
    }

    public static Api getApiXPassword(@NotNull String url,
                                      @NotNull String email,
                                      @NotNull String password,
                                      @Nullable Consumer<Api.Builder> apiConfig
                                     ) throws MalformedURLException, ApiException {
        if(!EndpointValidator.apiLogin(url, email, password)) {
            throw new InvalidApiCredentialsException();
        }
        Api.Builder builder = new Api.Builder(new URL(url).toString())
                .withXPassword(email, password);
        if(apiConfig != null) {
            apiConfig.accept(builder);
        }
        return builder.build();
    }

    @NotNull
    public static Map<Long, Integer> getIssueTypeSeverities(@NotNull final Api api) throws ApiException {
        return api.applications()
                       .scans()
                       .issues()
                       .types()
                       .get(new Filter().select("id", "severity"))
                       .orThrow(ApiException::new)
                       .stream()
                       .map(types -> new AbstractMap.SimpleImmutableEntry<>(
                               types.getId(),
                               types.getSeverity()))
                       .collect(Collectors.toMap(
                               AbstractMap.SimpleImmutableEntry::getKey,
                               AbstractMap.SimpleImmutableEntry::getValue));
    }

    public static Map<Long, Type> getIssueTypeSeverities(@NotNull final Api api, String... selects) throws ApiException {
        final Set<String> sendSelect = new HashSet<>(Arrays.asList(selects));
        sendSelect.add("id");
        return api.applications()
                       .scans()
                       .issues()
                       .types()
                       .get(new Filter().select(sendSelect))
                       .orThrow(ApiException::new)
                       .stream()
                       .map(types -> new AbstractMap.SimpleImmutableEntry<>(
                               types.getId(),
                               types))
                       .collect(Collectors.toMap(
                               AbstractMap.SimpleImmutableEntry::getKey,
                               AbstractMap.SimpleImmutableEntry::getValue));
    }

    @NotNull
    public static Map<Long, String> getIssueTypeNames(@NotNull final Api api) throws ApiException {
        return api.applications()
                       .scans()
                       .issues()
                       .types()
                       .get(new Filter().select("id", "name"))
                       .orThrow(ApiException::new)
                       .stream()
                       .map(types -> new AbstractMap.SimpleImmutableEntry<>(
                               types.getId(),
                               types.getName()))
                       .collect(Collectors.toMap(
                               AbstractMap.SimpleImmutableEntry::getKey,
                               AbstractMap.SimpleImmutableEntry::getValue));
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }


}
