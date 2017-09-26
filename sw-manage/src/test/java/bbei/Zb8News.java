package bbei;

import com.wnc.news.api.common.NewsInfo;

public class Zb8News extends NewsInfo {
	private long id;
	private String url;
	private String from_name;
	private int from_website;
	private String from_url;
	private String title;
	private String sub_text;
	private String day;
	private String keyword;
	private String chs_content;
	private String eng_content;
	private String thumbnail;
	private String news_time;
	private String create_time;
	private String update_time;
	private int sport_type;
	private int comments;
	private int hotComments;

	@Override
	public String getHtml_content() {
		return this.eng_content;
	}

	@Override
	public String getHead_pic() {
		return thumbnail;
	}

	public int getDb_id() {
		return (int) id;
	}

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

	public String getSub_text() {
		return sub_text;
	}

	public void setSub_text(String sub_text) {
		this.sub_text = sub_text;
	}

	public int getSport_type() {
		return sport_type;
	}

	public void setSport_type(int sport_type) {
		this.sport_type = sport_type;
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

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public int getFrom_website() {
		return from_website;
	}

	public void setFrom_website(int from_website) {
		this.from_website = from_website;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
