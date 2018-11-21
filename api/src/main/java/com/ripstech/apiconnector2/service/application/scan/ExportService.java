package com.ripstech.apiconnector2.service.application.scan;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.queryparameter.PdfOptions;
import com.ripstech.apiconnector2.service.template.GenericService;

import java.io.InputStream;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public class ExportService extends GenericService {

	private static final String MIMETYPE_TEXT_CSV = "text/csv";
	private static final String MIMETYPE_APPLICATION_PDF = "application/pdf";

	private final long applicationId;
	private final long scanId;

	public ExportService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/exports", applicationId, scanId);
	}

	public ApiResponse<InputStream> csv() {
		return csv(Filter.empty());
	}

	public ApiResponse<InputStream> csv(Filter filter) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath("csvs")
				                         .setQueryParams(filter)
				                         .setAcceptHeader(MIMETYPE_TEXT_CSV),
		                         InputStream.class);
	}

	public ApiResponse<InputStream> pdf() {
		return pdf(Filter.empty(), PdfOptions.empty());
	}

	public ApiResponse<InputStream> pdf(PdfOptions pdfOptions) {
		return pdf(Filter.empty(), pdfOptions);
	}

	public ApiResponse<InputStream> pdf(Filter filter) {
		return pdf(filter, PdfOptions.empty());
	}

	public ApiResponse<InputStream> pdf(Filter filter, PdfOptions pdfOptions) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath("pdfs")
				                         .setQueryParams(filter)
				                         .addQueryParams(pdfOptions)
				                         .setAcceptHeader(MIMETYPE_APPLICATION_PDF),
		                         InputStream.class);
	}
}
