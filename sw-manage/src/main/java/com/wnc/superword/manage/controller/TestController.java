package com.wnc.superword.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import translate.site.baidu.BaiduWordTranslate;

@Controller
public class TestController {

	@RequestMapping(value = "/talk")
	public String talk(Model model) {
		return "talk";
	}

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

	@RequestMapping(value = "/test")
	public String test(Model model) throws Exception {
		Logger.getLogger(TestController.class).info("测试文件日志!");
		Logger.getLogger(TestController.class).error("测试文件日志!");
		return "zb8";
	}
}