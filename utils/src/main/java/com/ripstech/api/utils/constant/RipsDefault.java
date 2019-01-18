package com.ripstech.api.utils.constant;

import com.ripstech.api.utils.validation.ApiVersion;

public class RipsDefault {

	private RipsDefault() {}

	public static final ApiVersion CURRENT_API_VERSION = ApiVersion.parse("3.0.2");

	public final static String UI_URL = "https://saas.ripstech.com";
	public final static String UI_URL_2 = "https://saas-2.ripstech.com";
	public final static String UI_URL_3 = "https://saas-3.ripstech.com";
	public final static String UI_URL_CURRENT = UI_URL_3;
	public final static String API_URL = "https://api.ripstech.com";
	public final static String API_URL_2 = "https://api-2.ripstech.com";
	public final static String API_URL_3 = "https://api-3.ripstech.com";
	public final static String API_URL_CURRENT = API_URL_3;

	public final static long SCAN_TIMEOUT_IN_MINUTES = 5 * 60;

	public final static String VERSION_PATTERN = "{isoDateTime}";

}
