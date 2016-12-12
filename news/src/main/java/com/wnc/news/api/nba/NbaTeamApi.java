package com.wnc.news.api.nba;

import java.util.List;

import org.jsoup.nodes.Element;

import com.wnc.news.api.common.AbstractNewsHtmlPicker;
import com.wnc.news.api.common.NewsContentService;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.api.common.TeamApi;
import com.wnc.news.website.WebSite;
import com.wnc.news.website.WebSiteUtil;

public class NbaTeamApi implements TeamApi
{
    String team;
    int MAX_PAGES = 2;
    WebSite webSite = WebSiteUtil.getBasketballInsiders();
    AbstractNewsHtmlPicker htmlPicker;

    public NbaTeamApi(final String team)
    {
        this.team = team;
        htmlPicker = new AbstractNewsHtmlPicker()
        {
            @Override
            protected int getFromPage()
            {
                return 1;
            }

            @Override
            protected int getMaxPages()
            {
                return MAX_PAGES;
            }

            @Override
            protected NewsInfo getNewsInfo(Element mainDiv)
            {
                NewsInfo newsInfo = null;
                try
                {
                    newsInfo = new NewsInfo();
                    newsInfo.addKeyWord(team);
                    newsInfo.setWebsite(webSite);
                    Element imgDiv = mainDiv.select(".blog-layout1-img")
                            .first();
                    if (imgDiv != null)
                    {
                        String url = imgDiv.select("a").first().absUrl("href");
                        String img = imgDiv.select("img").first().absUrl("src");
                        newsInfo.setUrl(url);
                        newsInfo.setHead_pic(img);
                    }
                    Element titleDiv = mainDiv.select(".blog-layout1-text")
                            .first();
                    if (titleDiv != null)
                    {
                        String title = titleDiv.select("a").first().text();
                        newsInfo.setTitle(title);
                        newsInfo.setSub_text(titleDiv.select("p").first()
                                .text());
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    newsInfo = null;
                }
                return newsInfo;
            }

        };
    }

    @Override
    public void setMaxPages(int max)
    {
        if (max >= 1)
        {
            MAX_PAGES = max;
        }
    }

    @Override
    public List<NewsInfo> getAllNewsWithContent()
    {
        final NewsContentService newsContentService = new NewsContentService(
                htmlPicker.getAllNews(webSite, team));
        newsContentService.execute();
        return newsContentService.getResult();
    }
}
