package com.ripstech.apiconnector2.service.application;

import com.ripstech.api.entity.send.ApplicationSend;
import com.ripstech.api.entity.send.application.ScanSend;
import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
import com.ripstech.apiconnector2.service.ApplicationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationScanServiceTest extends ApiSettings {

	private Api api;

	ApplicationScanServiceTest() throws IOException, ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
	}

	@Test
	@Disabled
	void postPatchDelete() throws ApiException, IOException {
		ApplicationService appService = api.applications();
		long appId = appService.post(new ApplicationSend.Post(ApplicationScanServiceTest.class.getName()))
				         .orThrow(ApiException::new)
				         .getId();
		UploadService uploadService = api.application(appId).uploads();
		File zipFile = File.createTempFile(ApplicationScanServiceTest.class.getName(), ".zip");
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		zos.putNextEntry(new ZipEntry("info.php"));
		zos.write("<?php phpinfo();phpinfo();phpinfo();phpinfo();phpinfo();phpinfo();phpinfo(); ?>".getBytes());
		zos.closeEntry();
		zos.close();
		fos.close();
		long uploadId = uploadService.post(zipFile).orThrow(ApiException::new).getId();
		zipFile.delete();
		ScanService scanService = api.application(appId).scans();
		long scanId = scanService.post(
				new ScanService.ScanSendPost(
						new ScanSend.Post(ApplicationScanServiceTest.class.getName())
								.setUpload(uploadId)))
				.orThrow(ApiException::new)
				.getId();
		List<String> tags = Collections.singletonList("abc");
		assertEquals(tags.size(),
		             scanService.patch(scanId, new ScanService.ScanSendPatch().setTags(tags))
				                    .orThrow(ApiException::new)
				                    .getTags()
				                    .size());
		scanService.delete(scanId);
		appService.delete(appId);
	}

}
