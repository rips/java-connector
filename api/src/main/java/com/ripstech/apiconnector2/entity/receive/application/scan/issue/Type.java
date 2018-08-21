package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class Type {

	private long id;
	private String tag;
	private String description;
	private String name;
	private String color;
	private String markup;
	private Integer severity;
	private Integer effort;
	private Boolean secondOrder;
	private String cwe;
	private String owasp;
	private String sans;
	private String pcidss;
	private String asvs;
	private Boolean enabled;
	private Type parent;
	private String category;
	private List<Resource> resources = Collections.emptyList();
	private Boolean inactive;

	public long getId() {
		return this.id;
	}

	public String getTag() {
		return this.tag;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public String getColor() {
		return this.color;
	}

	public String getMarkup() {
		return this.markup;
	}

	public Integer getSeverity() {
		return this.severity;
	}

	public Integer getEffort() {
		return this.effort;
	}

	public Boolean getSecondOrder() {
		return this.secondOrder;
	}

	public String getCwe() {
		return this.cwe;
	}

	public String getOwasp() {
		return this.owasp;
	}

	public String getSans() {
		return this.sans;
	}

	public String getPcidss() {
		return this.pcidss;
	}

	public String getAsvs() {
		return this.asvs;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public Type getParent() {
		return this.parent;
	}

	public String getCategory() {
		return category;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public Boolean getInactive() {
		return inactive;
	}

	public static class Resource {

		private long id;
		private String title;
		private String author;
		private String url;
		private OffsetDateTime publishedAt;

		public long getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public String getAuthor() {
			return author;
		}

		public String getUrl() {
			return url;
		}

		public OffsetDateTime getPublishedAt() {
			return publishedAt;
		}
	}

}
