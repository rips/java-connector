package com.ripstech.api.connector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ripstech.api.connector.entity.receive.ErrorMessage;
import com.ripstech.api.connector.exception.ErrorMessageException;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.ripstech.api.connector.ApiRequest.MEDIA_TYPE_JSON;
import static com.ripstech.api.connector.HttpStatus.NON_HTTP_ERROR;

public class ApiResponseMapper<T> {

	private final TypeReference<T> typeReference;
	private final ObjectMapper objectMapper;

	public ApiResponseMapper(final @NotNull TypeReference<T> typeReference, final @NotNull ObjectMapper objectMapper) {
		this.typeReference = typeReference;
		this.objectMapper = objectMapper;
	}

	@Nullable
	public T getValue(final @NotNull Response response) throws ErrorMessageException, IOException {
		if(!response.isSuccessful()) {
			throw new ErrorMessageException(responseToObject(response, new TypeReference<ErrorMessage>() {}, true));
		}

		if(typeReference.getType().equals(Void.class)) {
			return null;
		} else if (typeReference.getType().equals(InputStream.class)) {
			return (T) clone(response.body().byteStream());
		}

		return responseToObject(response, typeReference, false);
	}

	@Nullable
	private <K> K responseToObject(@NotNull final Response response,
	                               @NotNull final TypeReference<K> typeReference,
	                               boolean errorHandling) throws IOException, ErrorMessageException {
		ResponseBody body = response.body();
		if(body == null) {
			return null;
		}

		if(body.contentLength() != 0) {
			if(body.contentType() == null || body.contentType().subtype() == null) {
				throw new ErrorMessageException(NON_HTTP_ERROR, "Unknown media type to display more information");
			}
			String subtype = body.contentType().subtype();
			if(subtype.equals(MEDIA_TYPE_JSON.subtype())) {
				return objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
				                   .readValue(body.byteStream(), typeReference);
			} else if(errorHandling && "text".equals(body.contentType().type())) {
				throw new ErrorMessageException(response.code(), "");
			}
		}
		return null;
	}

	private InputStream clone(InputStream stream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int readLength = 0;
		while ((readLength = stream.read(buffer)) != -1) {
			baos.write(buffer, 0, readLength);
		}
		baos.flush();
		return new ByteArrayInputStream(baos.toByteArray());
	}

}
