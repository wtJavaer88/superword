package translation.dict;

import org.junit.Test;

import translate.abs.IWordTranslate;
import translate.site.dict.DictWordTranslate;

public class TestDictWord {
	@Test
	public void testBasic() throws Exception {
		IWordTranslate dictWordTranslate = new DictWordTranslate("hammer");
		System.out.println(dictWordTranslate.getBasicInfo());
	}
}
