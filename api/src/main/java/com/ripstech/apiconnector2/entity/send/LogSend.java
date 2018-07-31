package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "log")
public class LogSend {

	private String text;
	private String context;
	private Integer level;

	public static LogSend createPost(String text, Integer level) {
		return new LogSend().setText(text).setLevel(level);
	}

	private LogSend() {}

	public String getText() {
		return text;
	}

	public LogSend setText(String text) {
		this.text = text;
		return this;
	}

	public String getContext() {
		return context;
	}

	public LogSend setContext(String context) {
		this.context = context;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public LogSend setLevel(Integer level) {
		this.level = level;
		return this;
	}
}
