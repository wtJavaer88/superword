package com.wnc.news.api.mine.zhibo8;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wnc.basic.BasicStringUtil;
import com.wnc.news.api.mine.handler.HtmlHandler;
import com.wnc.news.api.mine.handler.MirrorHtmlHandler;
import com.wnc.news.api.mine.handler.NormalHtmlHandler;

public class WebSiteClassFactory {
	private static Map<String, String> websites = new HashMap<String, String>();
	static Map<String, Class<? extends HtmlHandler>> customHtmlHandlers = new HashMap<String, Class<? extends HtmlHandler>>();
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
		// 2017年1月12日添加
		websites.put(".football-italia.", "#story_content .content");// 意大利足球报
		websites.put(".marca.com/en/", "div [itemprop=articleBody] p");// 马卡报,只翻译有英文的
		websites.put(".mirror.", "div [itemprop=articleBody]");// 每日镜报
		// 2017年1月13日添加 篮球较多
		websites.put(".thescore.", ".article-contents article");
		websites.put(".realgm.", ".article-content .article-body");
		// websites.put("twitter.", ".js-tweet-text-container"); //推特暂时不可用,链接超时

		customHtmlHandlers.put(".mirror.", MirrorHtmlHandler.class);
	}

	public static Map<String, String> getWebsites() {
		return websites;
	}

	public static HtmlHandler getCustomHandler(String url) {
		HtmlHandler handler = new NormalHtmlHandler();

		for (Entry<String, Class<? extends HtmlHandler>> entry : customHtmlHandlers.entrySet()) {
			if (url.contains(entry.getKey())) {
				if (entry.getValue() != null) {
					try {
						handler = (HtmlHandler) entry.getValue().newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			}
		}
		return handler;
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