package com.wnc.news.api.mine.zhibo8;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.news.api.mine.handler.HtmlHandler;
import com.wnc.utils.JsoupHelper;

public class HtmlContentHelper {
	public void initHtmlContent(Zb8News news) {
		initEngHtmlContent(news);
		initChsHtmlContent(news);
	}

	public void initChsHtmlContent(Zb8News news) {
		news.setChs_content(extractNewsContent(news.getUrl()));
	}

	public void initEngHtmlContent(Zb8News news) {
		news.setEng_content(extractNewsContent(news.getFrom_url()));
	}

	public String extractNewsContent(String url) {
		try {
			String html_class = WebSiteClassFactory.getHtmlClass(url);
			if (html_class == null) {
				return null;
			}

			Document documentResult = JsoupHelper.getDocumentResult(url);
			HtmlHandler customHandler = WebSiteClassFactory.getCustomHandler(url);
			Elements select = customHandler.getContentElements(documentResult, html_class);
			for (Element element : select) {
				resetLinkAttr(element, "a", "href");
				resetLinkAttr(element, "img", "src");
			}

			return select.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将属性的链接地址变成绝对地址
	 * 
	 * @param element
	 * @param tag
	 * @param attrName
	 */
	private void resetLinkAttr(Element element, String tag, String attrName) {
		Elements selectA = element.select(tag);
		if (selectA.size() > 0) {
			for (Element element2 : selectA) {
				element2.attr(attrName, element2.absUrl(attrName));
			}
		}
	}
}
