package translate.site.dict;

import org.jsoup.nodes.Element;

import translate.abs.IWordTranslate;
import translate.bean.WordExchange;

public class DictWordTranslate extends DictTranslate implements IWordTranslate {
	public DictWordTranslate(String engKeyword) {
		super(engKeyword);
	}

	public String getBasicInfo() throws Exception {
		String result = "";
		for (Element element : getDocument().select(".dict-basic-ul").first().select("li")) {
			result += element.text() + "\n";
		}
		return result;
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

	public String getSimilar() {
		return null;
	}

	public String getAntonym() {
		return null;
	}

	public WordExchange getWordExchange() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSoundStr() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
