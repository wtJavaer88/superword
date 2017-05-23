package bbei;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.wnc.tools.SoupUtil;

public class kk {
	@Test
	public void a() {
		for (int i = 1; i < 10; i++) {
			Document doc = SoupUtil.getDoc("https://voice.hupu.com/nba/" + i);
			System.out.println(doc.toString().length());
		}
	}
}
