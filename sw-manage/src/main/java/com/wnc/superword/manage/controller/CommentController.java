package com.wnc.superword.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.service.CommentService;
import com.wnc.superword.manage.task.CommentThread;
import com.wnc.utils.EasyUIResult;

@RequestMapping("comments")
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/hot")
	public ResponseEntity<EasyUIResult> view(Model model, @RequestParam(value = "id") Long article_id) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			if (!commentService.isExist(article_id)) {
				System.out.println("查找:");
				Article article = articleService.queryById(article_id);
				// Zb8CommentsAnalyseTool tool = new
				// Zb8CommentsAnalyseTool(article.getUrl());
				// article.setComments(tool.getAllCommentCount());
				// article.setHotComments(tool.getHotCommentCount());
				// article.setUpdateTime(BasicDateUtil.getCurrentDateTimeString());
				// if (tool.getHotCommentCount() > 0 &&
				// tool.getAllCommentCount() > 0) {
				// List<Comment> top5Comments = tool.getTop5Comments(5);
				// articleService.updateComments(article, top5Comments,
				// commentService);
				// }
				new CommentThread(article, articleService, commentService).run();
			}
			EasyUIResult easyUIResult = new EasyUIResult(commentService.articleHotCount(article_id),
					commentService.queryByArticle(article_id));
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
