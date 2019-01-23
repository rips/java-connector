package com.ripstech.api.utils.scan.result;

import com.ripstech.api.entity.receive.application.scan.File;
import com.ripstech.api.utils.constant.Severity;
import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.queryparameter.Filter;

import java.util.Map;
import java.util.stream.Collectors;

public class ScanResultParser {

	public static ScanResult getResult(Api api, long applicationId, long scanId) throws ApiException {
		return getResult(api, applicationId, scanId, Filter.empty());
	}

	public static ScanResult getResult(Api api, long applicationId, long scanId, Filter filter) throws ApiException {
		return new ScanResult(api.application(applicationId)
		                         .scan(scanId)
		                         .issues()
		                         .getStats(filter.select("issueSeverities"))
		                         .orThrow(ApiException::new)
		                         .getIssueSeverities());
	}

	@Deprecated
	public static Map<Severity, Integer> getTotalIssues(Api api, long applicationId, long scanId) throws ApiException{
		return getResult(api, applicationId, scanId).getTotalIssues();
	}

	@Deprecated
	public static Map<Severity, Integer> getNewIssues(Api api, long applicationId, long scanId) throws ApiException{
		return getResult(api, applicationId, scanId).getNewIssues();
	}

	public static ThresholdViolations getReachedThreshold(Thresholds thresholds, ScanResult scanResult) {
		return new ThresholdViolations(thresholds, scanResult);
	}

	public static ThresholdViolations getReachedThreshold(Api api,
	                                                         long appId,
	                                                         long scanId,
	                                                         Thresholds thresholds) throws ApiException {
		return getReachedThreshold(thresholds, getResult(api, appId, scanId));
	}

	public static Map<Long, String> getFilesFromIssues(Api api, long applicationId, long scanId) throws ApiException {
		return api.application(applicationId)
		          .scan(scanId)
		          .files()
		          .get()
		          .orThrow(ApiException::new)
		          .stream()
		          .collect(Collectors.toMap(File::getId,
		                                    File::getPath));
	}
}
