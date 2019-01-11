package com.ripstech.apiconnector2.entity.receive.callback;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "class"
)
@JsonSubTypes({
		@JsonSubTypes.Type(
				value = ScanCallback.class,
				name = "Application\\Scan"),
		@JsonSubTypes.Type(
				value = ReviewCallback.class,
				name = "Application\\Scan\\Issue\\Review"),
		@JsonSubTypes.Type(
				value = CommentCallback.class,
				name = "Application\\Scan\\Issue\\Comment"),
})
public class CallbackMessage {

	private String action;
	@JsonProperty("class")
	private String entity;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

}
