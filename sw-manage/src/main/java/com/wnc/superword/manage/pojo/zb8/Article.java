package com.wnc.superword.manage.pojo.zb8;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "article")
public class Article {
	// 字段需要遵循驼峰原则
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select max(ID)+1 from ARTICLE")
	private Long id;
	private String url;
	private int fromWebsite;

	private String fromUrl;

	private String title;

	private String subText;
	private String day;
	private String keyword;
	private String chsContent;
	private String engContent;

	private String thumbnail;
	private String newsTime;
	private String createTime;
	private String updateTime;
	private int sportType;
	private int comments;
	private int hotComments;
	// 是否有翻译,1是0否
	@Transient
	private int translated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getFromWebsite() {
		return fromWebsite;
	}

	public void setFromWebsite(int fromWebsite) {
		this.fromWebsite = fromWebsite;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubText() {
		return subText;
	}

	public void setSubText(String subText) {
		this.subText = subText;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getChsContent() {
		return chsContent;
	}

	public void setChsContent(String chsContent) {
		this.chsContent = chsContent;
	}

	public String getEngContent() {
		return engContent;
	}

	public void setEngContent(String engContent) {
		this.engContent = engContent;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getSportType() {
		return sportType;
	}

	public void setSportType(int sportType) {
		this.sportType = sportType;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getHotComments() {
		return hotComments;
	}

	public void setHotComments(int hotComments) {
		this.hotComments = hotComments;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getTranslated() {
		return translated;
	}

	public void setTranslated(int translated) {
		this.translated = translated;
	}

}
