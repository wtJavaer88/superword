package com.wnc.superword.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}