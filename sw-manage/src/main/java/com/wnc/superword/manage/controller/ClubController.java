package com.wnc.superword.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnc.superword.manage.service.ClubService;
import com.wnc.utils.EasyUIResult;

@RequestMapping("club")
@Controller
public class ClubController {

	@Autowired
	private ClubService clubService;

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
			EasyUIResult easyUIResult = this.clubService.queryList(page, rows);
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "/keywords")
	public ResponseEntity<EasyUIResult> keywords() {
		try {
			List<String> list = new ArrayList<>();
			list.add("NBA");
			list.add("马刺");
			list.add("勇士");
			list.add("骑士");
			list.add("英超");
			list.add("阿森纳");
			list.add("曼城");
			list.add("利物浦");
			list.add("切尔西");
			list.add("曼联");
			list.add("西甲");
			list.add("皇马");
			list.add("马竞");
			list.add("巴塞罗那");
			list.add("德甲");
			list.add("拜仁");
			list.add("多特蒙德");
			return ResponseEntity.ok(new EasyUIResult(list.size(), list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "/cache")
	public ResponseEntity<Void> wordMean(@RequestParam(value = "team") String team,
			@RequestParam(value = "websitename") String websitename,
			@RequestParam(value = "maxpages", defaultValue = "10") int maxpages) {
		try {
			clubService.cacheTeamNews(team, websitename, maxpages);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
