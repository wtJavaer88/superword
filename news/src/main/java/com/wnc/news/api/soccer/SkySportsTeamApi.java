package com.wnc.news.api.soccer;

import java.util.List;

import org.jsoup.nodes.Element;

import com.wnc.news.api.common.AbstractNewsHtmlPicker;
import com.wnc.news.api.common.DateUtil;
import com.wnc.news.api.common.NewsContentService;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.api.common.TeamApi;
import com.wnc.news.website.WebSite;
import com.wnc.news.website.WebSiteUtil;

public class SkySportsTeamApi implements TeamApi
{
    String team;
    int MAX_PAGES = 5;
    WebSite webSite = WebSiteUtil.getSkySports();
    AbstractNewsHtmlPicker htmlPicker;

    public SkySportsTeamApi(final String team)
    {
        this.team = team;
        htmlPicker = new AbstractNewsHtmlPicker()
        {
            @Override
            protected NewsInfo getNewsInfo(Element mainDiv)
            {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.addKeyWord(team);
                newsInfo.setWebsite(webSite);

                try
                {
                    Element imgDiv = mainDiv.previousElementSibling()
                            .select("div img").first();
                    newsInfo.setHead_pic(imgDiv.absUrl("data-src").replaceAll(
                            "#\\{(\\d+)\\}", "$1"));

                    Element dateDiv = mainDiv.select(".label__timestamp")
                            .first();
                    if (dateDiv != null)
                    {
                        newsInfo.setDate(DateUtil.getDateFromSkeySport(dateDiv
                                .text()));
                    }

                    Element titleDiv = mainDiv.select(".news-list__headline a")
                            .first();
                    if (titleDiv != null)
                    {
                        String title = titleDiv.text();
                        newsInfo.setTitle(title);
                        newsInfo.setUrl(titleDiv.absUrl("href"));
                    }
                    // news-sub-text
                    Element subTextDiv = mainDiv.select(".news-list__snippet")
                            .first();
                    if (subTextDiv != null)
                    {
                        String text = subTextDiv.text();
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

            @Override
            protected int getMaxPages()
            {
                return MAX_PAGES;
            }

            @Override
            protected int getFromPage()
            {
                return 1;
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
