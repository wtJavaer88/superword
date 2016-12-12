package com.wnc.news.api.soccer;

import java.util.List;

import org.jsoup.nodes.Element;

import com.wnc.basic.BasicStringUtil;
import com.wnc.news.api.common.AbstractNewsHtmlPicker;
import com.wnc.news.api.common.DateUtil;
import com.wnc.news.api.common.NewsContentService;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.api.common.TeamApi;
import com.wnc.news.website.WebSite;
import com.wnc.news.website.WebSiteUtil;
import com.wnc.string.PatternUtil;

public class SquawkaTeamApi implements TeamApi
{
    String team;
    int MAX_PAGES = 3;
    WebSite webSite = WebSiteUtil.getSquawka();
    AbstractNewsHtmlPicker htmlPicker;

    public SquawkaTeamApi(final String team)
    {
        this.team = team;
        htmlPicker = new AbstractNewsHtmlPicker()
        {
            @Override
            protected int getFromPage()
            {
                return 0;
            }

            @Override
            protected int getMaxPages()
            {
                return MAX_PAGES;
            }

            @Override
            protected NewsInfo getNewsInfo(Element mainDiv)
            {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.addKeyWord(team);
                newsInfo.setWebsite(webSite);

                try
                {
                    Element dateDiv = mainDiv.select("div").get(1);
                    if (dateDiv != null)
                    {
                        newsInfo.setDate(DateUtil.getDateFromEngMonth(dateDiv
                                .text()));
                    }
                    Element imgDiv = mainDiv.select(".news-sub-image").first();
                    if (imgDiv != null)
                    {
                        String url = imgDiv.absUrl("href");
                        String img = imgDiv.select("img").first().absUrl("src");
                        newsInfo.setUrl(url);
                        newsInfo.setHead_pic(img);
                    }
                    Element titleDiv = mainDiv.select(".news-sub-heading")
                            .first();
                    if (titleDiv != null)
                    {
                        String title = titleDiv.text();
                        newsInfo.setTitle(title);
                    }
                    // news-sub-text
                    Element subTextDiv = mainDiv.select(".news-sub-text")
                            .first();
                    if (subTextDiv != null)
                    {
                        String text = PatternUtil.getFirstPatternGroup(
                                subTextDiv.text(), "(.*?\\[…\\])");
                        if (BasicStringUtil.isNullString(text))
                        {
                            text = subTextDiv.text();
                        }
                        newsInfo.setSub_text(text);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
                return newsInfo;
            }

        };
    }

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
