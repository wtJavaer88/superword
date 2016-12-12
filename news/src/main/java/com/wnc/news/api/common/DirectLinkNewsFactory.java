package com.wnc.news.api.common;

import com.wnc.news.api.forums.RealGmApi;
import com.wnc.news.api.forums.RedditApi;

public class DirectLinkNewsFactory {
	public static NewsInfo getNewsFromUrl(String url) {
		if (url.contains(".realgm.")) {
			return new RealGmApi().getNewsFromUrl(url);
		} else if (url.contains(".reddit.")) {
			if (url.contains("/soccer/")) {
				return new RedditApi(Config.getInstance().getSoccModelName()).getNewsFromUrl(url);
			} else {
				return new RedditApi(Config.getInstance().getBaskModelName()).getNewsFromUrl(url);
			}
		}
		return new ErrSiteNewsInfo();
	}
}
