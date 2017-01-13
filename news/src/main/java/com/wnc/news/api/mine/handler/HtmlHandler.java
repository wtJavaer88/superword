package com.wnc.news.api.mine.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface HtmlHandler {
	public Elements getContentElements(Element doc, String html_class);
}
