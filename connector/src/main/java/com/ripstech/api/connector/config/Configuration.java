package com.ripstech.api.connector.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class Configuration {

	private Configuration() {}

	public static boolean strict = false;
	public static int pageSize = 500;

	private static ObjectMapper getObjectMapperOAuth() {
		ObjectMapper om = new ObjectMapper()
				       .registerModule(new JavaTimeModule().addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer()))
				       //.registerModule(new JavaTimeModule())
				       .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		if(strict) {
			om.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		} else {
			om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}
		return om;
	}

	private static ObjectMapper getObjectMapper() {
		return getObjectMapperOAuth().copy()
				       .enable(SerializationFeature.WRAP_ROOT_VALUE);
	}

	private static ObjectMapper getObjectMapperPatch() {
		return getObjectMapper().copy()
				       .registerModule(new Jdk8Module().configureAbsentsAsNulls(false))
				       .setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	private static ObjectMapper getObjectMapperPost() {
		return getObjectMapper().copy()
				       .registerModule(new Jdk8Module().configureAbsentsAsNulls(false))
				       .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
	}

	private static ObjectMapper getObjectMapperPostWithoutRootName() {
		return getObjectMapperPost().copy()
				       .disable(SerializationFeature.WRAP_ROOT_VALUE);
	}

	private static ObjectMapper getObjectMapperPatchWithoutRootName() {
		return getObjectMapperPatch().copy()
				       .disable(SerializationFeature.WRAP_ROOT_VALUE);
	}

	public static ObjectMapper getObjectMapper(MAPPER_CONFIG configType) {
		switch (configType) {
			case OAUTH2:
				return getObjectMapperOAuth();
			case DEFAULT:
				return getObjectMapper();
			case PATCH:
				return getObjectMapperPatch();
			case POST:
				return getObjectMapperPost();
			case POST_WITHOUT_ROOT_NAME:
				return getObjectMapperPostWithoutRootName();
			case PATCH_WITHOUT_ROOT_NAME:
				return getObjectMapperPatchWithoutRootName();
		}
		throw new InvalidParameterException();
	}

	static class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

		private static final DateTimeFormatter DTF = new DateTimeFormatterBuilder().parseCaseInsensitive()
				                                             .append(ISO_LOCAL_DATE_TIME)
				                                             .appendOffset("+HH:MM", "+00:00").toFormatter();

		@Override
		public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString(value.truncatedTo(ChronoUnit.SECONDS).format(DTF));
		}

	}

	public enum MAPPER_CONFIG {
		OAUTH2,
		DEFAULT,
		PATCH,
		POST,
		POST_WITHOUT_ROOT_NAME,
		PATCH_WITHOUT_ROOT_NAME,
	}

}