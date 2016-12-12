package com.wnc.news.api.common;

import java.util.HashSet;
import java.util.Set;

import com.wnc.news.website.WebSite;

public class NewsInfo
{
    protected WebSite website;
    protected String title;
    protected String url;
    protected String sub_text;
    protected String date;
    protected String head_pic;
    protected String html_content;
    protected int db_id;
    protected String cet_topics;
    protected Set<String> keywords = new HashSet<String>();
    protected int topic_counts;
    protected int comment_counts;
    protected String create_time;

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

    @Override
    public String toString()
    {
        return "NewsInfo [website=" + website + ", title=" + title + ", url="
                + url + ", sub_text=" + sub_text + ", date=" + date
                + ", head_pic=" + head_pic + ", html_content=" + html_content
                + ", db_id=" + db_id + ", cet_topics=" + cet_topics
                + ", keywords=" + keywords + ", topic_counts=" + topic_counts
                + ", comment_counts=" + comment_counts + ", create_time="
                + create_time + "]";
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

    public int getDb_id()
    {
        return db_id;
    }

    public void setDb_id(int db_id)
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

    public int getTopic_counts()
    {
        return topic_counts;
    }

    public void setTopic_counts(int topic_counts)
    {
        this.topic_counts = topic_counts;
    }

    public int getComment_counts()
    {
        return comment_counts;
    }

    public void setComment_counts(int comment_counts)
    {
        this.comment_counts = comment_counts;
    }

    public String getCreate_time()
    {
        return create_time;
    }

    public void setCreate_time(String create_time)
    {
        this.create_time = create_time;
    }
}
