package translation.ciba;

import translate.site.iciba.CibaWordTranslate;

public class TestCibaWord {
	public static void main(String[] args) throws Exception {
		CibaWordTranslate cibaWordTranslate = new CibaWordTranslate("conservation");
		cibaWordTranslate = new CibaWordTranslate("champions");
		System.out.println(cibaWordTranslate.getBasicInfo());
		System.out.println(cibaWordTranslate.getSoundStr());
		System.out.println(cibaWordTranslate.getJsonObject());
	}
}
