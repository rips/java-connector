package com.ripstech.api.utils.application;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class UserApplications {

	private final Map<Long, Entry> entries;

	public UserApplications(Map<Long, Entry> entries) {
		this.entries = entries;
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

	public Map<Long, Entry> getEntries() {
		return entries;
	}

	public static class Entry implements Comparable<Entry> {
		private final String name;
		private final String language;

		public Entry(String name, String language) {
			this.name = name;
			this.language = language;
		}

		public String getName() {
			return name;
		}

		public String getDisplayName(long id) {
			return String.format("%s (%d)", getDisplayName(), id);
		}

		public String getDisplayName() {
			return String.format("%s [%s]", name, language);
		}

		public String getLanguage() {
			return language;
		}

		@Override
		public int compareTo(@NotNull UserApplications.Entry entry) {
			return name.compareToIgnoreCase(entry.name);
		}
	}
}
