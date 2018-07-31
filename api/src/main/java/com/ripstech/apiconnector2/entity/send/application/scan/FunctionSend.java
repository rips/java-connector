package com.ripstech.apiconnector2.entity.send.application.scan;

import java.util.Arrays;
import java.util.List;


public class FunctionSend {

	private List<Batch> functions;

	public static FunctionSend createPost(Batch function) {
		return new FunctionSend().addFunction(function);
	}

	public static FunctionSend createPost(List<Batch> function) {
		return new FunctionSend().setFunctions(function);
	}

	public static FunctionSend createPost(Batch... function) {
		return new FunctionSend().setFunctions(Arrays.asList(function));
	}

	private FunctionSend() {}

	public List<Batch> getFunctions() {
		return functions;
	}

	public FunctionSend setFunctions(List<Batch> functions) {
		this.functions = functions;
		return this;
	}

	public FunctionSend addFunction(Batch functions) {
		this.functions.add(functions);
		return this;
	}

}
