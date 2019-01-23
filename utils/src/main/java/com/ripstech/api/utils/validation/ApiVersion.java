package com.ripstech.api.utils.validation;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

final public class ApiVersion implements Comparable<ApiVersion> {
	// \p{Alpha} matches an alphabetic character
	private static final Pattern VERSION_PATTERN = Pattern.compile("((\\d+)(\\.\\d+)+)(-(\\p{Alpha}+)(-([a-z]?\\p{XDigit}+))?)?");

	private static final int STAGE_MILESTONE = 0;

	private final String version;
	private int majorPart;
	private String versionPart;
	private Stage stage;

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

		if (matcher.group(4) != null) {
			int stageNumber;
			switch (matcher.group(5)) {
				case "milestone":
					stageNumber = STAGE_MILESTONE;
					break;
				case "preview":
					stageNumber = 2;
					break;
				case "rc":
					stageNumber = 3;
					break;
				default:
					stageNumber = 1;
					break;
			}
			String stageString = matcher.group(7);
			if (stageString != null) {
				stage = new Stage(stageNumber, stageString);
			} else {
				stage = null;
			}
		} else {
			stage = null;
		}

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
		if (stage == null) {
			return this;
		}
		return parse(versionPart);
	}

	public ApiVersion getNextMajor() {
		if (stage != null && stage.stage == STAGE_MILESTONE) {
			return parse(majorPart + ".0");
		}
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

		if (stage != null && apiVersion.stage != null) {
			int diff = stage.compareTo(apiVersion.stage);
			if (diff != 0) {
				return diff;
			}
		}
		if (stage == null && apiVersion.stage != null) {
			return 1;
		}
		if (stage != null && apiVersion.stage == null) {
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

	public boolean isValid() {
		return versionPart != null;
	}

	static final class Stage implements Comparable<Stage> {
		final int stage;
		final int number;
		final Character patchNo;

		Stage(int stage, String number) {
			this.stage = stage;
			Matcher m = Pattern.compile("(\\d+)([a-z])?").matcher(number);
			try {
				m.matches();
				this.number = Integer.parseInt(m.group(1));
			} catch (Exception e) {
				throw new RuntimeException("Invalid stage small number: " + number, e);
			}

			if (m.groupCount() == 2 && m.group(2) != null) {
				this.patchNo = m.group(2).charAt(0);
			} else {
				this.patchNo = '_';
			}
		}

		@Override
		public int compareTo(Stage other) {
			if (stage > other.stage) {
				return 1;
			}
			if (stage < other.stage) {
				return -1;
			}
			if (number > other.number) {
				return 1;
			}
			if (number < other.number) {
				return -1;
			}
			if (patchNo > other.patchNo) {
				return 1;
			}
			if (patchNo < other.patchNo) {
				return -1;
			}
			return 0;
		}
	}

}
