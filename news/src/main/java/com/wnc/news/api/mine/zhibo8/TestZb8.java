package com.wnc.news.api.mine.zhibo8;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

public class TestZb8 {
	// @Test
	public void da() throws Exception {
		List<Zb8News> zuqiuNewsByDay = new NewsExtract().getZuqiuNewsByDay("2017-01-03");
		for (Zb8News zb8News : zuqiuNewsByDay) {
			if (zb8News.getChs_content() != null && zb8News.getEng_content() != null) {
				System.out.println(zb8News.getTitle() + " / " + zb8News.getFrom_url());
			}
		}
	}

	// @Test
	public void m() throws Exception {
		List<Zb8News> zuqiuNewsByYearMonth = new NewsExtract().getNewsByYearMonth(2017, 1, SportType.Zuqiu);
		List<Zb8News> filterTeam = NewsFilter.filterKeyWord(NewsFilter.filterTranslated(zuqiuNewsByYearMonth), "阿森纳");
		for (Zb8News zb8News : filterTeam) {
			System.out.println(zb8News.getTitle() + " / " + zb8News.getFrom_url());
		}
	}

	// @Test
	public void s() throws Exception {
		List<Zb8News> zuqiuNewsAfterDay = new NewsExtract().getNewsAfterDay("2017-01-01", SportType.Zuqiu);
		for (Zb8News zb8News : zuqiuNewsAfterDay) {
			System.out.println(zb8News.getTitle() + " / " + zb8News.getDay());
		}
	}

	// @Test
	public void dd() throws Exception {
		new NewsExtract().getNewsBetweenTwoDays("2016-12-29", "2017-01-02", SportType.Zuqiu);
	}

	// @Test
	public void dw() throws Exception {
		new NewsExtract().getNewsAfterDay("2016-12-29", SportType.Zuqiu);
	}

	// @Test
	public void testDm() throws Exception {
		String url = "http://www.arsenal.com/news/news-archive/20170103/xhaka-my-hopes-and-targets-for-2017";
		String extractNewsContent = new HtmlContentHelper().extractNewsContent(url);
		System.out.println(extractNewsContent);
		// Document documentResult = JsoupHelper.getDocumentResult(url);
		// System.out.println(documentResult.select("section[class=article-text]
		// p"));
	}

	// @Test
	public void testNba() throws Exception {
		List<Zb8News> nbaNewsByDay = new NewsExtract().getNBANewsByDay("2017-01-06");
		List<Zb8News> filterTeam = NewsFilter.filterWebSite(NewsFilter.filterKeyWord(nbaNewsByDay, "马刺"), "twitter");
		for (Zb8News zb8News : filterTeam) {
			System.out.println(zb8News.getTitle() + " / " + zb8News.getFrom_url());
		}
		System.out.println("##########################\n");
		List<Zb8News> filterOutSide = NewsFilter.filterOutSide(nbaNewsByDay);
		for (Zb8News zb8News : filterOutSide) {
			System.out.println("\nfrom:" + zb8News.getFrom_url());
			System.out.println("title:" + zb8News.getTitle() + "  url:" + zb8News.getUrl());
			new HtmlContentHelper().initEngHtmlContent(zb8News);
			String eng_content = zb8News.getEng_content();
			if (eng_content != null)
				System.out.println(eng_content.substring(0, eng_content.length() > 100 ? 100 : eng_content.length()));
			new HtmlContentHelper().initChsHtmlContent(zb8News);
			String chs_content = zb8News.getChs_content();
			System.out.println(chs_content.replaceAll("\\s", "").substring(0,
					chs_content.length() > 200 ? 200 : chs_content.length()));

		}
	}

	// @Test
	public void Cbs() {
		String url = "http://www.cbssports.com/nba/news/why-the-cleveland-cavaliers-and-kyle-korver-are-a-perfect-fit/";
		url = "http://www.nba.com/cavaliers/releases/korver-trade-170107";
		String extractNewsContent = new HtmlContentHelper().extractNewsContent(url);
		System.out.println(extractNewsContent);
	}

	// @Test
	public void chs() {
		String url = "http://news.zhibo8.cc/nba/2017-01-06/586f2c381d15d.htm";
		String extractNewsContent = new HtmlContentHelper().extractNewsContent(url);
		System.out.println(extractNewsContent);
	}

	// @Test
	public void tdomain() throws MalformedURLException {
		URL url = new java.net.URL("http://blog.csdn.net/zhujianlin1990");
		String host = url.getHost();// 获取主机名
		System.out.println("host:" + host);// 结果 blog.csdn.net
	}

	// @Test
	public void tGuard() {
		String url = "https://www.theguardian.com/football/2017/jan/10/48-team-world-cup-fifa-plan-2026";
		String extractNewsContent = new HtmlContentHelper().extractNewsContent(url);
		System.out.println("卫报:" + extractNewsContent);
	}

	@Test
	public void testYaoMing() throws Exception {
		List<Zb8News> newsByYearMonth = new NewsExtract().getNewsByYearMonth(2016, 7, SportType.NBA);
		for (Zb8News zb8News : newsByYearMonth) {
			if (zb8News.getTitle().contains("姚明")) {
				// System.out.println("find.." + zb8News.getTitle() +
				// zb8News.getUrl());
			}
			if (zb8News.getFrom_url().contains(".si.")) {
				System.out.println("find.." + zb8News.getTitle() + zb8News.getUrl() + " / " + zb8News.getFrom_url());
			}
		}
	}
}
