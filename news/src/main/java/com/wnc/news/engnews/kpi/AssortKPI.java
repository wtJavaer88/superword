package com.wnc.news.engnews.kpi;

import java.util.ArrayList;
import java.util.List;

public class AssortKPI
{
    private int count;
    private String date;
    private List<CharSequence> contents = new ArrayList<CharSequence>();
    private KPI_TYPE kpi_type;

    public AssortKPI(int count, String date, KPI_TYPE kpi_type)
    {
        super();
        this.count = count;
        this.date = date;
        this.kpi_type = kpi_type;
    }

    public List<CharSequence> getContents()
    {
        return contents;
    }

    public void addContent(CharSequence content)
    {
        this.contents.add(content);
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public KPI_TYPE getkPI_TYPE()
    {
        return kpi_type;
    }

    public void setkPI_TYPE(KPI_TYPE kpi_type)
    {
        this.kpi_type = kpi_type;
    }
}
