package com.wnc.news.api.mine.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 直接根据配置的选择器获取元素,不做任何干预
 * 
 * @author cpr216
 *
 */
public class NormalHtmlHandler implements HtmlHandler {

	@Override
	public Elements getContentElements(Element select, String html_class) {
		return select.select(html_class);
	}

}
