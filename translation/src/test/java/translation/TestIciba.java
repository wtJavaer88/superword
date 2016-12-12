package translation;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import translate.site.iciba.CibaTranslate;
import translate.util.JsoupHelper;

public class TestIciba {
	@Test
	public void basic() throws Exception {
		String body = JsoupHelper.getJsonResult("http://www.iciba.com/index.php?a=getWordMean&c=search&word=hammer");
		System.out.println(body);
		while (!body.startsWith("{")) {
			body = body.substring(1);
		}
		System.out.println(body);
		while (!body.endsWith("}")) {
			body = body.substring(0, body.length() - 1);
		}
		JSONObject parseObject = JSONObject.parseObject(body);
		System.out.println(parseObject);
		JSONArray jsonArray = parseObject.getJSONArray("sentence");
		for (Object object : jsonArray) {
			JSONObject sentence = JSONObject.parseObject(object.toString());
			System.out.println(sentence.get("Network_cn") + " " + sentence.get("tts_mp3"));
		}
		JSONArray jsonArray2 = parseObject.getJSONObject("baesInfo").getJSONArray("symbols");
		System.out.println(JSONObject.parseObject(jsonArray2.get(0).toString()));
	}

	// 更多例句
	// http://dj.iciba.com/love-"+1+"-" + 24 + "-%01-0-0-0-0.html" (全部)词典双语例句
	// http://dj.iciba.com/love-"+2+"-" + 24 + "-%01-0-0-0-0.html" (全部)权威媒体例句
	// http://dj.iciba.com/love-"+2+"-" + 24 + "-%01-0-0-2-3.html" (书面语,难)权威媒体例句
	// 类别:全部,口语,书面语
	// 难度:全部,简单,中,难
	@Test
	public void moreExampleApi() throws Exception {
		Document documentResult = JsoupHelper
				.getDocumentResult("http://dj.iciba.com/love-" + 1 + "-" + 24 + "-%01-0-0-0-0.html");
		System.out.println(documentResult.toString());
	}

	@Test
	public void test() throws Exception {
		CibaTranslate cibaTranslate = new CibaTranslate("hammer");
		String apiLink = cibaTranslate.getApiLink();
		System.out.println(apiLink);
		System.out.println(cibaTranslate.getJsonObject());
	}

}
