package translate.site.iciba;

import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;

import translate.abs.IDocResource;
import translate.abs.IJSONResource;
import translate.abs.ITranslate;
import translate.util.JsoupHelper;

public class CibaTranslate implements ITranslate, IDocResource, IJSONResource {
	protected final String API = "http://www.iciba.com/index.php?a=getWordMean&c=search&word=%s";
	protected String engKeyword;
	private JSONObject jsonObject;
	private Document document;

	public CibaTranslate(String engKeyword) {
		this.engKeyword = engKeyword;
	}

	public String getApiLink() {
		return String.format(API, this.engKeyword);
	}

	public JSONObject getJsonObject() throws Exception {
		if (jsonObject != null) {
			return jsonObject;
		}
		return JSONObject.parseObject(JsoupHelper.getJsonResult(getApiLink()));
	}

	public Document getDocument() throws Exception {
		if (document == null) {
			document = JsoupHelper.getDocumentResult("http://www.iciba.com/" + engKeyword);
		}
		return document;
	}

	public String getWebUrlForMobile() {
		return "http://m.iciba.com/" + engKeyword;
	}

}
