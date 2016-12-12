package com.wnc.superword.manage.util;

import com.wnc.basic.BasicNumberUtil;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.superword.manage.pojo.News;

public class NewsProxy {
	NewsInfo newsInfo;

	public NewsProxy(NewsInfo newsInfo) {
		this.newsInfo = newsInfo;
	}

	public News getRealNews() {
		News news = new News();
		news.setCetTopics(newsInfo.getCet_topics());
		news.setCommentCounts(news.getCommentCounts());
		news.setCreateTime(newsInfo.getCreate_time());
		news.setDate(newsInfo.getDate());
		news.setHeadPic(newsInfo.getHead_pic());
		news.setHtmlContent(newsInfo.getHtml_content());
		news.setKeywords(newsInfo.getKeywords().toString());
		news.setSubText(newsInfo.getSub_text());
		news.setTitle(newsInfo.getTitle());
		news.setTopicCounts(newsInfo.getTopic_counts());
		news.setUrl(newsInfo.getUrl());
		news.setWebsiteId(BasicNumberUtil.getNumber(newsInfo.getWebsite().getDb_id()));
		return news;
	}
}
