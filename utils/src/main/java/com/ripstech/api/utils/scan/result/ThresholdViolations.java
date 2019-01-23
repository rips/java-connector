package com.ripstech.api.utils.scan.result;

import com.ripstech.api.utils.constant.Severity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ThresholdViolations {

	private final Map<String, Entry> entries;

	public ThresholdViolations(@NotNull Thresholds thresholds, @NotNull ScanResult scanResult) {
		entries = new LinkedHashMap<>();
		Arrays.stream(Severity.values())
		      .filter(severity -> thresholds.getSeverity(severity) != null )
		      .forEachOrdered(severity -> {
			      final Integer threshold = thresholds.getSeverity(severity);
			      final int result = scanResult.getTotalIssues().getOrDefault(severity, 0);
			      if(result > threshold) {
			      	entries.put(severity.name().toLowerCase(), new Entry(threshold, result));
			      }
		      });
		final Integer newThreshold = thresholds.getNew();
		if(newThreshold != null) {
			final int newResult = scanResult.getAmoutOfNewIssues();
			if(newResult > newThreshold) {
				entries.put("new", new Entry(newThreshold, newResult));
			}
		}
	}

	public Map<String, Entry> getEntries() {
		return entries;
	}

	public Set<String> getViolatedNames() {
		return entries.keySet();
	}

	public boolean isFailed() {
		return entries.size() > 0;
	}

	public boolean isSuccess() {
		return entries.size() == 0;
	}

	public static class Entry {

		private int threshold;
		private int issues;

		public Entry(int threshold, int issues) {
			this.threshold = threshold;
			this.issues = issues;
		}

		public int getThreshold() {
			return threshold;
		}

		public int getIssues() {
			return issues;
		}
	}

}
