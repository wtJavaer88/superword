package com.wnc.superword.manage.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.mine.zhibo8.NewsExtract;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.pojo.zb8.Zb8NewsAdapter;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.service.CommentService;

@Component
public class NewsSchedule {

	Logger logger = Logger.getLogger(NewsSchedule.class);

	/**
	 * 每隔10分钟跟新索引数据
	 */
	@Scheduled(cron = "0 0 7-23 * * *")
	public void doJob() {
		try {
			logger.info("缓存新闻启动!");
			news(1);
			logger.info("缓存评论启动!");
			comments(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ArticleService articleService;
	@Autowired
	CommentService commentService;
	@Autowired
	ArticleCommentsTask articleCommentsTask;

	public void news(int i) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			String today = BasicDateUtil.getCurrentDateTimeString().substring(0, 10);
			List<Zb8News> nbaNewsByDay = new NewsExtract().getNBANewsBeforeDay(today, i);
			List<Zb8News> zuqiuNewsByDay = new NewsExtract().getZuqiuNewsBeforeDay(today, i);
			nbaNewsByDay.addAll(zuqiuNewsByDay);

			List<Article> articlesFromZb8 = Zb8NewsAdapter.getArticlesFromZb8(nbaNewsByDay);
			for (Article article : articlesFromZb8) {
				System.out.println("文章网址:" + article.getUrl());
				if (articleService.isExist(article.getUrl())) {
					continue;
				}
				try {
					articleService.decorateArticle(article);
					articleService.save(article);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
	}

	public void comments(int i) {
		int page = 1;
		final int rows = 100;
		try {
			while (true) {
				PageInfo<Article> queryList = articleService.queryLately(page, rows, i);
				if (queryList == null || queryList.getSize() == 0) {
					break;
				}
				articleCommentsTask.doTask(queryList.getList(), articleService, commentService);
				page++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
