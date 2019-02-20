package com.ripstech.api.utils.application;

import com.ripstech.api.entity.receive.Application;
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
		private final Application application;
		private final String language;

		Entry(Application application, String language) {
			this.application = application;
			this.language = language;
		}

		public String getDisplayName(long id) {
			return String.format("%s (%d)", getDisplayName(), id);
		}

		public String getDisplayName() {
			return String.format("%s [%s]", application.getName(), language);
		}

		public String getLanguage() {
			return language;
		}

		public Application getEntity() {
			return application;
		}

		@Override
		public int compareTo(@NotNull UserApplications.Entry entry) {
			return application.getName().compareToIgnoreCase(entry.application.getName());
		}

		@Override
		public String toString() {
			return getDisplayName();
		}
	}
}
