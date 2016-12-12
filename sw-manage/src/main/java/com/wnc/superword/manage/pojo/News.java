package com.wnc.superword.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select max(id)+1 from news")
	private Long id;
	private String title;
	private String url;
	@Column(name = "sub_text")
	private String subText;
	private String date;
	@Column(name = "head_pic")
	private String headPic;

	private String keywords;

	@Column(name = "website_id")
	private int websiteId;

	@Column(name = "html_content")
	private String htmlContent;

	@Column(name = "cet_topics")
	private String cetTopics;

	@Column(name = "create_time")
	private String createTime;

	@Column(name = "topic_counts")
	private int topicCounts;

	@Column(name = "comment_counts")
	private int commentCounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubText() {
		return subText;
	}

	public void setSubText(String subText) {
		this.subText = subText;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getCetTopics() {
		return cetTopics;
	}

	public void setCetTopics(String cetTopics) {
		this.cetTopics = cetTopics;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getTopicCounts() {
		return topicCounts;
	}

	public void setTopicCounts(int topicCounts) {
		this.topicCounts = topicCounts;
	}

	public int getCommentCounts() {
		return commentCounts;
	}

	public void setCommentCounts(int commentCounts) {
		this.commentCounts = commentCounts;
	}
}
