package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Scan;

import java.util.Collections;
import java.util.List;

public class Function {

	private long id;
	private String name;
	private List<Parameter> parameters = Collections.emptyList();
	private Integer startLine;
	private Integer endLine;
	private File file;
	@JsonProperty("class")
	private Class clazz;
	private Scan scan;

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public Integer getStartLine() {
		return this.startLine;
	}

	public Integer getEndLine() {
		return this.endLine;
	}

	public File getFile() {
		return this.file;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public Scan getScan() {
		return this.scan;
	}

	public static class Parameter {

		@JsonProperty("package")
		private String packageName;
		private String name;

		public String getPackageName() {
			return packageName;
		}

		public String getName() {
			return name;
		}

		public String getFullQualifiedName() {
			if(packageName == null || packageName.isEmpty()) {
				return name;
			}
			return packageName + "." + name;
		}

	}

}
