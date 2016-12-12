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

public abstract class AbstractForumsHtmlPicker {
	Logger log = Logger.getLogger(AbstractForumsHtmlPicker.class);
	protected int MAX_PAGES = 2;
	public static final String SPlIT_LINE = "------------------------------";
	private static final int MAX_CONTENT_SIZE = 30000;

	protected List<NewsInfo> getAllNews(WebSite website) {
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		Document doc = null;
		for (int i = getFromPage(); i < getFromPage() + getMaxPages(); i++) {
			String page = "";
			try {
				page = getPage(i);
				log.info("分页:" + page);
				doc = com.wnc.utils.JsoupHelper.getDocumentResult(page);
				if (doc != null) {
					Elements news_divs = doc.select(website.getMain_div());
					for (Element mainDiv : news_divs) {
						NewsInfo newsInfo = getBaseNewsInfo(mainDiv);
						if (setBaseLimit(newsInfo)) {
							NewsInfo t_info = getNewsFromUrl(website, newsInfo.getUrl());
							if (setMoreLimit(t_info)) {
								newsInfo.setHtml_content(t_info.getHtml_content());
								newsInfo.setWebsite(t_info.getWebsite());
								newsInfo.setComment_counts(t_info.getComment_counts());
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

	protected boolean setBaseLimit(NewsInfo newsInfo) {
		return true;
	}

	protected boolean setMoreLimit(NewsInfo t_info) {
		return t_info.getComment_counts() > 20 && t_info.getHtml_content() != null
				&& t_info.getHtml_content().length() > 200;
	}

	protected NewsInfo getNewsFromUrl(WebSite webSite, String url) throws Exception {
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setUrl(url);
		newsInfo.setWebsite(webSite);
		newsInfo.setCreate_time(BasicDateUtil.getCurrentDateTimeString());
		Document documentResult = JsoupHelper.getDocumentResult(url);
		newsInfo.setTitle(documentResult.title());
		Elements select = documentResult.select(webSite.getNews_class());
		newsInfo.setComment_counts(select.size());

		String content = "";
		int i = 1;
		for (Element element : select) {
			content += element.toString();
			if (i % 10 == 0) {
				content += "</br>" + SPlIT_LINE + (i++) + "</br>";
			} else {
				content += "</br>" + SPlIT_LINE + "</br>";
			}
			if (content.length() > MAX_CONTENT_SIZE) {
				break;
			}
		}
		newsInfo.setHtml_content(content);
		// System.out.println("内容长度:" + content.length());

		return newsInfo;
	}

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

	protected boolean hasReachOldLine(NewsInfo newsInfo) {
		return false;
	}

	protected abstract NewsInfo getBaseNewsInfo(Element mainDiv);
}
