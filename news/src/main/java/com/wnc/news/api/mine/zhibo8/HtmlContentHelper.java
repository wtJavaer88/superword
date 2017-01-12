package com.wnc.news.api.mine.zhibo8;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
			Elements select = documentResult.select(html_class);
			for (Element element : select) {
				resetAttr(element, "a", "href");
				resetAttr(element, "img", "src");
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
	private void resetAttr(Element element, String tag, String attrName) {
		Elements selectA = element.select(tag);
		if (selectA.size() > 0) {
			for (Element element2 : selectA) {
				element2.attr(attrName, element2.absUrl(attrName));
			}
		}
	}
}
