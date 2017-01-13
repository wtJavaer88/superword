import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wnc.basic.BasicFileUtil;
import com.wnc.news.api.mine.zhibo8.NewsExtract;
import com.wnc.news.api.mine.zhibo8.SportType;
import com.wnc.news.api.mine.zhibo8.Zb8News;

public class CountZb8WebSite {
	class SiteInfo {
		String name;
		int count;

		public SiteInfo(String name, int count) {
			super();
			this.name = name;
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		@Override
		public String toString() {
			return "SiteInfo [name=" + name + ", count=" + count + "]";
		}
	}

	@Test
	public void t() throws Exception {
		List<Zb8News> newsAfterDay = new NewsExtract().getNewsAfterDay("20160101", SportType.Zuqiu);
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, String> map_siteurl = new HashMap<String, String>();

		for (Zb8News zb8News : newsAfterDay) {
			String keyword = zb8News.getForm_name();
			if (map.containsKey(keyword)) {
				map.put(keyword, map.get(keyword) + 1);
			} else {
				map.put(keyword, 1);
				map_siteurl.put(keyword, zb8News.getTitle() + " / " + zb8News.getFrom_url());
			}
		}
		List<SiteInfo> sites = new ArrayList<SiteInfo>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			sites.add(new SiteInfo(entry.getKey(), entry.getValue()));
		}
		Collections.sort(sites, new Comparator<SiteInfo>() {

			@Override
			public int compare(SiteInfo o1, SiteInfo o2) {
				return o2.getCount() - o1.getCount();
			}
		});
		BasicFileUtil.deleteFile("siteinfos.txt");
		for (SiteInfo siteInfo : sites) {
			BasicFileUtil.writeFileString("siteinfos.txt",
					siteInfo + " 代表新闻: " + map_siteurl.get(siteInfo.getName()) + "\n", null, true);
			System.out.println(siteInfo + " 代表新闻: " + map_siteurl.get(siteInfo.getName()));
		}
	}
}
