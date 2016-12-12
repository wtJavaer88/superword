package com.wnc.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RedditHelper {
	final static String SPlIT_LINE = "----------------";

	public static String read(String url) throws Exception {
		Document document = JsoupHelper.getDocumentResult(url);
		String title = document.title();
		Elements select = document.select(".content .md");
		String content = "<h1>" + title + "</h1></br>" + "</br>";
		for (Element element : select) {
			content += element.toString();
			content += "</br>" + SPlIT_LINE + "</br>";
		}
		return content;
	}
}
