package com.wnc.news.api.forums;

import java.util.List;

import org.jsoup.nodes.Element;

import com.wnc.news.api.common.AbstractForumsHtmlPicker;
import com.wnc.news.api.common.DateUtil;
import com.wnc.news.api.common.ForumsApi;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.website.WebSite;
import com.wnc.news.website.WebSiteUtil;

public class RealGmApi extends AbstractForumsHtmlPicker implements ForumsApi
{

    WebSite webSite = WebSiteUtil.getRealGm();

    public RealGmApi()
    {

    }

    @Override
    protected int getFromPage()
    {
        return 0;
    }

    @Override
    protected NewsInfo getBaseNewsInfo(Element mainDiv)
    {
        NewsInfo newsInfo = null;
        try
        {
            newsInfo = new NewsInfo();

            Element dateDiv = mainDiv.select(".responsive-hide").first();
            newsInfo.setDate(DateUtil.getDateFromRealGame(dateDiv.text()));
            Element titleDiv = mainDiv.select(".list-inner a").first();
            if (titleDiv != null)
            {
                String title = titleDiv.text();
                String url = titleDiv.absUrl("href");
                newsInfo.setTitle(title);
                newsInfo.setUrl(url);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            newsInfo = null;
        }
        return newsInfo;
    }

    @Override
    public String getPage(int i)
    {
        return String.format(webSite.getFormat(), i * 25);
    }

    @Override
    public List<NewsInfo> getAllNewsWithContent()
    {
        return getAllNews(webSite);
    }

    public NewsInfo getNewsFromUrl(String url)
    {
        try
        {
            return getNewsFromUrl(webSite, url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
