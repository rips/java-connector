package com.ripstech.apiconnector2.entity.send.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FunctionBatch extends Batch {

	private List<SubParameter> parameters;
	@JsonProperty("class")
	private Long clazz;

	public FunctionBatch(Integer startLine, Integer endLine, String name, List<SubParameter> parameters, Long clazz, Long file) {
		super(startLine, endLine, name, file);
		this.parameters = parameters;
		this.clazz = clazz;
	}

	public FunctionBatch(Batch batch, List<SubParameter> parameters, Long clazz) {
		super(batch);
		this.parameters = parameters;
		this.clazz = clazz;
	}

	public List<SubParameter> getParameters() {
		return parameters;
	}

	public FunctionBatch setParameters(List<SubParameter> parameters) {
		this.parameters = parameters;
		return this;
	}

	public Long getClazz() {
		return clazz;
	}

	public FunctionBatch setClazz(Long clazz) {
		this.clazz = clazz;
		return this;
	}

	public static class SubParameter {

		@JsonProperty("package")
		private String packageName;
		private String name;

		public SubParameter(final String packageName, final String name) {
			this.packageName = packageName;
			this.name = name;
		}

		public String getPackageName() {
			return packageName;
		}

		public String getName() {
			return name;
		}
	}
}
