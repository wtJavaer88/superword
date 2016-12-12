package translation;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import translate.util.JsoupHelper;

public class TestDict {
	@Test
	public void basic() throws Exception {
		Document documentResult = JsoupHelper.getDocumentResult("http://dict.cn/hammer");
		// audio.dict.cn
		for (Element e : documentResult.select("i")) {
			System.out.println("http://audio.dict.cn/" + e.attr("naudio"));
		}
		// System.out.println(documentResult.toString());
	}

}
