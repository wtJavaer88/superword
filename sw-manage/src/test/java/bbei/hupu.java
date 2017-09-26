package bbei;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.wnc.news.api.mine.zhibo8.SportType;
import com.wnc.tools.SoupUtil;
import com.wnc.utils.JsoupHelper;

public class hupu {
	// @Test
	public void nba() {
		for (int i = 1; i < 10; i++) {
			Document doc = SoupUtil.getDoc("https://voice.hupu.com/nba/" + i);
			System.out.println(doc.toString().length());
		}
	}

	// @Test
	public void soccer() {
		for (int i = 3; i < 4; i++) {
			Document doc = SoupUtil.getDoc("https://voice.hupu.com/soccer/" + i);
			Elements select = doc.select(".news-list ul li");
			int size = select.size();
			System.out.println("li条数:" + size);
			for (Element element : select) {
				Element list_hd = element.select(".list-hd a").first();
				if (list_hd != null) {
					System.out.println("------------------------");
					System.out.println("title:" + list_hd.text());
					System.out.println("url:" + list_hd.absUrl("href"));
				} else {
					System.err.println(element);
					size--;
				}
				Element other_left = element.select(".other-left a").first();
				if (other_left != null) {
					System.out.println("news_time:" + other_left.attr("title"));
				} else {
					System.err.println(element);
					size--;
				}
				Element comeFrom = element.select(".comeFrom a").first();
				if (comeFrom != null) {
					System.out.println("fromsite:" + comeFrom.text());
					System.out.println("fromurl:" + comeFrom.absUrl("href"));
					System.out.println("------------------------\n");
				} else {
					System.err.println(element);
					size--;
				}
			}
			System.out.println("新闻条数:" + size);
		}
	}

	// @Test
	public void singlePage() throws Exception {
		String url = "http://www.realmadrid.com/noticias/2017/05/cristiano-ronaldo-%E2%80%9Cqueremos-entrar-en-la-historia-ganando-dos-champions-seguidas%E2%80%9D";
		url = "http://www.standard.co.uk/sport/football/danny-rose-harry-kane-wants-to-break-records-with-tottenham-but-others-may-leave-a3550186.html";
		Document documentResult = JsoupHelper.getDocumentResult(url);
		System.out.println(documentResult.select(".article-wrapper .text-wrapper > p"));
	}

	@Test
	public void page() throws Exception {
		ArrayList<HpNews> list = new ArrayList<HpNews>();
		new HpDayNewsThread("2017-04-29 00:00:00", list, SportType.Soccer).run();
		;
		for (HpNews hpNews : list) {
			System.out.println(hpNews.getTitle() + " " + hpNews.getNews_time() + " " + hpNews.getFrom_name());
		}
	}
}
