package com.ripstech.api.utils.profiles;

import com.ripstech.api.entity.receive.application.Profile;
import com.ripstech.api.utils.constant.RipsDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class AnalysisProfiles {

	private final Map<Long, Entry> entries;
	private final Long defaultId;

	AnalysisProfiles(Set<Profile> profiles, @Nullable Long defaultId) {
		this.entries = profiles.stream().collect(Collectors.toMap(Profile::getId, Entry::new));
		this.entries.put(-1L, new AutomaticSelectionEntry());
		// Will be used internally only
		this.defaultId = defaultId;
	}

	public static AnalysisProfiles empty() {
		return new AnalysisProfiles(Collections.emptySet(), null);
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

	public Entry getDefaultEntry() {
		return entries.getOrDefault(getDefaultId(), new AutomaticSelectionEntry());
	}

	public Long getDefaultId() {
		return -1L; // auto selection is the default option
	}

	public class Entry implements Comparable<Entry> {
		private final Profile profile;

		Entry(Profile profile) {
			this.profile = profile;
		}

		public Long getId() {
			return profile.getId();
		}

		public String getDisplayName() {
			return profile.getName() + (isGlobal() ? " (Global)" : "") + (isSuperDefault() ? " *" : "");
		}

		public boolean isGlobal() {
			return profile.getApplication() == null;
		}

		public int getDepth() {
			return profile.getSetting().getAnalysisDepth();
		}

		public boolean isSuperDefault() {
			return profile.getId().equals(AnalysisProfiles.this.defaultId);
		}

		public Profile getEntity() {
			return profile;
		}

		@Override
		public int compareTo(@NotNull AnalysisProfiles.Entry entry) {
			if(entry instanceof AutomaticSelectionEntry) {
				return 1;
			}
			return profile.getName().compareToIgnoreCase(entry.profile.getName());
		}

		@Override
		public String toString() {
			return getDisplayName();
		}
	}

	public class AutomaticSelectionEntry extends Entry {

		AutomaticSelectionEntry() {
			super(null);
		}

		@Override
		public Long getId() {
			return -1L;
		}

		@Override
		public String getDisplayName() {
			return "-- automatic selection --";
		}

		@Override
		public boolean isGlobal() {
			return false;
		}

		@Override
		public int getDepth() {
			if(defaultId == null) {
				return RipsDefault.DEFAULT_ANALYSIS_DEPTH;
			} else {
				return AnalysisProfiles.this.entries
						.entrySet()
						.stream()
						.filter(e -> e.getKey().equals(defaultId))
						.findFirst()
						.map(Map.Entry::getValue)
						.map(Entry::getDepth)
						.orElse(RipsDefault.DEFAULT_ANALYSIS_DEPTH);
			}
		}

		@Override
		public boolean isSuperDefault() {
			return super.isSuperDefault();
		}

		@Nullable
		@Override
		public Profile getEntity() {
			return super.getEntity();
		}

		@Override
		public int compareTo(@NotNull Entry entry) {
			return -1;
		}

	}

}
