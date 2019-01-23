package com.ripstech.api.utils;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class MinimalLogging {

	protected Consumer<String> logger;

	public MinimalLogging setLogger(@NotNull Consumer<String> logger) {
		this.logger = logger;
		return this;
	}

	protected void log(String message) {
		if(logger != null) {
			logger.accept(message);
		}
	}

	protected void log(String format, Object... obj) {
		if(logger != null) {
			logger.accept(String.format(format, obj));
		}
	}

}
