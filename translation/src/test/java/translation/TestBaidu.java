package translation;

import org.jsoup.Jsoup;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import translate.site.baidu.BaiduPrographTranslate;
import translate.site.baidu.BaiduWordTranslate;

public class TestBaidu {
	@Test
	public void basic() throws Exception {
		String url = "http://fanyi.baidu.com/v2transapi?from=en&query=I hava a wife&to=zh";
		String body = Jsoup.connect(url).ignoreContentType(true).execute().body();
		System.out.println(body);
		JSONObject parseObject = JSONObject.parseObject(body);
		JSONArray jsonArray = parseObject.getJSONObject("trans_result").getJSONArray("data");
		for (Object object : jsonArray) {
			System.out.println(JSONObject.parseObject(object.toString()).get("dst"));
		}
	}

	// @Test
	public void t() throws Exception {
		String chResult = new BaiduPrographTranslate("pretty").getChResult();
		System.out.println(chResult);

		chResult = new BaiduWordTranslate("pretty").getBasicInfo();
		System.out.println(chResult);
	}
}
