package bbei;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.mine.zhibo8.SportType;
import com.wnc.tools.SoupUtil;

public class HpDayNewsThread implements Runnable {
	String lastUpdateTime;
	List<HpNews> list;
	String dayNewsUrl;
	SportType type;
	final String HP_URL_FORMAT = "https://voice.hupu.com/%s/%d";
	final int MAX_PAGE = 100;

	public HpDayNewsThread(String lastUpdateTime, List<HpNews> list, SportType type) {
		this.type = type;
		this.lastUpdateTime = lastUpdateTime;
		this.list = list;
	}

	@Override
	public void run() {
		if (lastUpdateTime.replace("-", "").substring(0, 8).compareTo(BasicDateUtil.getCurrentDateString()) > 0) {
			return;
		}
		System.out.println("开始抽取:" + lastUpdateTime);
		try {
			getNews();
		} catch (Exception e) {
			System.out.println("抽取中发生异常:" + lastUpdateTime);
			e.printStackTrace();
		}
		System.out.println("结束抽取:" + lastUpdateTime);
	}

	private void getNews() throws Exception {
		boolean flag = false;
		for (int i = 1; i <= MAX_PAGE && !flag; i++) {
			Document doc = SoupUtil.getDoc(String.format(HP_URL_FORMAT, type.getDesc(), i));
			Elements select = doc.select(".news-list ul li");
			int size = select.size();
			System.out.println("li条数:" + size);
			for (Element element : select) {
				HpNews news = new HpNews();
				String from_url = "";

				news.setSport_type(type.getId());
				news.setCreate_time(BasicDateUtil.getCurrentDateTimeString());

				Element list_hd = element.select(".list-hd a").first();
				if (list_hd != null) {
					System.out.println("------------------------");
					System.out.println("title:" + list_hd.text());
					System.out.println("url:" + list_hd.absUrl("href"));
					news.setTitle(list_hd.text());
					news.setUrl(list_hd.absUrl("href"));
				} else {
					System.err.println(element);
					size--;
				}
				Element other_left = element.select(".other-left a").first();
				if (other_left != null) {
					System.out.println("news_time:" + other_left.attr("title"));
					news.setNews_time(other_left.attr("title") + ":00");
					news.setDay(other_left.attr("title").replace("-", "").substring(0, 8));
				} else {
					System.err.println(element);
					size--;
				}
				Element comeFrom = element.select(".comeFrom a").first();
				if (comeFrom != null) {
					System.out.println("fromsite:" + comeFrom.text());
					System.out.println("fromurl:" + comeFrom.absUrl("href"));
					System.out.println("---------------" + "---------\n");
					news.setFrom_name(comeFrom.text());
					from_url = comeFrom.absUrl("href");
					if (from_url.contains(".hupu.")) {
						continue;
					}
					news.setFrom_url(from_url);
				} else {
					System.err.println(element);
					size--;
				}
				if (news.getNews_time() == null) {
					continue;
				}
				if (news.getNews_time().compareTo(lastUpdateTime) < 0) {
					flag = true;
					break;
				} else {
					list.add(news);
				}
			}
			System.out.println("新闻条数:" + size);
		}

	}
}
