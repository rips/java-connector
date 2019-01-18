package com.ripstech.api.utils.profiles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysisProfiles {

	private final Map<Long, Entry> entries;
	private final Long defaultId;

	public AnalysisProfiles(Map<Long, Entry> entries, @Nullable Long defaultId) {
		this.entries = entries;
		this.defaultId = defaultId;
	}

	public Map<Long, String> getNames() {
		return entries.entrySet()
		              .stream()
		              .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
		              .collect(Collectors.toMap(Map.Entry::getKey,
		                                        o -> o.getValue().getDisplayName(),
		                                        (e1, e2) -> e1,
		                                        LinkedHashMap::new));
	}

	public Map<Long, Integer> getDepths() {
		return entries.entrySet()
		              .stream()
		              .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().depth));
	}

	public Map<Long, Entry> getEntries() {
		return entries;
	}

	@Nullable
	public Long getDefaultId() {
		return defaultId;
	}

	public static class Entry implements Comparable<Entry> {
		private final String name;
		private final boolean global;
		private final int depth;

		public Entry(String name, boolean global, int depth) {
			this.name = name;
			this.global = global;
			this.depth = depth;
		}

		public String getName() {
			return name;
		}

		public String getDisplayName() {
			return name + (global ? " (Global)" : "");
		}

		public boolean isGlobal() {
			return global;
		}

		public int getDepth() {
			return depth;
		}

		@Override
		public int compareTo(@NotNull AnalysisProfiles.Entry entry) {
			return name.compareToIgnoreCase(entry.name);
		}
	}
}
