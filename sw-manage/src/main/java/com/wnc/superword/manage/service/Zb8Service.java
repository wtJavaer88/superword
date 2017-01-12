package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.mine.zhibo8.HtmlContentHelper;
import com.wnc.news.api.mine.zhibo8.NewsExtract;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.manage.util.NewsWordsAnalyse;

@Service
public class Zb8Service {
	@Autowired
	NewsWordsAnalyse newsWordsAnalyse;

	public Zb8News findNews(String from_url) throws Exception {
		List<Zb8News> nbaNewsByDay = new NewsExtract().getNBANewsByDay(BasicDateUtil.getCurrentDateString());
		Zb8News news = new Zb8News();
		for (Zb8News zb8News : nbaNewsByDay) {
			if (zb8News.getFrom_url().equals(from_url)) {
				new HtmlContentHelper().initHtmlContent(zb8News);
				news = zb8News;
				System.out.println(news.getUrl());
				newsWordsAnalyse.decorateZb8News(news);
				break;
			}
		}
		return news;
	}

}