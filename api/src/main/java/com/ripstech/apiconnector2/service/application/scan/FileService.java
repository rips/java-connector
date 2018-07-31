package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.File;
import com.ripstech.apiconnector2.service.template.DeleteGetService;

import java.util.List;

public class FileService extends DeleteGetService<File> {

	private final int applicationId;
	private final int scanId;

	public FileService(String baseUri, int applicationId, int scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<File> getGenericType() {
		return new TypeReference<File>() {};
	}

	@Override
	public TypeReference<List<File>> getGenericListType() {
		return new TypeReference<List<File>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/files", applicationId, scanId);
	}
}
