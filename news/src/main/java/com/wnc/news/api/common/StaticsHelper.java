package com.wnc.news.api.common;

import com.wnc.news.website.WebSiteUtil;

public class StaticsHelper {
	public static boolean isSoccerTeam(String team) {
		return true;
	}

	public static boolean isTeam(String str) {
		if (str.equalsIgnoreCase("san-antonio-spurs") || str.equalsIgnoreCase("golden-state-warriors")) {
			return true;
		}
		return true;
	}

	public static boolean isWebSite(String str) {
		return WebSiteUtil.getBasketballInsiders().getName().equalsIgnoreCase(str)
				|| WebSiteUtil.getRealGm().getName().equalsIgnoreCase(str)
				|| WebSiteUtil.getSkySports().getName().equalsIgnoreCase(str)
				|| WebSiteUtil.getSquawka().getName().equalsIgnoreCase(str)
				|| WebSiteUtil.getRedditNBA().getName().equalsIgnoreCase(str);
	}

	public static String getWebSiteId(String str) {
		if (WebSiteUtil.getBasketballInsiders().getName().equalsIgnoreCase(str)) {
			return WebSiteUtil.getBasketballInsiders().getDb_id();
		}
		if (WebSiteUtil.getRealGm().getName().equalsIgnoreCase(str)) {
			return WebSiteUtil.getRealGm().getDb_id();
		}
		if (WebSiteUtil.getSkySports().getName().equalsIgnoreCase(str)) {
			return WebSiteUtil.getSkySports().getDb_id();
		}
		if (WebSiteUtil.getSquawka().getName().equalsIgnoreCase(str)) {
			return WebSiteUtil.getSquawka().getDb_id();
		}
		if (WebSiteUtil.getRedditNBA().getName().equalsIgnoreCase(str)) {
			return WebSiteUtil.getRedditNBA().getDb_id();
		}
		return "-1";
	}
}
