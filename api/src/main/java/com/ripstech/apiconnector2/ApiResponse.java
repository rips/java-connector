package com.ripstech.apiconnector2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.exception.ErrorMessageException;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.function.*;

import static java.net.HttpURLConnection.*;

public class ApiResponse<T> {

	private T value;
	private HttpStatus status;
	private String message;

	private ApiRequest request;
	private ApiResponseMapper<T> apiResponseMapper;

	public ApiResponse(final @NotNull ApiRequest request, final @NotNull Class<T> clazz) {
		this.request = request;
		final TypeReference<T> typeReference = new TypeReference<T>() {
			@Override
			public Type getType() {
				return clazz;
			}
		};
		this.apiResponseMapper = new ApiResponseMapper<>(typeReference, request.getObjectMapper());
		exec();
	}

	public ApiResponse(final @NotNull ApiRequest request, final @NotNull TypeReference<T> typeReference) {
		this.request = request;
		this.apiResponseMapper = new ApiResponseMapper<>(typeReference, request.getObjectMapper());
		exec();
	}

	public ApiResponse(int status, String message) {
		this.status = new HttpStatus(status, "");
		this.message = message;
	}

	public ApiResponse(Exception e) {
		setException(e);
	}

	@SuppressWarnings("unchecked")
	private void exec() {
		try (Response response = request.execute()) {
			status = new HttpStatus(response.code(), response.message());
			value = apiResponseMapper.getValue(response);
			if (value instanceof Collection<?> && request.usePagination()) {
				boolean nextPage = "1".equals(response.header("X-API-Pagination-More", "0"));
				while (nextPage) {
					try (Response nextResponse = request.nextPage().execute()) {
						status = new HttpStatus(nextResponse.code(), nextResponse.message());
						nextPage = "1".equals(nextResponse.header("X-API-Pagination-More", "0"));
						final Collection nextItems = (Collection) apiResponseMapper.getValue(nextResponse);
						if(nextItems != null) {
							((Collection<?>) value).addAll(nextItems);
						}
					}
				}
			}
		} catch (ApiException | IOException e) {
			setException(e);
		} catch (ErrorMessageException e) {
			this.message = e.getErrorMessage().getMessageAndErrors();
		}
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
		return status.getCode() >= HTTP_OK && status.getCode() < HTTP_MULT_CHOICE;
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
		if(isNotOk() || this.value == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(mapper.apply(value));
	}

}