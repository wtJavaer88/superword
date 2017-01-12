package com.wnc.news.api.mine.zhibo8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wnc.news.api.common.Comment;
import com.wnc.string.PatternUtil;
import com.wnc.utils.JsoupHelper;

public class Zb8CommentsUtil {
	static String hot_format = "http://cache.zhibo8.cc/json/%s/news/%s/%s_hot.htm";
	static String count_format = "http://cache.zhibo8.cc/json/%s/news/%s/%s_count.htm";

	public static List<Comment> getHotComments(String article_url) throws Exception {
		List<Comment> list = new ArrayList<Comment>();
		String hotcommentsApiUrl = String.format(hot_format, getArticleDate(article_url), getArticleType(article_url),
				getArticleId(article_url));
		String jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
		JSONArray array = JSONArray.parseArray(jsonResult);
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			Comment comment = new Comment();
			String content = object.getString("content");
			comment.setContent(content);
			comment.setUp(object.getIntValue("up"));
			comment.setDown(object.getIntValue("down"));
			comment.setNewsId(article_url);
			comment.setUserId(object.getString("username"));
			list.add(comment);
		}
		return list;
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
	public int getAllCommentsCount(String article_url) throws Exception {
		String hotcommentsApiUrl = String.format(count_format, getArticleDate(article_url), getArticleType(article_url),
				getArticleId(article_url));
		String jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
		JSONObject obj = JSONObject.parseObject(jsonResult);
		int intValue = obj.getIntValue("all_num");
		System.out.println("评论总数:" + intValue);
		return intValue;
	}

	@Test
	public void t() throws Exception {
		String article_url = "http://news.zhibo8.cc/zuqiu/2017-01-12/5876c7cb5c335.htm";// 苏亚雷斯
		// article_url = "http://news.zhibo8.cc/nba/2017-01-12/83002.htm";// 火箭
		System.out.println(getArticleType(article_url));
		getAllCommentsCount(article_url);
		System.out.println(getHotComments(article_url));
	}
}
