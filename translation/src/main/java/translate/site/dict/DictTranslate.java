package translate.site.dict;

import org.jsoup.nodes.Document;

import translate.abs.IDocResource;
import translate.abs.ITranslate;
import translate.util.JsoupHelper;

public class DictTranslate implements ITranslate, IDocResource {
	protected final String API = "http://dict.cn/%s";
	protected String engKeyword;
	private Document document;

	public DictTranslate(String engKeyword) {
		this.engKeyword = engKeyword;
	}

	public String getApiLink() {
		return String.format(API, this.engKeyword);
	}

	public Document getDocument() throws Exception {
		if (document == null) {
			document = JsoupHelper.getDocumentResult(getApiLink());
		}
		return document;
	}

	public String getWebUrlForMobile() {
		return "http://m.dict.cn/" + engKeyword;
	}

}
