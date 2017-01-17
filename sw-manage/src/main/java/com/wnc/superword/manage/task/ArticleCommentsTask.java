package com.wnc.superword.manage.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.service.CommentService;

@Component
public class ArticleCommentsTask {
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	public void doTask(List<Article> articles, ArticleService articleService, CommentService commentService) {
		CommentThread task;
		for (Article article : articles) {
			task = new CommentThread(article, articleService, commentService);
			taskExecutor.execute(task);
		}
		// taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		// taskExecutor.shutdown();
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
