package com.wnc.news.api.mine.zhibo8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.common.Comment;
import com.wnc.string.PatternUtil;
import com.wnc.utils.JsoupHelper;

/**
 * 暂时支持足球篮球滚动新闻模块的新闻, 其它小模块的,需要去新闻页面找到评论加载地址
 * 
 * @author cpr216
 *
 */
public class Zb8CommentsUtil {
	final static String hot_format = "http://cache.zhibo8.cc/json/%s/news/%s/%s_hot.htm";
	final static String count_format = "http://cache.zhibo8.cc/json/%s/news/%s/%s_count.htm";

	public static List<Comment> getHotComments(String article_url) throws Exception {
		List<Comment> list = new ArrayList<Comment>();
		String hotcommentsApiUrl = String.format(hot_format, getArticleDate(article_url), getArticleType(article_url),
				getArticleId(article_url));
		String jsonResult = "";
		try {
			jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
		} catch (Exception e) {
			if (e.getMessage().contains("404 error ")) {
				hotcommentsApiUrl = String.format(hot_format, getYesterday(getArticleDate(article_url)),
						getArticleType(article_url), getArticleId(article_url));
				jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
			}
		}
		JSONArray array = JSONArray.parseArray(jsonResult);
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			Comment comment = new Comment();
			String content = object.getString("content");
			comment.setContent(content);
			comment.setUp(object.getIntValue("up"));
			comment.setDown(object.getIntValue("down"));
			comment.setNewsId(article_url);
			comment.setUserName(object.getString("username"));
			comment.setUserId(object.getString("m_uid"));
			list.add(comment);
		}
		return list;
	}

	private static Object getYesterday(String articleDate) {
		String replaceAll = articleDate.replaceAll("[^0-9]", "");
		String yes = BasicDateUtil.getDateBeforeDayDateString(replaceAll, 1);
		return yes.substring(0, 4) + "_" + yes.substring(4, 6) + "_" + yes.substring(6);
	}

	public static String getArticleDate(String article_url) {
		return PatternUtil.getFirstPatternGroup(article_url, "/(\\d{4}-\\d{2}-\\d{2})/").replace("-", "_");
	}

	public static String getArticleType(String article_url) {
		return PatternUtil.getFirstPatternGroup(article_url, "/(\\w+)/\\d{4}-\\d{2}-\\d{2}/");
	}

	public static String getArticleId(String article_url) {
		return PatternUtil.getFirstPatternGroup(article_url, "\\d{4}-\\d{2}-\\d{2}/(.*?)\\.");
	}

	/**
	 * 获取所有评论总条数
	 * 
	 * @param article_url
	 * @return
	 * @throws Exception
	 */
	public static int getAllCommentsCount(String article_url) throws Exception {
		String hotcommentsApiUrl = String.format(count_format, getArticleDate(article_url), getArticleType(article_url),
				getArticleId(article_url));
		String jsonResult = "";
		try {
			jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
		} catch (Exception e) {
			if (e.getMessage().contains("404 error ")) {
				hotcommentsApiUrl = String.format(count_format, getYesterday(getArticleDate(article_url)),
						getArticleType(article_url), getArticleId(article_url));
				jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
			}
		}
		JSONObject obj = JSONObject.parseObject(jsonResult);
		int intValue = obj.getIntValue("all_num");
		return intValue;
	}

	@Test
	public void t() throws Exception {
		String article_url = "http://news.zhibo8.cc/zuqiu/2017-01-12/5876c7cb5c335.htm";// 苏亚雷斯
		// article_url = "http://news.zhibo8.cc/nba/2017-01-12/83002.htm";// 火箭
		article_url = "http://news.zhibo8.cc/zuqiu/2016-12-19/82705.htm";
		System.out.println(getArticleType(article_url));
		System.out.println(getAllCommentsCount(article_url));
		System.out.println(getHotComments(article_url));
	}
}
