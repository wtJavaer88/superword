package com.wnc.superword.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.utils.EasyUIResult;

@RequestMapping("zb8")
@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EasyUIResult> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "day", required = false) String day,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "is_translate", required = false) boolean is_translate) {
		try {
			if (keyword != null)
				keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
			System.out.println("kw:" + keyword + " day:" + day);
			List<Article> queryList = articleService.queryList(page, rows, day, keyword, is_translate);
			return ResponseEntity.ok(new EasyUIResult(articleService.getTotal(), queryList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "/view")
	public String view(Model model, @RequestParam(value = "id") Long id) {
		Article article;
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			article = articleService.queryById(id);
			if (article.getEngContent() == null && article.getChsContent() == null) {
				articleService.decorateArticle(article);
			}
			model.addAttribute("message", article.getEngContent());
			model.addAttribute("message_chs", article.getChsContent());
			model.addAttribute("title", article.getTitle());
			model.addAttribute("head_pic", article.getThumbnail());
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
