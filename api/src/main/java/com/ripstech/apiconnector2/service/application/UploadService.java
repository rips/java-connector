package com.ripstech.apiconnector2.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.application.Upload;
import com.ripstech.apiconnector2.service.template.DeletePostGetService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.POST;

public class UploadService extends DeletePostGetService<Upload, File> {

	private final long applicationId;

	public UploadService(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
	}

	@Override
	public TypeReference<Upload> getGenericType() {
		return new TypeReference<Upload>() {};
	}

	@Override
	public TypeReference<List<Upload>> getGenericListType() {
		return new TypeReference<List<Upload>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/uploads", applicationId);
	}

	@Override
	public ApiResponse<Upload> post(File file) {
		return new ApiResponse<>(getTarget(POST)
				                         .setBody(new MultipartBody.Builder()
						                                  .setType(MultipartBody.FORM)
						                                  .addFormDataPart("upload[file]",
						                                                   file.getName(),
						                                                   RequestBody.create(MediaType.parse("application/zip"),
						                                                                      file))
						                                  .build()),
		                         getGenericType());
	}
}
