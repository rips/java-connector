package com.ripstech.api.utils.scan.result;

import com.ripstech.api.utils.constant.Severity;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ScanResult {

	private final static String TOTAL_ISSUE_STATS = "total";
	private final static String NEW_ISSUE_STATS = "new";

	private final int criticalSeverityIssues;
	private final int highSeverityIssues;
	private final int mediumSeverityIssues;
	private final int lowSeverityIssues;
	private final int newIssues;
	private final Map<Severity, Integer> newSeverityIssues;

	public ScanResult(@NotNull Map<String, Map<String, Integer>> issueSeverities) {
		Map<String, Integer> total = issueSeverities.getOrDefault(TOTAL_ISSUE_STATS, Collections.emptyMap());
		criticalSeverityIssues = total.getOrDefault(Severity.CRITICAL.name().toLowerCase(), 0);
		highSeverityIssues = total.getOrDefault(Severity.HIGH.name().toLowerCase(), 0);
		mediumSeverityIssues = total.getOrDefault(Severity.MEDIUM.name().toLowerCase(), 0);
		lowSeverityIssues = total.getOrDefault(Severity.LOW.name().toLowerCase(), 0);
		Map<String, Integer> newOnes = issueSeverities.getOrDefault(NEW_ISSUE_STATS, Collections.emptyMap());
		newSeverityIssues = new LinkedHashMap<>();
		Arrays.stream(Severity.values()).forEachOrdered(severity -> newSeverityIssues
				.put(severity, newOnes.getOrDefault(severity.name().toLowerCase(), 0)));
		newIssues = newOnes.values().stream().mapToInt(Integer::intValue).sum();
	}

	public Map<Severity, Integer> getTotalIssues() {
		Map<Severity, Integer> totalIssues = new LinkedHashMap<>();
		totalIssues.put(Severity.CRITICAL, criticalSeverityIssues);
		totalIssues.put(Severity.HIGH, highSeverityIssues);
		totalIssues.put(Severity.MEDIUM, mediumSeverityIssues);
		totalIssues.put(Severity.LOW, lowSeverityIssues);
		return totalIssues;
	}

	public Map<Severity, Integer> getNewIssues() {
		return newSeverityIssues;
	}

	public int getAmoutOfNewIssues() {
		return newIssues;
	}

	public Map<String, Integer> getMap() {
		Map<String, Integer> map = new HashMap<>();
		map.put(Severity.CRITICAL.name().toLowerCase(), criticalSeverityIssues);
		map.put(Severity.HIGH.name().toLowerCase(), highSeverityIssues);
		map.put(Severity.MEDIUM.name().toLowerCase(), mediumSeverityIssues);
		map.put(Severity.LOW.name().toLowerCase(), lowSeverityIssues);
		map.put(NEW_ISSUE_STATS, newIssues);
		return map;	}

	public int getCriticalSeverityIssues() {
		return criticalSeverityIssues;
	}

	public int getHighSeverityIssues() {
		return highSeverityIssues;
	}

	public int getMediumSeverityIssues() {
		return mediumSeverityIssues;
	}

	public int getLowSeverityIssues() {
		return lowSeverityIssues;
	}

	@Override
	public String toString() {
		return String.format("critical: %d, high: %d, medium: %d, low: %d, new: %d",
		                     criticalSeverityIssues, highSeverityIssues, mediumSeverityIssues, lowSeverityIssues,
		                     newIssues);
	}

	public String toString(Thresholds thresholds) {
		return String.format("critical: %d/%d, high: %d/%d, medium: %d/%d, low: %d/%d, new: %d/%d",
		                     criticalSeverityIssues, thresholds.getSeverity(Severity.CRITICAL),
		                     highSeverityIssues, thresholds.getSeverity(Severity.HIGH),
		                     mediumSeverityIssues, thresholds.getSeverity(Severity.MEDIUM),
		                     lowSeverityIssues, thresholds.getSeverity(Severity.LOW),
		                     newIssues, thresholds.getNew());
	}
}
