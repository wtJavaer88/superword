package com.wnc.news.api.common;

public class Club
{
    int league;
    String full_name;
    String short_name;
    String abbreviation;// 缩写
    String cn_name;
    String club_stats_url;
    String photo;
    int club_id;

    public int getLeague()
    {
        return league;
    }

    public void setLeague(int league)
    {
        this.league = league;
    }

    public String getShort_name()
    {
        return short_name;
    }

    public void setShort_name(String short_name)
    {
        this.short_name = short_name;
    }

    public String getCn_name()
    {
        return cn_name;
    }

    public void setCn_name(String cn_name)
    {
        this.cn_name = cn_name;
    }

    @Override
    public String toString()
    {
        return "Club [league=" + league + ", full_name=" + full_name
                + ", short_name=" + short_name + ", abbreviation="
                + abbreviation + ", cn_name=" + cn_name + ", club_stats_url="
                + club_stats_url + ", photo=" + photo + ", club_id=" + club_id
                + "]";
    }

    public String getFull_name()
    {
        return full_name;
    }

    public void setFull_name(String full_name)
    {
        this.full_name = full_name;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    public String getClub_stats_url()
    {
        return club_stats_url;
    }

    public void setClub_stats_url(String club_stats_url)
    {
        this.club_stats_url = club_stats_url;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public int getClub_id()
    {
        return club_id;
    }

    public void setClub_id(int club_id)
    {
        this.club_id = club_id;
    }
}
