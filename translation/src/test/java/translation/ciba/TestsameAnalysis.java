package translation.ciba;

import translate.site.iciba.CibaWordTranslate;

public class TestsameAnalysis {
	public static void main(String[] args) throws Exception {
		CibaWordTranslate cibaWordTranslate = new CibaWordTranslate("like");
		System.out.println(cibaWordTranslate.getsameAnalysis());

		cibaWordTranslate = new CibaWordTranslate("seam");
		System.out.println(cibaWordTranslate.getSimilar());
		System.out.println(cibaWordTranslate.getAntonym());
	}

}
