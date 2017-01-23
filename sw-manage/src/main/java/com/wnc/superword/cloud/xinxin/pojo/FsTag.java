package com.wnc.superword.cloud.xinxin.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tags")
public class FsTag {
	@Id
	private int id;
	private String tagName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tag_name) {
		this.tagName = tag_name;
	}

	@Override
	public String toString() {
		return "FsTag [id=" + id + ", tag_name=" + tagName + "]";
	}
}
