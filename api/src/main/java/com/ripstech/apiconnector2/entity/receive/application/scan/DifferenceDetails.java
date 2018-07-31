package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class DifferenceDetails {

	private List<Entry> old = Collections.emptyList();
	@JsonProperty("new")
	private List<Entry> new_ = Collections.emptyList();
	private List<Entry> fixed = Collections.emptyList();

	public List<Entry> getNew_() {
		return new_;
	}

	public List<Entry> getOld() {
		return this.old;
	}

	public List<Entry> getFixed() {
		return this.fixed;
	}

	public class Entry {

		private int id;
		private List<String> comments = Collections.emptyList();
		private List<String> summaries = Collections.emptyList();
		private List<String> markups = Collections.emptyList();
		private Integer depth;
		private List<String> reviews = Collections.emptyList();
		private Boolean reviewed;
		private Boolean negativelyReviewed;
		private Integer effort;
		private Boolean complete;
		private String hash;

		public int getId() {
			return this.id;
		}

		public List<String> getComments() {
			return this.comments;
		}

		public List<String> getSummaries() {
			return this.summaries;
		}

		public List<String> getMarkups() {
			return this.markups;
		}

		public Integer getDepth() {
			return this.depth;
		}

		public List<String> getReviews() {
			return this.reviews;
		}

		public Boolean getReviewed() {
			return this.reviewed;
		}

		public Boolean getNegativelyReviewed() {
			return this.negativelyReviewed;
		}

		public Integer getEffort() {
			return this.effort;
		}

		public Boolean getComplete() {
			return this.complete;
		}

		public String getHash() {
			return this.hash;
		}
	}

}
