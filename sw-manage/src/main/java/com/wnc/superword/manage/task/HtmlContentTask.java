package com.wnc.superword.manage.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.service.ArticleService;

@Component
public class HtmlContentTask {
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	public void doTask(List<Article> articles, ArticleService articleService, NewsWordsAnalyse newsWordsAnalyse) {
		ContentThread task;
		for (Article article : articles) {
			task = new ContentThread(article, articleService, newsWordsAnalyse);
			taskExecutor.execute(task);
		}
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
