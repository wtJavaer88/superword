package translate.site.baidu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import translate.abs.IPrograghTranslate;

public class BaiduPrographTranslate extends BaiduTranslate implements IPrograghTranslate {
	public BaiduPrographTranslate(String engKeyword) {
		super(engKeyword);
	}

	public BaiduPrographTranslate(String from, String to, String engKeyword) {
		super(from, to, engKeyword);
	}

	public String getChResult() throws Exception {
		this.from = "en";
		this.to = "zh";
		System.out.println(getJsonObject());
		JSONArray jsonArray = getJsonObject().getJSONObject("trans_result").getJSONArray("data");
		if (jsonArray != null && !jsonArray.isEmpty())
			return JSONObject.parseObject(jsonArray.get(0).toString()).getString("dst");
		return null;
	}

}
