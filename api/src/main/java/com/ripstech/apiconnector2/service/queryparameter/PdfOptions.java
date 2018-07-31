package com.ripstech.apiconnector2.service.queryparameter;

public class PdfOptions extends QueryParamerters {

	public static PdfOptions empty() { return new PdfOptions(); }

	public PdfOptions userName(String value) {
		addParam("userName", value);
		return this;
	}

	public PdfOptions projectName(String value) {
		addParam("projectName", value);
		return this;
	}

	public PdfOptions chapterSummary(boolean value) {
		addParam("chapterSummary", value);
		return this;
	}

	public PdfOptions issueBreakdown(boolean value) {
		addParam("issueBreakdown", value);
		return this;
	}

	public PdfOptions issueDetails(boolean value) {
		addParam("issueDetails", value);
		return this;
	}

	public PdfOptions codeSummary(boolean value) {
		addParam("codeSummary", value);
		return this;
	}

	public PdfOptions context(boolean value) {
		addParam("context", value);
		return this;
	}

	public PdfOptions vulnerabilityDescription(boolean value) {
		addParam("vulnerabilityDescription", value);
		return this;
	}

	public PdfOptions comments(boolean value) {
		addParam("comments", value);
		return this;
	}

	public PdfOptions maxIssuesPerType(int value) {
		addParam("maxIssuesPerType", Integer.toString(value));
		return this;
	}

	private void addParam(String name, String value) {
		params.put(String.format("pdf[%s]", name), value);
	}

	private void addParam(String name, boolean value) {
		addParam(name, Boolean.toString(value));
	}

}
