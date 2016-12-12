package translate.site.baidu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import translate.abs.IWordTranslate;
import translate.bean.WordExchange;

public class BaiduWordTranslate extends BaiduTranslate implements IWordTranslate {
	public BaiduWordTranslate(String engKeyword) {
		super(engKeyword);
	}

	public BaiduWordTranslate(String from, String to, String engKeyword) {
		super(from, to, engKeyword);
	}

	public String getBasicInfo() throws Exception {
		JSONArray jsonArray = getJsonObject().getJSONObject("dict_result").getJSONObject("simple_means")
				.getJSONArray("symbols");
		if (jsonArray != null && !jsonArray.isEmpty())
			return jsonArray.get(0).toString();
		return null;
	}

	public String getExampleBasic() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getExampleAdvance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEngEng() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCollocation() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSyntax() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSimilar() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAntonym() {
		// TODO Auto-generated method stub
		return null;
	}

	public WordExchange getWordExchange() throws Exception {
		JSONObject exchangeObj = getJsonObject().getJSONObject("dict_result").getJSONObject("simple_means")
				.getJSONObject("exchange");
		WordExchange we = new WordExchange();
		we.setWord_done(("" + exchangeObj.get("word_done")).replace("[", "").replace("]", "").replace("\"", ""));
		we.setWord_er(("" + exchangeObj.get("word_er")).replace("[", "").replace("]", "").replace("\"", ""));
		we.setWord_est(("" + exchangeObj.get("word_est")).replace("[", "").replace("]", "").replace("\"", ""));
		we.setWord_ing(("" + exchangeObj.get("word_ing")).replace("[", "").replace("]", "").replace("\"", ""));
		we.setWord_past(("" + exchangeObj.get("word_past")).replace("[", "").replace("]", "").replace("\"", ""));
		we.setWord_pl(("" + exchangeObj.get("word_pl")).replace("[", "").replace("]", "").replace("\"", ""));
		we.setWord_third(("" + exchangeObj.get("word_third")).replace("[", "").replace("]", "").replace("\"", ""));
		return we;
	}

	public String getSoundStr() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
