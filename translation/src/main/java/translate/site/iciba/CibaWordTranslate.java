package translate.site.iciba;

import com.wnc.string.PatternUtil;

import translate.abs.IWordTranslate;
import translate.bean.WordExchange;

public class CibaWordTranslate extends CibaTranslate implements IWordTranslate {
	public CibaWordTranslate(String engKeyword) {
		super(engKeyword);
	}

	public String getSoundStr() throws Exception {
		// JSONObject jsonObject =
		// getJsonObject().getJSONObject("baesInfo").getJSONArray("symbols").getJSONObject(0);
		// String am_mp3 = jsonObject.getString("ph_am_mp3");
		// if (BasicStringUtil.isNotNullString(am_mp3)) {
		// return am_mp3;
		// } else {
		// return jsonObject.getString("ph_en_mp3");
		// }
		return PatternUtil.getFirstPattern(getDocument().select(".new-speak-step").last().toString(),
				"http://.*?\\.mp3");
	}

	public String getBasicInfo() throws Exception {
		return getDocument().select(".info-base ul").text();
	}

	public String getExampleBasic() throws Exception {
		return null;
	}

	public String getExampleAdvance() throws Exception {
		return null;
	}

	public String getEngEng() throws Exception {
		return null;
	}

	public String getCollocation() throws Exception {
		return null;
	}

	public String getSyntax() throws Exception {
		return null;
	}

	public String getsameAnalysis() throws Exception {
		if (!getJsonObject().containsKey("sameAnalysis")) {
			return null;
		}
		return getJsonObject().getJSONArray("sameAnalysis").toString();
	}

	public String getSimilar() throws Exception {
		if (!getJsonObject().containsKey("synonym")) {
			return null;
		}
		return getJsonObject().getJSONArray("synonym").toString();
	}

	public String getAntonym() throws Exception {
		if (!getJsonObject().containsKey("antonym")) {
			return null;
		}
		return getJsonObject().getJSONArray("antonym").toString();
	}

	public WordExchange getWordExchange() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
