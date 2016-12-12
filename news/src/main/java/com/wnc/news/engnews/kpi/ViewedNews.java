package com.wnc.news.engnews.kpi;

import com.wnc.news.api.common.NewsInfo;

public class ViewedNews extends NewsInfo
{
    private int view_duration;
    private String view_time;

    public int getView_duration()
    {
        return view_duration;
    }

    public void setView_duration(int view_duration)
    {
        this.view_duration = view_duration;
    }

    public String getView_time()
    {
        return view_time;
    }

    public void setView_time(String view_time)
    {
        this.view_time = view_time;
    }
}
