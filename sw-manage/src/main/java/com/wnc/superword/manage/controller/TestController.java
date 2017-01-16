package com.wnc.superword.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.common.Comment;
import com.wnc.news.api.mine.zhibo8.NewsExtract;
import com.wnc.news.api.mine.zhibo8.SportType;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.news.api.mine.zhibo8.comments_analyse.Zb8CommentsAnalyseTool;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.pojo.zb8.ArticleComment;
import com.wnc.superword.manage.pojo.zb8.CommentAdapter;
import com.wnc.superword.manage.pojo.zb8.Zb8NewsAdapter;
import com.wnc.superword.manage.service.ArticleService;
import com.wnc.superword.manage.service.CommentService;

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

	@Resource
	ArticleService articleService;
	@Resource
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

	@RequestMapping(value = "/comments")
	public String comments(Model model) throws Exception {
		try {
			int page = 1;
			final int rows = 100;
			while (true) {
				PageInfo<Article> queryList = articleService.queryList(page, rows);
				if (queryList.getSize() == 0) {
					break;
				}
				for (Article article : queryList.getList()) {
					Zb8CommentsAnalyseTool tool = new Zb8CommentsAnalyseTool(article.getUrl());
					System.out.println(
							article.getTitle() + " / " + tool.getHotCommentCount() + " / " + tool.getAllCommentCount());
					article.setComments(tool.getAllCommentCount());
					article.setHotComments(tool.getHotCommentCount());
					articleService.update(article);
					List<Comment> top5Comments = tool.getTop5Comments(5);
					for (Comment comment : top5Comments) {
						ArticleComment articleComment = CommentAdapter.getArticleComment(comment);
						articleComment.setArticleId(article.getId());
						articleComment.setCreateTime(BasicDateUtil.getCurrentDateTimeString());
						System.out.println(articleComment);
						try {
							commentService.save(articleComment);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				page++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "zb8";
	}

}