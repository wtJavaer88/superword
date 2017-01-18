package com.wnc.superword.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.ArticleComment;
import com.wnc.superword.manage.service.CommentService;

@RequestMapping("comments")
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/hot")
	public String view(Model model, @RequestParam(value = "id") Long id) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			List<ArticleComment> queryByArticle = commentService.queryByArticle(id);
			model.addAttribute("message", queryByArticle.toString().replace(",", "<br>").replaceAll("[\\[\\]]", ""));
			return "news_viewer";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}

		return null;
	}
}
