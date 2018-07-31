package com.ripstech.apiconnector2.entity.send.application.scan;

import com.ripstech.apiconnector2.entity.send.dsl.Parameter;
import com.ripstech.apiconnector2.entity.send.dsl.ParameterImpl;

import java.util.Arrays;

public class PhpSend {

	private String releaseVersion;
	private Boolean allowUrlInclude;
	private Boolean magicQuotesGpc;
	private Boolean allowUrlFopen;
	private String minorVersion;
	private String majorVersion;
	private Boolean registerGlobals;
	private String filterDefault;

	public static PhpSend createPost() {
		return new PhpSend();
	}

	@SafeVarargs
	public static PhpSend createPost(Parameter<PhpSend>... params) {
		PhpSend applicationScanPhpPost = new PhpSend();
		Arrays.stream(params).forEach(param -> param.set(applicationScanPhpPost));
		return applicationScanPhpPost;
	}

	public String getReleaseVersion() {
		return this.releaseVersion;
	}

	public Boolean getAllowUrlInclude() {
		return this.allowUrlInclude;
	}

	public Boolean getMagicQuotesGpc() {
		return this.magicQuotesGpc;
	}

	public Boolean getAllowUrlFopen() {
		return this.allowUrlFopen;
	}

	public String getMinorVersion() {
		return this.minorVersion;
	}

	public String getMajorVersion() {
		return this.majorVersion;
	}

	public Boolean getRegisterGlobals() {
		return this.registerGlobals;
	}

	public String getFilterDefault() {
		return this.filterDefault;
	}

	public static Parameter<PhpSend> releaseVersion(String releaseVersion) {
		return new ParameterImpl<>(releaseVersion, PhpSend::setReleaseVersion);
	}

	public PhpSend setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
		return this;
	}

	public static Parameter<PhpSend> allowUrlInclude(Boolean allowUrlInclude) {
		return new ParameterImpl<>(allowUrlInclude, PhpSend::setAllowUrlInclude);
	}

	public PhpSend setAllowUrlInclude(Boolean allowUrlInclude) {
		this.allowUrlInclude = allowUrlInclude;
		return this;
	}

	public static Parameter<PhpSend> magicQuotesGpc(Boolean magicQuotesGpc) {
		return new ParameterImpl<>(magicQuotesGpc, PhpSend::setMagicQuotesGpc);
	}

	public PhpSend setMagicQuotesGpc(Boolean magicQuotesGpc) {
		this.magicQuotesGpc = magicQuotesGpc;
		return this;
	}

	public static Parameter<PhpSend> allowUrlFopen(Boolean allowUrlFopen) {
		return new ParameterImpl<>(allowUrlFopen, PhpSend::setAllowUrlFopen);
	}

	public PhpSend setAllowUrlFopen(Boolean allowUrlFopen) {
		this.allowUrlFopen = allowUrlFopen;
		return this;
	}

	public static Parameter<PhpSend> minorVersion(String minorVersion) {
		return new ParameterImpl<>(minorVersion, PhpSend::setMinorVersion);
	}

	public PhpSend setMinorVersion(String minorVersion) {
		this.minorVersion = minorVersion;
		return this;
	}

	public static Parameter<PhpSend> majorVersion(String majorVersion) {
		return new ParameterImpl<>(majorVersion, PhpSend::setMajorVersion);
	}

	public PhpSend setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
		return this;
	}

	public static Parameter<PhpSend> registerGlobals(Boolean registerGlobals) {
		return new ParameterImpl<>(registerGlobals, PhpSend::setRegisterGlobals);
	}

	public PhpSend setRegisterGlobals(Boolean registerGlobals) {
		this.registerGlobals = registerGlobals;
		return this;
	}

	public static Parameter<PhpSend> filterDefault(String filterDefault) {
		return new ParameterImpl<>(filterDefault, PhpSend::setFilterDefault);
	}

	public PhpSend setFilterDefault(String filterDefault) {
		this.filterDefault = filterDefault;
		return this;
	}
}