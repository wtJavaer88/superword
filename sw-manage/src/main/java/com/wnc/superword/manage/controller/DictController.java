package com.wnc.superword.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnc.superword.manage.service.DictService;

import translate.site.iciba.CibaWordTranslate;
import word.DicWord;

@RequestMapping("dict")
@Controller
public class DictController {

	@Autowired
	private DictService dictService;

	@RequestMapping(value = "/hello")
	public String HelloWorld(Model model) {
		try {
			model.addAttribute("message", dictService.findCETWords("despite seal of upgrade"));
			return "HelloWorld";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/mean")
	public ResponseEntity<DicWord> wordMean(Model model, @RequestParam(value = "word") String word) {
		DicWord findWord = null;
		try {
			String cn_mean = "";
			findWord = dictService.findWord(word);
			if (findWord != null) {
				cn_mean = findWord.getCn_mean();
			} else {
				findWord = new DicWord();
				cn_mean = new CibaWordTranslate(word).getBasicInfo();
			}
			findWord.setCn_mean("<p>" + cn_mean.replaceAll("([a-zA-Z]+\\. )", "</p><p>$1") + "</p>");
			return ResponseEntity.status(HttpStatus.OK).body(findWord);
		} catch (Exception e) {
			findWord = new DicWord();
			findWord.setCn_mean("<font size=\"10\" color=\"red\">字典找不到单词,请直接在线查询!</font>");
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(findWord);
	}
}
