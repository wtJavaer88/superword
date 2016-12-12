package com.wnc.news.api.common;

import java.util.List;

import com.wnc.basic.BasicDateUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.string.PatternUtil;

public class DateUtil
{
    static String[] engMonthes =
    { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    public static String getDateFromEngMonth(String engDate)
    {
        String ret = "";
        String d = PatternUtil.getFirstPatternGroup(engDate, "(\\d+)");
        String y = PatternUtil.getLastPatternGroup(engDate, "(\\d+)");
        String month = PatternUtil.getLastPatternGroup(engDate, " (\\w+) ");
        int findMonth = -1;
        for (int i = 0; i < engMonthes.length; i++)
        {
            if (engMonthes[i].toLowerCase().startsWith(month.toLowerCase()))
            {
                findMonth = i + 1;
                break;
            }
        }
        if (findMonth > -1)
        {
            month = BasicStringUtil.fillLeftString(findMonth + "", 2, "0");
            if (d.length() > y.length())
            {
                ret = d + month + y;
            }
            else
            {
                ret = y + month + d;
            }
        }
        return ret;
    }

    public static String getDateFromSkeySport(String text)
    {
        List<String> nums = PatternUtil.getAllPatternGroup(text, "(\\d+)");

        return "20" + nums.get(2) + nums.get(1) + nums.get(0);
    }

    public static String getDateFromRealGame(String engDate)
    {
        if (engDate.toLowerCase().contains("yesterday"))
        {
            return BasicDateUtil.getDateBeforeDayDateString(
                    BasicDateUtil.getCurrentDateString(), 1);
        }
        if (engDate.toLowerCase().contains("today"))
        {
            return BasicDateUtil.getCurrentDateString();
        }

        engDate = engDate.replaceAll(".*?»", "").replaceAll("\\d+:\\d+", "")
                .replaceAll("[ap]+m", "");
        String ret = "";
        String d = PatternUtil.getFirstPatternGroup(engDate, "(\\d+)");
        String y = PatternUtil.getLastPatternGroup(engDate, "(\\d+)");
        String month = PatternUtil.getLastPatternGroup(engDate, " ([a-zA-Z]+)");
        int findMonth = -1;
        for (int i = 0; i < engMonthes.length; i++)
        {
            if (engMonthes[i].toLowerCase().startsWith(month.toLowerCase()))
            {
                findMonth = i + 1;
                break;
            }
        }
        if (findMonth > -1)
        {
            month = BasicStringUtil.fillLeftString(findMonth + "", 2, "0");
            d = BasicStringUtil.fillLeftString(d, 2, "0");
            if (d.length() > y.length())
            {
                ret = d + month + y;
            }
            else
            {
                ret = y + month + d;
            }
        }
        return ret;
    }

}
