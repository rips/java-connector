package com.ripstech.api.utils.validation;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

final public class ApiVersion implements Comparable<ApiVersion> {
	// \p{Alnum} matches an alphabetic character or a digit [a-zA-Z0-9]
	private static final Pattern VERSION_PATTERN = Pattern.compile("((\\d+)(\\.\\d+)+)(.*)?");

	private static final int STAGE_MILESTONE = 0;

	private final String version;
	private int majorPart;
	private String versionPart;

	/**
	 * Creates a new ApiVersion object, throws IllegalArgumentException if the parse String doesn't match the pattern.
	 * See {@link #VERSION_PATTERN}
	 *
	 * @param version Version String
	 * @return new ApiVersion
	 * @throws IllegalArgumentException
	 */
	public static ApiVersion parse(String version) throws IllegalArgumentException{
		return new ApiVersion(version);
	}

	private ApiVersion(String version) {
		this.version = version;
		parseVersion(version);
	}

	private void parseVersion(String version) throws IllegalArgumentException{
		Matcher matcher = VERSION_PATTERN.matcher(version);
		if (!matcher.matches()) {
			throw new IllegalArgumentException(format("'%s' cannot be parsed into a parse object", version));
		}

		versionPart = matcher.group(1);
		majorPart = Integer.parseInt(matcher.group(2), 10);

	}

	public String getVersion() {
		return version;
	}

	/**
	 * The base parse of this parse. For pre-release versions, this is the target parse.
	 *
	 * For example, the parse base of '1.2-rc-1' is '1.2'.
	 *
	 * @return The parse base
	 */
	public ApiVersion getBaseVersion() {
		return parse(versionPart);
	}

	public ApiVersion getNextMajor() {
		return parse((majorPart + 1) + ".0");
	}

	@Override
	public int compareTo(@NotNull ApiVersion apiVersion) {
		String[] majorVersionParts = versionPart.split("\\.", -1);
		String[] otherMajorVersionParts = apiVersion.versionPart.split("\\.", -1);

		for (int i = 0; i < majorVersionParts.length && i < otherMajorVersionParts.length; i++) {
			int part = Integer.parseInt(majorVersionParts[i]);
			int otherPart = Integer.parseInt(otherMajorVersionParts[i]);

			if (part > otherPart) {
				return 1;
			}
			if (otherPart > part) {
				return -1;
			}
		}
		if (majorVersionParts.length > otherMajorVersionParts.length) {
			return 1;
		}
		if (majorVersionParts.length < otherMajorVersionParts.length) {
			return -1;
		}

		return 0;
	}

	public boolean isGreaterEqualThan(@NotNull ApiVersion apiVersion) {
		return this.compareTo(apiVersion) >= 0;
	}

	public boolean isLowerThan(@NotNull ApiVersion apiVersion) {
		return this.compareTo(apiVersion) < 0;
	}

	public boolean isCompatible(@NotNull ApiVersion apiVersion) {
		if(this.majorPart != apiVersion.majorPart) {
			return false;
		}
		return apiVersion.isGreaterEqualThan(this);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass() != getClass()) {
			return false;
		}
		ApiVersion other = (ApiVersion) o;
		return version.equals(other.version);
	}

	@Override
	public int hashCode() {
		return version.hashCode();
	}

	@Override
	public String toString() {
		return version;
	}

	public boolean isValid() {
		return versionPart != null;
	}

}
