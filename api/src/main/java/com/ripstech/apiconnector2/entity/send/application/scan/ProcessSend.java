package com.ripstech.apiconnector2.entity.send.application.scan;

public class ProcessSend {

	public static ProcessPatch createPatch() {
		return ProcessPatch.createPatch();
	}

	public static ProcessPost createPost(int pid) {
		return ProcessPost.createPost(pid);
	}

}
