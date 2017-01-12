package com.wnc.news.api.mine.zhibo8;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wnc.basic.BasicStringUtil;

public class WebSiteClassFactory {
	static Map<String, String> websites = new HashMap<String, String>();
	static {
		websites.put(".zhibo8.", "#signals p");// 直播吧
		websites.put(".skysports.", ".article__body");// 天空体育
		websites.put(".squawka.", ".entry-content");
		websites.put(".fourfourtwo.", ".node-content p");// 442
		websites.put(".goal.", ".article-text p");// 进球网
		websites.put(".bbc.", "#story-body p");// BBC
		websites.put(".dailymail.", "div [itemprop=articleBody] p[class=mol-para-with-font]");// 每日邮报
		websites.put(".arsenal.", "section[class=article-text] p");// 阿森纳官网
		websites.put(".cbssports.", "div [itemprop=articleBody] p");// 每日邮报
		websites.put(".nba.", ".field-name-body p");// NBA官网
		websites.put(".foxsports.", "div [itemprop=articleBody] p");// 福克斯体育
		websites.put(".yahoo.", "#Col1-0-ContentCanvas p");// 雅虎新闻
		websites.put(".palmbeachpost.", "article .story p");
		websites.put(".sportsmole.", "#article_body p");
		websites.put(".theguardian.", "div [itemprop=articleBody]");// 卫报
		websites.put(".si.", "div [itemprop=articleBody] p");// 体育画报
		websites.put(".theplayerstribune.", ".entry-content");// 球星看台
		websites.put(".hupu.", ".artical-content-read");// 虎扑
		websites.put(".dongqiudi.", ".detail div");// 懂球帝
	}

	public static String getHtmlClass(String url) throws MalformedURLException {
		if (BasicStringUtil.isNullString(url)) {
			return null;
		}
		String host = new java.net.URL(url).getHost();
		for (Entry<String, String> entry : websites.entrySet()) {
			if (host.toLowerCase().contains(entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}
}