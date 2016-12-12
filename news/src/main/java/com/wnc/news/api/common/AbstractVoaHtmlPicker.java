package com.wnc.news.api.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.news.website.WebSite;
import com.wnc.utils.JsoupHelper;

import voa.VoaNewsInfo;

public abstract class AbstractVoaHtmlPicker {
	Logger log = Logger.getLogger(AbstractVoaHtmlPicker.class);
	protected int MAX_PAGES = 2;
	public static final String SPlIT_LINE = "------------------------------";
	private static final int MAX_CONTENT_SIZE = 30000;

	protected List<VoaNewsInfo> getAllNews(WebSite website) {
		List<VoaNewsInfo> list = new ArrayList<VoaNewsInfo>();
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
						VoaNewsInfo newsInfo = getBaseNewsInfo(mainDiv);
						if (isFresh(newsInfo)) {
							VoaNewsInfo t_info = getNewsFromUrl(website, newsInfo.getUrl());
							newsInfo.setHtml_content(t_info.getHtml_content());
							newsInfo.setWebsite(t_info.getWebsite());
							newsInfo.setComment_counts(t_info.getComment_counts());
							newsInfo.setMp3(t_info.getMp3());
							list.add(newsInfo);
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

	protected boolean isFresh(NewsInfo t_info) {
		return t_info.getComment_counts() > 20 && t_info.getHtml_content() != null
				&& t_info.getHtml_content().length() > 200;
	}

	protected abstract VoaNewsInfo getNewsFromUrl(WebSite webSite, String url) throws Exception;

	public void setMaxPages(int max) {
		if (max >= 0) {
			MAX_PAGES = max;
		}
	}

	public abstract String getPage(int i);

	protected int getFromPage() {
		return 1;
	}

	protected int getMaxPages() {
		return MAX_PAGES;
	}

	protected abstract VoaNewsInfo getBaseNewsInfo(Element mainDiv);
}
