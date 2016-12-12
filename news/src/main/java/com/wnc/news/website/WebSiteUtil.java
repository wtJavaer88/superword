package com.wnc.news.website;

public class WebSiteUtil
{

	private WebSiteUtil()
	{

	}

	public static WebSite getRedditSoccer()
	{
		return getReddit("soccer");
	}

	private static WebSite getReddit(String type)
	{
		WebSite site;
		site = new WebSite();
		site.setName("reddit");
		site.setDb_id("5");
		site.setNews_class(".content .md");
		site.setMain_div(".siteTable .entry");
		site.setFormat("https://www.reddit.com/r/" + type + "/?page=%d");
		return site;
	}

	public static WebSite getRedditNBA()
	{
		return getReddit("nba");
	}

	public static WebSite getReddit_Spurs()
	{
		return getReddit("NBASpurs");
	}

	public static WebSite getReddit_Warrior()
	{
		return getReddit("warriors");
	}

	public static WebSite getReddit_Arsenal()
	{
		return getReddit("Gunners");
	}

	public static WebSite getIyuba()
	{
		WebSite iyuba = new WebSite();

		iyuba = new WebSite();
		iyuba.setName("iyuba");
		iyuba.setDb_id("100");
		iyuba.setMain_div(".list-contents li");
		iyuba.setFormat("http://voa.iyuba.com/voachangsu_0_%d.html");
		return iyuba;
	}

	public static WebSite getRealGm()
	{
		WebSite realgm = new WebSite();

		realgm = new WebSite();
		realgm.setName("realgm");
		realgm.setDb_id("4");
		realgm.setNews_class(".postbody .content");
		realgm.setMain_div(".inner .row");
		realgm.setFormat("http://forums.realgm.com/boards/viewforum.php?f=6&start=%d");
		return realgm;
	}

	public static WebSite getSquawka()
	{
		WebSite squawka = new WebSite();

		squawka = new WebSite();
		squawka.setName("squawka");
		squawka.setDb_id("3");
		squawka.setNews_class(".entry-content p");
		squawka.setMain_div(".news-sub");
		squawka.setFormat("http://www.squawka.com/teams/%s/news?getitems=true&ajax=true&pg=%d");
		return squawka;
	}

	public static WebSite getSkySports()
	{
		WebSite skysports = new WebSite();

		skysports = new WebSite();
		skysports.setName("skysports");
		skysports.setDb_id("2");
		skysports.setNews_class(".article__body p");
		skysports.setMain_div(".news-list__body");
		skysports.setFormat("http://www.skysports.com/%s-news/more/%d");
		return skysports;
	}

	public static WebSite getBasketballInsiders()
	{
		WebSite basketballinsiders = new WebSite();

		basketballinsiders = new WebSite();
		basketballinsiders.setDb_id("1");
		basketballinsiders.setName("basketballinsiders");
		basketballinsiders.setNews_class("#content-area p");
		basketballinsiders.setMain_div(".infinite-post");
		basketballinsiders.setFormat("http://www.basketballinsiders.com/tag/%s/page/%d/");
		return basketballinsiders;
	}
}
