package com.wnc.news.api.mine.zhibo8;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wnc.news.api.common.Comment;
import com.wnc.utils.JsoupHelper;

/**
 * 新闻页面找到评论加载地址
 * 
 * @author cpr216
 *
 */
public class Zb8CommentsUtil2 {
	final static String hot_format = "http://cache.zhibo8.cc/json/%s_hot.htm";
	final static String count_format = "http://cache.zhibo8.cc/json/%s_count.htm";

	public static List<Comment> getHotComments(String article_url) throws Exception {
		List<Comment> list = new ArrayList<Comment>();

		String hotcommentsApiUrl = String.format(hot_format, getArticleFileKey(article_url));
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
			comment.setUserName(object.getString("username"));
			comment.setUserId(object.getString("m_uid"));
			list.add(comment);
		}
		return list;
	}

	private static String getArticleFileKey(String article_url) {
		try {
			Document documentResult = JsoupHelper.getDocumentResult(article_url);
			return documentResult.select("#pl_box").first().attr("file").replace("-", "/");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 获取所有评论总条数
	 * 
	 * @param article_url
	 * @return
	 * @throws Exception
	 */
	public static int getAllCommentsCount(String article_url) throws Exception {
		String hotcommentsApiUrl = String.format(count_format, getArticleFileKey(article_url));
		String jsonResult = JsoupHelper.getJsonResult(hotcommentsApiUrl);
		JSONObject obj = JSONObject.parseObject(jsonResult);
		int intValue = obj.getIntValue("all_num");
		return intValue;
	}

	@Test
	public void t() throws Exception {
		String article_url = "http://news.zhibo8.cc/zuqiu/2017-01-12/5876c7cb5c335.htm";// 苏亚雷斯
		// article_url = "http://news.zhibo8.cc/nba/2017-01-12/83002.htm";// 火箭
		// article_url = "http://news.zhibo8.cc/zuqiu/2016-12-19/82705.htm";
		System.out.println(getHotComments(article_url));
	}
}
