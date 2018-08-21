package com.ripstech.apiconnector2.service.application;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.entity.receive.application.Scan;
import com.ripstech.apiconnector2.entity.send.ApplicationSend;
import com.ripstech.apiconnector2.entity.send.application.ScanPost;
import com.ripstech.apiconnector2.entity.send.application.ScanSend;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
import com.ripstech.apiconnector2.service.ApplicationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.ripstech.apiconnector2.entity.send.application.ScanPost.ScanSub.upload;
import static com.ripstech.apiconnector2.entity.send.application.ScanPost.scan;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationScanServiceTest extends ApiSettings {

	private static Api api;

	ApplicationScanServiceTest() throws IOException, ApiException {
	}

	@BeforeAll
	static void setUp() throws ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
	}

	@Test
	void postPatchDelete() throws ApiException, IOException {
		ApplicationService appService = api.applications();
		long appId = appService.post(ApplicationSend.createPost(ApplicationScanServiceTest.class.getName()))
				         .orThrow(ApiException::new)
				         .getId();
		UploadService uploadService = api.application(appId).uploads();
		File zipFile = File.createTempFile(ApplicationScanServiceTest.class.getName(), ".zip");
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		zos.putNextEntry(new ZipEntry("info.php"));
		zos.write("<?php phpinfo(); ?>".getBytes());
		zos.closeEntry();
		zos.close();
		fos.close();
		long uploadId = uploadService.post(zipFile).orThrow(ApiException::new).getId();
		zipFile.delete();
		ScanService scanService = api.application(appId).scans();
		long scanId = scanService.post(ScanPost.createPost(
				scan(ApplicationScanServiceTest.class.getName(),
				     upload(uploadId)
				    )))
				             .orThrow(ApiException::new)
				             .getId();
		List<String> tags = Collections.singletonList("abc");
		assertEquals(tags,
		             scanService.patch(scanId, ScanSend.createPatch().setTags(tags))
				             .orThrow(ApiException::new)
				             .getTags()
				             .stream()
				             .map(Scan.TagItem::getName)
				             .collect(Collectors.toList()));
		scanService.delete(scanId);
		appService.delete(appId);
	}

}
