package com.ripstech.api.utils.scan.result;

import com.ripstech.api.utils.constant.Severity;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Thresholds {
	private final Integer criticalThreshold;
	private final Integer highThreshold;
	private final Integer mediumThreshold;
	private final Integer lowThreshold;
	private final Integer newThreshold;

	public Thresholds(Integer criticalThreshold, Integer highThreshold, Integer mediumThreshold, Integer lowThreshold,
	                  Integer newThreshold) {
		this.criticalThreshold = criticalThreshold;
		this.highThreshold = highThreshold;
		this.mediumThreshold = mediumThreshold;
		this.lowThreshold = lowThreshold;
		this.newThreshold = newThreshold;
	}

	public Thresholds(Map<Severity, Integer> thresholds) {
		this.criticalThreshold = thresholds.get(Severity.CRITICAL);
		this.highThreshold = thresholds.get(Severity.HIGH);
		this.mediumThreshold = thresholds.get(Severity.MEDIUM);
		this.lowThreshold = thresholds.get(Severity.LOW);
		this.newThreshold = null;
	}

	public Thresholds(Map<Severity, Integer> thresholds, Integer newThreshold) {
		this.criticalThreshold = thresholds.get(Severity.CRITICAL);
		this.highThreshold = thresholds.get(Severity.HIGH);
		this.mediumThreshold = thresholds.get(Severity.MEDIUM);
		this.lowThreshold = thresholds.get(Severity.LOW);
		this.newThreshold = newThreshold;
	}

	@Nullable
	Integer getSeverity(Severity threshold) {
		switch (threshold) {
			case CRITICAL:
				return criticalThreshold;
			case HIGH:
				return highThreshold;
			case MEDIUM:
				return mediumThreshold;
			case LOW:
				return lowThreshold;
			default:
				throw new UnsupportedOperationException();
		}
	}

	@Nullable
	Integer getNew() {
		return newThreshold;
	}

}
