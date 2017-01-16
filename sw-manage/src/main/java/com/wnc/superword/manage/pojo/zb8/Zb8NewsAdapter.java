package com.wnc.superword.manage.pojo.zb8;

import com.wnc.news.api.mine.zhibo8.Zb8News;

public class Zb8NewsAdapter {

	public static Article getArticleFromZb8(Zb8News zb8News) {
		Article article = new Article();
		article.setChsContent(zb8News.getChs_content());
		article.setEngContent(zb8News.getEng_content());
		article.setCreateTime(zb8News.getCreate_time());
		article.setDay(zb8News.getDay());
		article.setFromUrl(zb8News.getFrom_url());
		article.setFromWebsite(0);
		article.setKeyword(zb8News.getKeyword());
		article.setNewsTime(zb8News.getNews_time());
		article.setSubText(zb8News.getSub_text());
		article.setThumbnail(zb8News.getThumbnail());
		article.setTitle(zb8News.getTitle());
		article.setUrl(zb8News.getUrl());
		article.setSportType(zb8News.getSport_type());
		return article;
	}

	public static Zb8News getZb8FromArticle(Article article) {
		Zb8News zb8News = new Zb8News();
		zb8News.setChs_content(article.getChsContent());
		zb8News.setEng_content(article.getEngContent());
		zb8News.setCreate_time(article.getCreateTime());
		zb8News.setDay(article.getDay());
		zb8News.setFrom_url(article.getFromUrl());
		zb8News.setKeyword(article.getKeyword());
		zb8News.setNews_time(article.getNewsTime());
		zb8News.setSub_text(article.getSubText());
		zb8News.setThumbnail(article.getThumbnail());
		zb8News.setTitle(article.getTitle());
		zb8News.setUrl(article.getUrl());
		zb8News.setSport_type(article.getSportType());
		return zb8News;
	}
}
