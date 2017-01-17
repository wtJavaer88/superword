package com.wnc.superword.manage.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.common.Comment;
import com.wnc.news.api.mine.zhibo8.comments_analyse.Zb8CommentsAnalyseTool;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.service.CommentService;

public class CommentThread implements Runnable {
	Article article;
	ArticleService articleService;
	CommentService commentService;
	Logger logger = Logger.getLogger(CommentThread.class);

	public CommentThread(Article article, ArticleService articleService, CommentService commentService) {
		this.article = article;
		this.articleService = articleService;
		this.commentService = commentService;
	}

	@Override
	public void run() {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Zb8CommentsAnalyseTool tool = new Zb8CommentsAnalyseTool(article.getUrl());
			article.setComments(tool.getAllCommentCount());
			article.setHotComments(tool.getHotCommentCount());
			article.setUpdateTime(BasicDateUtil.getCurrentDateTimeString());
			if (tool.getHotCommentCount() > 0 && tool.getAllCommentCount() > 0) {
				List<Comment> top5Comments = tool.getTop5Comments(5);
				articleService.updateComments(article, top5Comments, commentService);
			}
		} catch (Exception e) {
			logger.error(article.getId() + " 生成内容失败!", e);
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}

	}

}
