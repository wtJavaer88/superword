package com.wnc.news.api.mine.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SkyHtmlHandler implements HtmlHandler {

	@Override
	public Elements getContentElements(Element select, String html_class) {
		Elements allElements = select.select(html_class).first().children();// 获取直接的子节点
		for (Element element : allElements) {
			Elements imgs = element.select("img");
			for (Element element2 : imgs) {
				element2.attr("src", element2.absUrl("data-src"));
				element2.removeAttr("data-src");
			}
		}
		return allElements;
	}

}
