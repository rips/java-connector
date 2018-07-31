package com.ripstech.apiconnector2.entity.send.application;

import com.ripstech.apiconnector2.entity.send.dsl.Parameter;
import com.ripstech.apiconnector2.entity.send.dsl.RequiredParameter;

public class ScanSend {

	public static ScanPatch createPatch() {
		return ScanPatch.createPatch();
	}

	public static ScanPost createPost(String version) {
		return ScanPost.createPost(ScanPost.ScanSub.createPost(version));
	}

	public static ScanPost createPost(ScanPost.ScanSub scan) {
		return ScanPost.createPost(scan);
	}

	public static ScanPost createPost(ScanPost.ScanSub scan, Parameter<ScanPost>... params) {
		return ScanPost.createPost(scan, params);
	}


	public static ScanPost createPost(RequiredParameter<ScanPost> reqParam, Parameter<ScanPost>... params) {
		return ScanPost.createPost(reqParam, params);
	}



}
