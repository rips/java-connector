package com.ripstech.api.connector.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationTest {

	OptionalEntity entityWithNull;
	OptionalEntity entityWithValue;

	@BeforeEach
	void setUp() {
		entityWithNull = new OptionalEntity().setValue(null);
		entityWithValue = new OptionalEntity().setValue("abc");
	}

	@Test
	void getObjectMapperPatch() throws JsonProcessingException {
		ObjectMapper mapper = Configuration.getObjectMapper(Configuration.MAPPER_CONFIG.PATCH);
		assertEquals(
				"{\"OptionalEntity\":{\"value\":null}}",
				mapper.writeValueAsString(entityWithNull)
		            );
		assertEquals(
				"{\"OptionalEntity\":{\"value\":\"abc\"}}",
				mapper.writeValueAsString(entityWithValue)
		            );
	}

	@Test
	void getObjectMapperPost() throws JsonProcessingException {
		ObjectMapper mapper = Configuration.getObjectMapper(Configuration.MAPPER_CONFIG.POST);
		assertEquals(
				"{\"OptionalEntity\":{}}",
				mapper.writeValueAsString(entityWithNull)
		            );
		assertEquals(
				"{\"OptionalEntity\":{\"value\":\"abc\"}}",
				mapper.writeValueAsString(entityWithValue)
		            );
	}

	public static class OptionalEntity {
		Optional<String> value;

		public Optional<String> getValue() {
			return value;
		}

		public OptionalEntity setValue(String value) {
			this.value = Optional.ofNullable(value);
			return this;
		}
	}

}