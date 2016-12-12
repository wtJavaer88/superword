package com.wnc.news.api.common;

import java.util.HashSet;
import java.util.Set;

import com.wnc.news.website.WebSite;

public class DiscussInfo
{
	WebSite website;
	String title;
	String url;
	String sub_text;
	String date;
	String head_pic;
	String html_content;
	String db_id;
	String cet_topics;
	Set<String> keywords = new HashSet<String>();

	public boolean addKeyWord(String keyword)
	{
		return keywords.add(keyword);
	}

	public boolean removeKeyWord(String keyword)
	{
		return keywords.remove(keyword);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getSub_text()
	{
		return sub_text;
	}

	public void setSub_text(String sub_text)
	{
		this.sub_text = sub_text;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getHead_pic()
	{
		return head_pic;
	}

	public void setHead_pic(String head_pic)
	{
		this.head_pic = head_pic;
	}

	public WebSite getWebsite()
	{
		return website;
	}

	public void setWebsite(WebSite website)
	{
		this.website = website;
	}

	public Set<String> getKeywords()
	{
		return keywords;
	}

	public void setKeywords(Set<String> keywords)
	{
		this.keywords = keywords;
	}

	public String getHtml_content()
	{
		return html_content;
	}

	public void setHtml_content(String html_content)
	{
		this.html_content = html_content;
	}

	public String getDb_id()
	{
		return db_id;
	}

	public void setDb_id(String db_id)
	{
		this.db_id = db_id;
	}

	public String getCet_topics()
	{
		return cet_topics;
	}

	public void setCet_topics(String cet_topics)
	{
		this.cet_topics = cet_topics;
	}
}
