package com.wnc.news.api.mine.zhibo8;

import java.util.ArrayList;
import java.util.List;

import com.wnc.basic.BasicStringUtil;

public class NewsFilter {
	public static List<Zb8News> filterOutSide(List<Zb8News> newsList) {
		List<Zb8News> ret = new ArrayList<Zb8News>();
		for (Zb8News zb8News : newsList) {
			String from_url = zb8News.getFrom_url();
			if (BasicStringUtil.isNotNullString(from_url) && !from_url.contains(".zhibo8.")) {
				ret.add(zb8News);
			}
		}
		return ret;
	}

	public static List<Zb8News> filterTranslated(List<Zb8News> newsList) {
		List<Zb8News> ret = new ArrayList<Zb8News>();
		for (Zb8News zb8News : newsList) {
			if (zb8News.getChs_content() != null && zb8News.getEng_content() != null) {
				ret.add(zb8News);
			}
		}
		return ret;
	}

	public static List<Zb8News> filterKeyWord(List<Zb8News> newsList, String keyword) {
		List<Zb8News> ret = new ArrayList<Zb8News>();
		for (Zb8News zb8News : newsList) {
			if (zb8News.getKeyword() != null && zb8News.getKeyword().contains(keyword)) {
				ret.add(zb8News);
			}
		}
		return ret;
	}

	public static List<Zb8News> filterWebSite(List<Zb8News> newsList, String website) {
		List<Zb8News> ret = new ArrayList<Zb8News>();
		for (Zb8News zb8News : newsList) {
			if (zb8News.getFrom_url() != null && zb8News.getFrom_url().contains(website)) {
				ret.add(zb8News);
			}
		}
		return ret;
	}

	public static List<Zb8News> filterTeamAndWebSite(List<Zb8News> newsList, String team, String website) {
		List<Zb8News> ret = filterKeyWord(newsList, team);
		ret = filterWebSite(ret, website);
		return ret;
	}
}
