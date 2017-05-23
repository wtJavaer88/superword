package com.wnc.superword.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("mobile-page")
@Controller
public class MobileController {

	@RequestMapping(method = RequestMethod.GET)
	public String showMobile() {
		return "mobile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/2")
	public String showMobile2() {
		return "mobile2";
	}

}
