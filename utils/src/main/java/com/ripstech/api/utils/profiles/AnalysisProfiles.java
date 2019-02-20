package com.ripstech.api.utils.profiles;

import com.ripstech.api.entity.receive.application.Profile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalysisProfiles {

	private final Map<Long, Entry> entries;
	private final Long defaultId;

	AnalysisProfiles(Set<Profile> profiles, @Nullable Long defaultId) {
		this.entries = profiles.stream().collect(Collectors.toMap(Profile::getId, Entry::new));
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
		              .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getDepth()));
	}

	public Map<Long, Entry> getEntries() {
		return entries;
	}

	@Nullable
	public Long getDefaultId() {
		return defaultId;
	}

	public class Entry implements Comparable<Entry> {
		private final Profile profile;

		Entry(Profile profile) {
			this.profile = profile;
		}

		public String getDisplayName() {
			return profile.getName() + (isGlobal() ? " (Global)" : "");
		}

		public boolean isGlobal() {
			return profile.getApplication() == null;
		}

		public int getDepth() {
			return profile.getSetting().getAnalysisDepth();
		}

		public boolean isSuperDefault() {
			return profile.getId().equals(AnalysisProfiles.this.getDefaultId());
		}

		public Profile getEntity() {
			return profile;
		}

		@Override
		public int compareTo(@NotNull AnalysisProfiles.Entry entry) {
			return profile.getName().compareToIgnoreCase(entry.profile.getName());
		}

		@Override
		public String toString() {
			return getDisplayName();
		}
	}
}
