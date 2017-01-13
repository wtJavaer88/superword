package com.wnc.news.api.mine.zhibo8;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wnc.basic.BasicDateUtil;
import com.wnc.utils.JsoupHelper;

public class DayNewsThread implements Runnable {
	String today;
	List<Zb8News> list;
	private final String ZB8_DOMAIN = "http://news.zhibo8.cc";
	private final String API_FORMAT = "http://news.zhibo8.cc/%s/json/%s.htm";
	String dayNewsUrl;

	public DayNewsThread(String today, List<Zb8News> list, SportType type) {
		if (today.length() == 8) {
			today = today.substring(0, 4) + "-" + today.substring(4, 6) + "-" + today.substring(6);
		}
		this.today = today;
		this.list = list;
		this.dayNewsUrl = String.format(API_FORMAT, type.getDesc(), today);
	}

	@Override
	public void run() {
		if (today.replace("-", "").compareTo(BasicDateUtil.getCurrentDateString()) > 0) {
			return;
		}
		System.out.println("开始抽取:" + today);
		try {
			getNewsByDay(today);
		} catch (Exception e) {
			System.out.println("抽取中发生异常:" + today);
			e.printStackTrace();
		}
		System.out.println("结束抽取:" + today);
	}

	private void getNewsByDay(String day) throws Exception {
		String jsonResult = JsoupHelper.getJsonResult(dayNewsUrl);
		JSONObject parseObject = JSONObject.parseObject(jsonResult);
		JSONArray jsonArray = parseObject.getJSONArray("video_arr");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String from_url = object.getString("from_url");
			if (from_url.contains(".zhibo8.")) {
				continue;
			}
			String url = ZB8_DOMAIN + object.getString("url");
			Zb8News news = new Zb8News();
			news.setFrom_url(from_url);
			news.setForm_name(object.getString("from_name"));
			news.setUrl(url);
			news.setDay(day);
			news.setTitle(object.getString("shortTitle"));
			news.setKeyword(object.getString("lable"));
			news.setThumbnail(object.getString("thumbnail"));
			news.setNews_time(object.getString("createtime"));
			news.setCreate_time(BasicDateUtil.getCurrentDateTimeString());
			// new HtmlContentHelper().initHtmlContent(news);
			list.add(news);
		}
	}

}
