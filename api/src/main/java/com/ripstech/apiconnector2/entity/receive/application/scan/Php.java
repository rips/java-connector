package com.ripstech.apiconnector2.entity.receive.application.scan;

public class Php {

	private String releaseVersion;
	private Boolean allowUrlInclude;
	private Boolean magicQuotesGpc;
	private Boolean allowUrlFopen;
	private String minorVersion;
	private String majorVersion;
	private Boolean registerGlobals;
	private String filterDefault;
	private Integer id;

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

	public Integer getId() {
		return this.id;
	}

	public String getVersion() {
		return String.join(".", majorVersion, minorVersion, releaseVersion);
	}

}
