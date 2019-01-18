package com.ripstech.api.utils;

import com.ripstech.api.entity.receive.application.scan.issue.Type;
import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class ApiUtils {

    // util class, needs no constructor
    private ApiUtils() {
        // if the constructor is somehow called, throw exception
        throw new UnsupportedOperationException();
    }

	public static Api getApi(String url, String email, String password) throws MalformedURLException, ApiException {
		return new Api.Builder(new URL(url).toString())
				.withXPassword(email, password)
				.build();
		//TODO validation
		//TODO fallback
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
