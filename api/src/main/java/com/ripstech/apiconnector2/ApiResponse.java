package com.ripstech.apiconnector2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ripstech.apiconnector2.entity.receive.ErrorMessage;
import okhttp3.Response;

import java.io.*;
import java.util.Optional;
import java.util.function.*;

import static com.ripstech.apiconnector2.ApiRequest.MEDIA_TYPE_JSON;
import static java.net.HttpURLConnection.*;

public class ApiResponse<T> {

	private T value;
	private HttpStatus status;
	private String message;

	public ApiResponse(ApiRequest request, Class<T> clazz) {
		try {
			Response response = request.execute();
			status = new HttpStatus(response.code(), response.message());
			if (!clazz.equals(Void.class)
			    && response.body() != null
			    && response.body().contentLength() != 0
			    && response.body().contentType() != null
			    && response.body().contentType().subtype() != null
			    && response.body().contentType().subtype().equals(MEDIA_TYPE_JSON.subtype())) {
				if (isOk()) {
					if(response.body().contentType().subtype().equals(MEDIA_TYPE_JSON.subtype())) {
						value = responseToObject(request, response, clazz);
					} else if(clazz.equals(InputStream.class)) {
						value = (T) clone(response.body().byteStream());
					}
				} else {
					ErrorMessage errorMessage = responseToObject(request, response, ErrorMessage.class);
					message = errorMessage.getMessageAndErrors();
				}
			}
			response.close();
		} catch (IOException e) {
			setException(e);
		}
	}

	public ApiResponse(ApiRequest request, TypeReference<T> genericType) {
		try {
			Response response = request.execute();
			status = new HttpStatus(response.code(), response.message());
			if (!genericType.getType().equals(Void.class)
			    && response.body() != null
			    && response.body().contentLength() != 0
			    && response.body().contentType() != null
			    && response.body().contentType().subtype() != null
			    && response.body().contentType().subtype().equals(MEDIA_TYPE_JSON.subtype())) {
				if (isOk()) {
					value = responseToObject(request, response, genericType);
				} else {
					ErrorMessage errorMessage = responseToObject(request, response, ErrorMessage.class);
					message = errorMessage.getMessageAndErrors();
				}
			}
			response.close();
		} catch (IOException e) {
			setException(e);
		}
	}

	private <K> K responseToObject(ApiRequest request, Response response, TypeReference<K> genericType) throws IOException {
		assert response.body() != null;
		return request.getObjectMapper()
				       .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
				       .readValue(response.body().byteStream(), genericType);
	}

	private <K> K responseToObject(ApiRequest request, Response response, Class<K> clazz) throws IOException {
		assert response.body() != null;
		return request.getObjectMapper()
				       .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
				       .readValue(response.body().byteStream(), clazz);
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

	public ApiResponse(int status, String message) {
		this.status = new HttpStatus(status, "");
		this.message = message;
	}

	public ApiResponse(Exception e) {
		setException(e);
	}

	private void setException(Exception e) {
		status = new HttpStatus(-1, "Exception");
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		this.message = String.format("%s\n%s", e.getMessage(), sw.toString());
	}

	public int getStatus() {
		return status.getCode();
	}

	public HttpStatus getHttpStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public boolean isOk() {
		return status.getCode() == HTTP_OK;
	}


	public boolean isNotOk() {
		return !isOk();
	}

	public boolean isForbidden() {
		return status.getCode() == HTTP_FORBIDDEN;
	}

	public boolean isNotFound() {
		return status.getCode() == HTTP_NOT_FOUND;
	}

	public void ifOk(Consumer<T> consumer) {
		if(isOk()) {
			consumer.accept(value);
		}
	}

	public void ifNotOk(BiConsumer<HttpStatus, String> consumer) {
		if(!isOk()) {
			consumer.accept(status, message);
		}
	}

	public Optional<T> get() {
		return Optional.ofNullable(value);
	}

	public T or(T orValue) {
		return isOk() ? value : orValue;
	}

	public T orNull() {
		return or(null);
	}

	public <X extends Throwable> T orThrow(Supplier<? extends X> throwable) throws X {
		if(isOk()) {
			return value;
		} else {
			throw throwable.get();
		}
	}

	public <X extends Throwable> T orThrow(BiFunction<HttpStatus, String, ? extends X> function) throws X {
		if(isOk()) {
			return value;
		} else {
			throw function.apply(status, message);
		}
	}

	public void process(Consumer<T> successConsumer, BiConsumer<HttpStatus, String> errorConsumer) {
		ifOk(successConsumer);
		ifNotOk(errorConsumer);
	}

	public <R> Optional<R> map(Function<? super T, ? extends R> mapper) {
		if(isNotOk()) {
			return Optional.empty();
		}
		return Optional.of(mapper.apply(value));
	}

}