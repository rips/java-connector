package com.ripstech.apiconnector2.entity.send.application.scan;

import java.util.Arrays;
import java.util.List;

public class ClassSend {

	private List<Batch> classes;

	public static ClassSend createPost(Batch function) {
		return new ClassSend().addClasses(function);
	}

	public static ClassSend createPost(List<Batch> function) {
		return new ClassSend().setClasses(function);
	}

	public static ClassSend createPost(Batch... function) {
		return new ClassSend().setClasses(Arrays.asList(function));
	}

	private ClassSend() {}

	public List<Batch> getClasses() {
		return classes;
	}

	public ClassSend setClasses(List<Batch> classes) {
		this.classes = classes;
		return this;
	}

	public ClassSend addClasses(Batch classes) {
		this.classes.add(classes);
		return this;
	}

}
