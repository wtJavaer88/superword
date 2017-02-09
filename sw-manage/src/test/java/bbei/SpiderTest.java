package bbei;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.wnc.superword.bbei.pojo.Milk;
import com.wnc.superword.bbei.pojo.MilkBrand;
import com.wnc.superword.bbei.pojo.Spider;

public class SpiderTest {
	// @Test
	public void a() {
		Spider spider = new Spider();
		List<MilkBrand> brands = spider.getBrands();
		MilkBrand milkBrand = brands.get(0);
		String jsonString = JSONObject.toJSONString(milkBrand);
		System.out.println(jsonString);
		for (MilkBrand milkBrand2 : brands) {
			System.out.println(spider.getMilks(milkBrand2.getUrl()));
		}
	}

	@Test
	public void b() {
		List<Milk> milks = new Spider().getMilks("http://d.beibei.com/-62--4721-hot-1.html");
		System.out.println(milks);
		for (Milk milk : milks) {
			System.out.println(milk.getName() + " / " + milk.getPic());
		}
	}
}
