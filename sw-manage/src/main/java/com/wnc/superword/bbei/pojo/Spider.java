package com.wnc.superword.bbei.pojo;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.basic.BasicNumberUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.string.PatternUtil;
import com.wnc.tools.SoupUtil;

public class Spider {
	Document doc;
	final static String h = "http://d.beibei.com/search/-62.html";

	public Spider() {
		doc = SoupUtil.getDoc(h);
	}

	public List<MilkBrand> getBrands() {
		List<MilkBrand> mBrands = new ArrayList<MilkBrand>();
		Elements select = doc.select(".brand-cont table a");
		for (Element e : select) {
			mBrands.add(new MilkBrand(e.select("img").first().absUrl("src"), e.text(), e.absUrl("href")));
		}
		return mBrands;
	}

	public List<Milk> getMilks(String url) {
		List<Milk> milks = new ArrayList<Milk>();
		Document t_doc = SoupUtil.getDoc(url);
		Elements select = t_doc.select(".view-ItemListItem a");
		for (Element e : select) {
			String absUrl = e.select("img").first().absUrl("data-src");
			if (BasicStringUtil.isNullString(absUrl)) {
				absUrl = e.select("img").first().absUrl("src");
			}
			milks.add(new Milk(absUrl, e.select(".title").first().text(), e.absUrl("href"), BasicNumberUtil
					.getDouble(PatternUtil.getLastPattern(e.select(".title").first().text(), "\\d+.\\d+"))));
		}
		return milks;
	}

}
