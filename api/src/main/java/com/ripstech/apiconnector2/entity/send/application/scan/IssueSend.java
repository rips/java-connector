package com.ripstech.apiconnector2.entity.send.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IssueSend {

	private Issue issue;
	private Source source;
	private Sink sink;
	private Concat concat;
	private List<Markup> markups;
	private List<Summary> summaries;

	public static IssueSend createPost(Issue issue) {
		return new IssueSend().setIssue(issue);
	}

	public static Issue createIssue(long type, int depth) {
		return new Issue(type, depth);
	}

	public static Source createSource() {
		return new Source();
	}

	public static Sink createSink() {
		return new Sink();
	}

	public static Concat createConcat() {
		return new Concat();
	}

	public static Markup createMarkup() {
		return new Markup();
	}

	public static Summary createSummary() {
		return new Summary();
	}

	private IssueSend() {}

	public Issue getIssue() {
		return this.issue;
	}

	public Source getSource() {
		return this.source;
	}

	public Sink getSink() {
		return this.sink;
	}

	public Concat getConcat() {
		return this.concat;
	}

	public List<Markup> getMarkups() {
		return this.markups;
	}

	public List<Summary> getSummaries() {
		return this.summaries;
	}

	public IssueSend setIssue(Issue issue) {
		this.issue = issue;
		return this;
	}

	public IssueSend setSource(Source source) {
		this.source = source;
		return this;
	}

	public IssueSend setSink(Sink sink) {
		this.sink = sink;
		return this;
	}

	public IssueSend setConcat(Concat concat) {
		this.concat = concat;
		return this;
	}

	public IssueSend setMarkups(List<Markup> markups) {
		this.markups = markups;
		return this;
	}

	public IssueSend setSummaries(List<Summary> summaries) {
		this.summaries = summaries;
		return this;
	}

	public static class Issue {
		private Long type;
		private Long origin;
		private Integer depth;
		private String cve;
		private Boolean directlyCalled;
		private Boolean registerGlobals;
		private String tool;

		private Issue(long type, int depth) {
			this.type = type;
			this.depth = depth;
		}

		public Long getType() {
			return this.type;
		}

		public Long getOrigin() {
			return this.origin;
		}

		public Integer getDepth() {
			return this.depth;
		}

		public String getCve() {
			return cve;
		}

		public Boolean getDirectlyCalled() {
			return directlyCalled;
		}

		public Boolean getRegisterGlobals() {
			return registerGlobals;
		}

		public String getTool() {
			return tool;
		}

		public Issue setType(Long type) {
			this.type = type;
			return this;
		}

		public Issue setOrigin(Long origin) {
			this.origin = origin;
			return this;
		}

		public Issue setDepth(Integer depth) {
			this.depth = depth;
			return this;
		}

		public Issue setCve(String cve) {
			this.cve = cve;
			return this;
		}

		public Issue setDirectlyCalled(Boolean directlyCalled) {
			this.directlyCalled = directlyCalled;
			return this;
		}

		public Issue setRegisterGlobals(Boolean registerGlobals) {
			this.registerGlobals = registerGlobals;
			return this;
		}

		public Issue setTool(String tool) {
			this.tool = tool;
			return this;
		}
	}

	public static class Source {
		private Integer line;
		private String name;
		private String parameter;
		private Long file;
		private Long function;
		@JsonProperty("class")
		private Long clazz;

		public Integer getLine() {
			return this.line;
		}

		public String getName() {
			return this.name;
		}

		public String getParameter() {
			return this.parameter;
		}

		public Long getFile() {
			return this.file;
		}

		public Long getFunction() {
			return this.function;
		}

		public Long getClazz() {
			return this.clazz;
		}

		public Source setLine(Integer line) {
			this.line = line;
			return this;
		}

		public Source setName(String name) {
			this.name = name;
			return this;
		}

		public Source setParameter(String parameter) {
			this.parameter = parameter;
			return this;
		}

		public Source setFile(Long file) {
			this.file = file;
			return this;
		}

		public Source setFunction(Long function) {
			this.function = function;
			return this;
		}

		public Source setClazz(Long clazz) {
			this.clazz = clazz;
			return this;
		}
	}

	public static class Sink {
		private Integer line;
		private String name;
		private Long file;
		private Long function;
		@JsonProperty("class")
		private Long clazz;

		public Integer getLine() {
			return this.line;
		}

		public String getName() {
			return this.name;
		}

		public Long getFile() {
			return this.file;
		}

		public Long getFunction() {
			return this.function;
		}

		public Long getClazz() {
			return this.clazz;
		}

		public Sink setLine(Integer line) {
			this.line = line;
			return this;
		}

		public Sink setName(String name) {
			this.name = name;
			return this;
		}

		public Sink setFile(Long file) {
			this.file = file;
			return this;
		}

		public Sink setFunction(Long function) {
			this.function = function;
			return this;
		}

		public Sink setClazz(Long clazz) {
			this.clazz = clazz;
			return this;
		}
	}

	public static class Concat {
		private Integer line;
		private String name;
		private Long file;
		private Long function;
		@JsonProperty("class")
		private Long clazz;

		public Integer getLine() {
			return this.line;
		}

		public String getName() {
			return this.name;
		}

		public Long getFile() {
			return file;
		}

		public Long getFunction() {
			return this.function;
		}

		public Long getClazz() {
			return this.clazz;
		}

		public Concat setLine(Integer line) {
			this.line = line;
			return this;
		}

		public Concat setName(String name) {
			this.name = name;
			return this;
		}

		public Concat setFile(Long file) {
			this.file = file;
			return this;
		}

		public Concat setFunction(Long function) {
			this.function = function;
			return this;
		}

		public Concat setClazz(Long clazz) {
			this.clazz = clazz;
			return this;
		}
	}

	public static class Markup {
		private String markup;

		public String getMarkup() {
			return this.markup;
		}

		public Markup setMarkup(String markup) {
			this.markup = markup;
			return this;
		}
	}

	public static class Summary {
		private Integer line;
		private String content;
		private Long file;

		public Integer getLine() {
			return this.line;
		}

		public String getContent() {
			return this.content;
		}

		public Long getFile() {
			return this.file;
		}

		public Summary setLine(Integer line) {
			this.line = line;
			return this;
		}

		public Summary setContent(String content) {
			this.content = content;
			return this;
		}

		public Summary setFile(Long file) {
			this.file = file;
			return this;
		}
	}

}
