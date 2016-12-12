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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wnc.news.api.common.DirectLinkNewsFactory;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.superword.manage.pojo.News;
import com.wnc.superword.manage.service.NewsService;
import com.wnc.superword.manage.util.NewsProxy;
import com.wnc.utils.EasyUIResult;

@RequestMapping("news")
@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	/**
	 * 新增内容
	 * 
	 * @param news
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveNews(News news) {
		try {
			news.setId(null);
			this.newsService.save(news);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 查询内容列表
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EasyUIResult> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		try {
			EasyUIResult easyUIResult = this.newsService.queryList(page, rows);
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteNews(@RequestParam(value = "ids", defaultValue = "0") List<Object> ids) {
		try {
			if (ids == null || ids.size() == 0) {
				// 请求参数有误
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			System.out.println("ids:" + ids.get(0));
			newsService.deleteByIds(News.class, "id", ids);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "add")
	public String users(Model model) {
		return "news_add";
	}

	@RequestMapping(value = "getSingleNews", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> getSingleNews(@RequestParam(value = "url") String url) {
		try {
			NewsInfo newsFromUrl = DirectLinkNewsFactory.getNewsFromUrl(url);
			System.out.println(newsFromUrl);
			if (newsFromUrl != null) {
				newsService.saveNews(new NewsProxy(newsFromUrl).getRealNews());
				return ResponseEntity.status(HttpStatus.OK).body(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-100);
	}

	@RequestMapping(value = "/view")
	public String view(Model model, @RequestParam(value = "id") Long id) {
		try {
			News news = newsService.queryById(id);
			model.addAttribute("message", news.getHtmlContent());
			model.addAttribute("title", news.getTitle());
			model.addAttribute("head_pic", news.getHeadPic());
			return "news_viewer";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
