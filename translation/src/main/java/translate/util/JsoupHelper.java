package translate.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupHelper {

	public static String getJsonResult(String url) throws Exception {
		return Jsoup.connect(url).ignoreContentType(true).execute().body();
	}

	public static Document getDocumentResult(String url) throws Exception {
		return Jsoup.connect(url).timeout(60000)
				.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)").get();
	}
}
