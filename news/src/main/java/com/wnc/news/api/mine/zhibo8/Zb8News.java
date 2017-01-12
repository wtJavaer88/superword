package com.wnc.news.api.mine.zhibo8;

public class Zb8News {
	private long id;
	private String url;
	private String from_url;
	private String title;
	private String day;
	private String keyword;
	private String chs_content;
	private String eng_content;
	private String thumbnail;
	private String news_time;
	private String create_time;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFrom_url() {
		return from_url;
	}

	public void setFrom_url(String from_url) {
		this.from_url = from_url;
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

	public String getChs_content() {
		return chs_content;
	}

	public void setChs_content(String chs_content) {
		this.chs_content = chs_content;
	}

	public void setEng_content(String eng_content) {
		this.eng_content = eng_content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEng_content() {
		return eng_content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getNews_time() {
		return news_time;
	}

	public void setNews_time(String news_time) {
		this.news_time = news_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
