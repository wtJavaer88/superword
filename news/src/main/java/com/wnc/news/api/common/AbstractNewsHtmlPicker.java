package com.wnc.news.api.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.basic.BasicDateUtil;
import com.wnc.news.website.WebSite;
import com.wnc.utils.JsoupHelper;

public abstract class AbstractNewsHtmlPicker {
	Logger log = Logger.getLogger(AbstractNewsHtmlPicker.class);
	String team = "";
	WebSite webSite;

	public List<NewsInfo> getAllNews(WebSite website, String team) {
		this.team = team;
		this.webSite = website;
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		Document doc = null;
		for (int i = getFromPage(); i < getFromPage() + getMaxPages(); i++) {
			String page = "";
			try {
				page = getPage(i);
				log.info("分页:" + page);
				doc = JsoupHelper.getDocumentResult(page);
				if (doc != null) {
					Elements news_divs = doc.select(website.getMain_div());
					for (Element mainDiv : news_divs) {
						NewsInfo newsInfo = getNewsInfo(mainDiv);
						if (newsInfo != null) {
							newsInfo.setCreate_time(BasicDateUtil.getCurrentDateTimeString());

							if (hasReachOldLine(newsInfo)) {
								// return list;
							} else {
								list.add(newsInfo);
							}
						}
					}
				} else {
					log.error("连接" + page + "失败.");
				}
			} catch (Exception e) {
				log.error("解析网页" + page + "时出错.", e);
			}
		}
		return list;
	}

	protected String getPage(int i) {
		return String.format(webSite.getFormat(), team, i);
	}

	protected abstract int getFromPage();

	protected abstract int getMaxPages();

	protected boolean hasReachOldLine(NewsInfo newsInfo) {
		return false;
	}

	protected abstract NewsInfo getNewsInfo(Element mainDiv);
}
