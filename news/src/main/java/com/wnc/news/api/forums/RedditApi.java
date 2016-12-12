package com.wnc.news.api.forums;

import java.util.List;

import org.jsoup.nodes.Element;

import com.wnc.news.api.common.AbstractForumsHtmlPicker;
import com.wnc.news.api.common.Config;
import com.wnc.news.api.common.ForumsApi;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.website.WebSite;
import com.wnc.news.website.WebSiteUtil;

public class RedditApi extends AbstractForumsHtmlPicker implements ForumsApi {
	WebSite webSite;
	String type;
	/**
	 * 重复检查
	 */
	boolean reflictCheck = false;

	public RedditApi(final String type) {
		this.type = type;
		if (type.equalsIgnoreCase(Config.getInstance().getBaskModelName())) {
			reflictCheck = true;
			webSite = WebSiteUtil.getRedditNBA();
		} else if (type.equalsIgnoreCase(Config.getInstance().getSoccModelName())) {
			reflictCheck = true;
			webSite = WebSiteUtil.getRedditSoccer();
		} else if (type.equalsIgnoreCase("san-antonio-spurs")) {
			webSite = WebSiteUtil.getReddit_Spurs();
		} else if (type.equalsIgnoreCase("golden-state-warriors")) {
			webSite = WebSiteUtil.getReddit_Warrior();
		} else if (type.equalsIgnoreCase("arsenal")) {
			webSite = WebSiteUtil.getReddit_Arsenal();
		}
	}

	@Override
	protected boolean setBaseLimit(NewsInfo newsInfo) {
		return true;
	}

	@Override
	protected NewsInfo getBaseNewsInfo(Element mainDiv) {
		NewsInfo newsInfo = null;
		try {
			newsInfo = new NewsInfo();
			newsInfo.setWebsite(webSite);
			newsInfo.addKeyWord(type);
			Element aElement = mainDiv.select("a").first();
			String title = aElement.text();
			newsInfo.setTitle(title);
			newsInfo.setDate(mainDiv.select("time").last().attr("datetime"));
			String commentHref = mainDiv.select("a[data-event-action=comments]").first().absUrl("href");
			newsInfo.setUrl(commentHref);
		} catch (Exception e) {
			e.printStackTrace();
			newsInfo = null;
		}
		return newsInfo;
	}

	@Override
	public String getPage(int i) {
		return String.format(webSite.getFormat(), i);
	}

	@Override
	public List<NewsInfo> getAllNewsWithContent() {
		return getAllNews(webSite);
	}

	public NewsInfo getNewsFromUrl(String url) {
		try {
			return getNewsFromUrl(webSite, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
