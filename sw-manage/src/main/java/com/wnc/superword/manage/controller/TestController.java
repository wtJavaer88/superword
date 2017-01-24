package com.wnc.superword.manage.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.wnc.news.api.mine.zhibo8.NewsExtract;
import com.wnc.news.api.mine.zhibo8.SportType;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.cloud.xinxin.service.FootStepService;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.pojo.zb8.Zb8NewsAdapter;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.service.CommentService;
import com.wnc.superword.manage.task.ArticleCommentsTask;
import com.wnc.superword.manage.task.HtmlContentTask;
import com.wnc.superword.manage.task.NewsWordsAnalyse;

import translate.site.baidu.BaiduWordTranslate;

@Controller
public class TestController {

	@RequestMapping(value = "/hello")
	public String HelloWorld(Model model) {
		try {
			BaiduWordTranslate translate = new BaiduWordTranslate("hello");
			model.addAttribute("message", translate.getBasicInfo());
			return "HelloWorld";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/newslist")
	public String newslist(Model model) {
		return "news";
	}

	@RequestMapping(value = "/zb8news")
	public String zhibo8news(Model model) {
		return "zb8";
	}

	@Autowired
	ArticleService articleService;
	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/zhibo8db")
	public String zhibo8db(Model model) throws Exception {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			List<Zb8News> nbaNewsByDay = new NewsExtract().getNewsAfterDay("2016-01-01", SportType.NBA);
			List<Zb8News> zuqiuNewsByDay = new NewsExtract().getNewsAfterDay("2016-01-01", SportType.Zuqiu);
			nbaNewsByDay.addAll(zuqiuNewsByDay);
			for (Zb8News zb8News : nbaNewsByDay) {
				System.out.println("文章网址:" + zb8News.getUrl());
				articleService.save(Zb8NewsAdapter.getArticleFromZb8(zb8News));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return "zb8";
	}

	@RequestMapping(value = "/test")
	public String test(Model model) throws Exception {
		Logger.getLogger(TestController.class).info("测试文件日志!");
		Logger.getLogger(TestController.class).error("测试文件日志!");
		return "zb8";
	}

	@RequestMapping(value = "/comments")
	public ResponseEntity<Void> comments(Model model, @RequestParam(value = "i", defaultValue = "1") Integer i)
			throws Exception {
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
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@Autowired
	HtmlContentTask htmlContentTask;
	@Autowired
	ArticleCommentsTask articleCommentsTask;
	@Autowired
	NewsWordsAnalyse newsWordsAnalyse;

	// 解析文章中英文
	@RequestMapping(value = "/contents")
	public String contents(Model model) throws Exception {
		try {
			int page = 1;
			final int rows = 100;
			while (true) {
				List<Article> queryList = articleService.queryNullContent(page, rows);
				System.out.println("分页数据:" + page + "/" + queryList.size());
				if (queryList.size() == 0) {
					break;
				}
				// for (Article article : queryList) {
				// Zb8News zb8FromArticle =
				// Zb8NewsAdapter.getZb8FromArticle(article);
				// newsWordsAnalyse.decorateZb8News(zb8FromArticle);
				// article.setEngContent(zb8FromArticle.getEng_content());
				// articleService.updateContent(article);
				// }
				htmlContentTask.doTask(queryList, articleService, newsWordsAnalyse);
				page++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "zb8";
	}

	@Autowired
	FootStepService footStepService;

	@RequestMapping(value = "/fs")
	public String s(Model model) {
		System.out.println("#####");
		// System.out.println("#####" + footStepService.queryList(0, 10));
		System.out.println("#####");
		return "zb8";
	}

}