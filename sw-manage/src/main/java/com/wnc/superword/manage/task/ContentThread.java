package com.wnc.superword.manage.task;

import org.apache.log4j.Logger;

import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.mine.zhibo8.HtmlContentHelper;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.pojo.zb8.Zb8NewsAdapter;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.util.NewsWordsAnalyse;

public class ContentThread implements Runnable {
	Article article;
	ArticleService articleService;
	NewsWordsAnalyse newsWordsAnalyse;
	Logger logger = Logger.getLogger(ContentThread.class);

	public ContentThread(Article article, ArticleService articleService, NewsWordsAnalyse newsWordsAnalyse) {
		this.article = article;
		this.articleService = articleService;
		this.newsWordsAnalyse = newsWordsAnalyse;
	}

	@Override
	public void run() {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Zb8News zb8FromArticle = Zb8NewsAdapter.getZb8FromArticle(article);
			HtmlContentHelper htmlContentHelper = new HtmlContentHelper();
			htmlContentHelper.initEngHtmlContent(zb8FromArticle);
			String eng_content = zb8FromArticle.getEng_content();
			if (eng_content != null && eng_content.trim().length() > 10) {
				newsWordsAnalyse.decorateZb8News(zb8FromArticle);
				htmlContentHelper.initChsHtmlContent(zb8FromArticle);
				article.setChsContent(zb8FromArticle.getChs_content());
				article.setEngContent(zb8FromArticle.getEng_content());
				article.setUpdateTime(BasicDateUtil.getCurrentDateTimeString());
				articleService.updateContent(article);
			}
		} catch (Exception e) {
			logger.error(article.getId() + " 生成内容失败!", e);
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}

	}

}
