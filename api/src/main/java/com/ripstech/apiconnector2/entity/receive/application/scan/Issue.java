package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.ripstech.apiconnector2.entity.receive.application.Scan;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Issue {

	private long id;
	private List<Comment> comments = Collections.emptyList();
	private List<Summary> summaries = Collections.emptyList();
	private List<Markup> markups = Collections.emptyList();
	private OriginType origin;
	private Integer depth;
	private Type type;
	private List<Review> reviews = Collections.emptyList();
	private Boolean reviewed;
	private Boolean negativelyReviewed;
	private Source source;
	private Sink sink;
	private Scan scan;
	private Map<String, String> readable = Collections.emptyMap();
	private Issue parent;
	private Review lastReview;
	private Integer effort;
	private Boolean complete;
	private Concat concat;
	private String hash;
	private Boolean directlyCalled;
	private Boolean registerGlobals;
	private String cve;
	private String uuid;

	public long getId() {
		return this.id;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public List<Summary> getSummaries() {
		return this.summaries;
	}

	public List<Markup> getMarkups() {
		return this.markups;
	}

	public OriginType getOrigin() {
		return this.origin;
	}

	public Integer getDepth() {
		return this.depth;
	}

	public Type getType() {
		return this.type;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public Boolean getReviewed() {
		return this.reviewed;
	}

	public Boolean getNegativelyReviewed() {
		return this.negativelyReviewed;
	}

	public Source getSource() {
		return this.source;
	}

	public Sink getSink() {
		return this.sink;
	}

	public Scan getScan() {
		return this.scan;
	}

	public Map<String, String> getReadable() {
		return this.readable;
	}

	public Issue getParent() {
		return this.parent;
	}

	public Review getLastReview() {
		return this.lastReview;
	}

	public Integer getEffort() {
		return this.effort;
	}

	public Boolean getComplete() {
		return this.complete;
	}

	public Concat getConcat() {
		return this.concat;
	}

	public String getHash() {
		return this.hash;
	}

	public Boolean getDirectlyCalled() {
		return directlyCalled;
	}

	public Boolean getRegisterGlobals() {
		return registerGlobals;
	}

	public String getCve() {
		return cve;
	}

	public String getUuid() {
		return uuid;
	}

}
