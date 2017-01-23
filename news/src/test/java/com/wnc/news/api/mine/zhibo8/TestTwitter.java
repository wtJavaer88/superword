package com.wnc.news.api.mine.zhibo8;

import org.junit.Test;

public class TestTwitter {
	// @Test
	public void t() {
		String url = "http://www.mirror.co.uk/sport/football/news/philippe-coutinho-feared-ankle-injury-9621866";
		url = "http://www.mirror.co.uk/sport/football/news/jurgen-klopp-ready-park-liverpool-9621930";
		String extractNewsContent = new HtmlContentHelper().extractNewsContent(url);
		System.out.println(extractNewsContent);
	}

	@Test
	public void t2() {
		String article = "<div class=\"image-gallery-placeholder\" data-gallery-id=\"9605061\">";
		String replaceAll = article.replaceAll("<a.*?(href=\".*?\").*?>", "<a $1>").replaceAll("<p.*?>", "<p>")
				.replaceAll("<div.*?>", "<div>");
		System.out.println(replaceAll);

	}

}
