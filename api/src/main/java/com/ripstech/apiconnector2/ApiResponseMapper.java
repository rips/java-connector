package com.ripstech.apiconnector2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ripstech.apiconnector2.entity.receive.ErrorMessage;
import com.ripstech.apiconnector2.exception.ErrorMessageException;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.ripstech.apiconnector2.ApiRequest.MEDIA_TYPE_JSON;

public class ApiResponseMapper<T> {

	private final TypeReference<T> typeReference;
	private final ObjectMapper objectMapper;

	public ApiResponseMapper(final @NotNull TypeReference<T> typeReference, final @NotNull ObjectMapper objectMapper) {
		this.typeReference = typeReference;
		this.objectMapper = objectMapper;
	}

	@Nullable
	public T getValue(final @NotNull Response response) throws ErrorMessageException, IOException {
		if (!typeReference.getType().equals(Void.class)
		    && response.body() != null
		    && response.body().contentLength() != 0
		    && response.body().contentType() != null
		    && response.body().contentType().subtype() != null
		    && response.body().contentType().subtype().equals(MEDIA_TYPE_JSON.subtype())) {
			if (response.isSuccessful()) {
				if(response.body().contentType().subtype().equals(MEDIA_TYPE_JSON.subtype())) {
					return responseToObject(response, typeReference);
				} else if(typeReference.getType().equals(InputStream.class)) {
					return  (T) clone(response.body().byteStream());
				}
			} else {
				throw new ErrorMessageException(responseToObject(response, new TypeReference<ErrorMessage>() {}));
			}
		}
		return null;
	}

	@Nullable
	private <K> K responseToObject(@NotNull final Response response,
	                               @NotNull final TypeReference<K> typeReference) throws IOException {
		if(response.body() == null) {
			return null;
		}
		return objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
				       .readValue(response.body().byteStream(), typeReference);
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
