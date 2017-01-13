package com.wnc.news.api.mine.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MirrorHtmlHandler implements HtmlHandler {

	@Override
	public Elements getContentElements(Element select, String html_class) {
		Elements elements = new Elements();
		Elements allElements = select.select("html_class").first().children();// 获取直接的子节点
		for (Element element : allElements) {
			String tag = element.tagName().toLowerCase();
			if (tag.equals("a")) {
				elements.add(element);
			}
			if (tag.equals("p") || tag.matches("h\\d+") || tag.equals("strong")) {
				if (element.text().trim().length() > 0) {
					elements.add(element);
				}
			}
			if (tag.equals("div") && (element.text().trim().length() > 0 || element.select("img").size() > 0)) {
				elements.add(element);
			}
		}
		return elements;
	}

}
