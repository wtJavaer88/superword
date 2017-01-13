package com.wnc.news.api.mine.hupu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.wnc.basic.BasicDateUtil;
import com.wnc.basic.BasicNumberUtil;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.utils.JsoupHelper;

/**
 * 虎扑api不是严格按照日期来的,而是分页到html
 * 
 * @author cpr216
 *
 */
public class TestHupu {
	final String URL_FORMAT = "https://voice.hupu.com/nba/%d";

	public List<Zb8News> getNeed(TimeFilter filter) throws Exception {
		List<Zb8News> list = new ArrayList<Zb8News>();
		int innerLoop = 0;
		for (int i = 1; i <= 100; i++) {
			String format = String.format(URL_FORMAT, i);
			Document documentResult = JsoupHelper.getDocumentResult(format);
			Elements select = documentResult.select(".news-list li");
			for (Element element : select) {
				if (element.select(".list-content").size() == 0) {
					continue;
				}
				// 获取标题和连接
				Element linkElement = element.select(".list-hd a").first();
				String absUrl = linkElement.absUrl("href");
				String text2 = linkElement.text();
				System.out.println("\n" + absUrl + " / " + text2);

				Element contentElement = element.select(".J_share_title").first();
				String jtitle = contentElement.text();
				System.out.println("摘要:" + jtitle);

				// 获取来源
				Element fromElement = element.select(".comeFrom a").first();
				String fromUrl = fromElement.absUrl("href");
				String fromsite = fromElement.text();
				System.out.println(fromUrl + " / " + fromsite);

				String text = element.select(".time").first().text();
				System.out.println("新闻时间:" + text);

				if (filter == null || filter.isFitDay(text)) {
					innerLoop = 1;
					Zb8News news = new Zb8News();
					news.setDay(text);
					news.setTitle(text2);
					news.setNews_time(text);
					news.setFrom_url(fromUrl);
					news.setUrl(absUrl);
					news.setForm_name(fromsite);
					list.add(news);
				} else {
					if (innerLoop == 1) {
						totalWebsites(list);
						return list;
					}
				}

			}
		}
		return list;
	}

	private void totalWebsites(List<Zb8News> list) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Zb8News zb8News : list) {
			String keyword = zb8News.getKeyword();
			if (map.containsKey(keyword)) {
				map.put(keyword, map.get(keyword) + 1);
			} else {
				map.put(keyword, 1);
			}
		}
		System.out.println(map);
	}

	// @Test
	public void testToDay() throws Exception {
		List<Zb8News> need = getNeed(new TimeFilter() {

			@Override
			public boolean isFitDay(String datetime) {
				if (datetime.contains("小时") || datetime.contains("分钟")) {
					return true;
				}
				return false;
			}
		});
		System.out.println("今天的新闻真正好" + need.size());
		for (Zb8News zb8News : need) {
			System.out.println(zb8News.getDay() + " / " + zb8News.getTitle());
		}
	}

	// @Test
	public void testYesterday() throws Exception {
		String today = BasicDateUtil.getCurrentDateString();
		String yesterday = BasicDateUtil.getDateBeforeDayDateString(today, 1);
		int month = BasicNumberUtil.getNumber(yesterday.substring(4, 6));
		int day = BasicNumberUtil.getNumber(yesterday.substring(6, 8));
		final String str = month + "月" + day + "日";
		List<Zb8News> need = getNeed(new TimeFilter() {
			@Override
			public boolean isFitDay(String datetime) {
				if (datetime.equalsIgnoreCase(str)) {
					return true;
				}
				return false;
			}
		});
		System.out.println("昨天的新闻真正好" + need.size());
		for (Zb8News zb8News : need) {
			System.out.println(zb8News.getDay() + " / " + zb8News.getTitle());
		}
	}

	@Test
	public void ttheplayerstribune() throws Exception {
		List<Zb8News> need = getNeed(null);
		System.out.println("theplayerstribune:" + need.size());
		for (Zb8News zb8News : need) {
			if (zb8News.getKeyword().equalsIgnoreCase("theplayerstribune")) {
				System.out.println("find theplayerstribune:" + zb8News.getFrom_url());
			}
		}
	}
}
