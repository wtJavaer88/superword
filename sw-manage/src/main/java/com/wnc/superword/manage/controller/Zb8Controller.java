package com.wnc.superword.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.mine.zhibo8.NewsExtract;
import com.wnc.news.api.mine.zhibo8.NewsFilter;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.manage.service.Zb8Service;
import com.wnc.utils.EasyUIResult;

@RequestMapping("zb8")
@Controller
public class Zb8Controller {
	@Autowired
	private Zb8Service zb8Service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EasyUIResult> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		try {
			List<Zb8News> nbaNewsByDay = new NewsExtract().getNBANewsByDay(BasicDateUtil.getCurrentDateString());
			for (Zb8News zb8News : nbaNewsByDay) {
				System.out.println(zb8News.getThumbnail());
			}
			System.out.println("##########################\n");
			List<Zb8News> filterOutSide = NewsFilter.filterOutSide(nbaNewsByDay);
			List<Zb8News> filterOutSide2 = new ArrayList<Zb8News>();
			for (int i = (page - 1) * rows; i < page * rows && i < filterOutSide.size(); i++) {
				filterOutSide2.add(filterOutSide.get(i));
			}
			return ResponseEntity.ok(new EasyUIResult(filterOutSide.size(), filterOutSide2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "/view")
	public String view(Model model, @RequestParam(value = "from_url") String from_url) {
		Zb8News news;
		try {
			news = zb8Service.findNews(from_url);
			// System.out.println("getEng_content:" + news.getEng_content());
			model.addAttribute("message", news.getEng_content());
			model.addAttribute("message_chs", news.getChs_content());
			model.addAttribute("title", news.getTitle());
			model.addAttribute("head_pic", news.getThumbnail());
			return "news_viewer";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
