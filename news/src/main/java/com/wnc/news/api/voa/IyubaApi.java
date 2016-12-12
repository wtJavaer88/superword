package com.wnc.news.api.voa;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.basic.BasicNumberUtil;
import com.wnc.news.api.common.AbstractVoaHtmlPicker;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.api.common.VoaApi;
import com.wnc.news.website.WebSite;
import com.wnc.news.website.WebSiteUtil;
import com.wnc.string.PatternUtil;
import com.wnc.utils.JsoupHelper;

import voa.VINFO;
import voa.VoaNewsInfo;

public class IyubaApi extends AbstractVoaHtmlPicker implements VoaApi {
	WebSite webSite = WebSiteUtil.getIyuba();
	Logger log = Logger.getLogger(IyubaApi.class);

	public IyubaApi() {

	}

	@Override
	protected int getFromPage() {
		return 1;
	}

	@Override
	protected VoaNewsInfo getBaseNewsInfo(Element mainDiv) {
		VoaNewsInfo newsInfo = null;
		try {
			newsInfo = new VoaNewsInfo();

			newsInfo.setDate(mainDiv.select(".date").first().text().replace("-", ""));
			newsInfo.setUrl(mainDiv.select("a").first().absUrl("href"));
			newsInfo.setHead_pic(mainDiv.select("img").first().absUrl("src"));
			newsInfo.setTitle(mainDiv.select(".desc_en").first().text());
		} catch (Exception e) {
			e.printStackTrace();
			newsInfo = null;
		}
		return newsInfo;
	}

	@Override
	protected VoaNewsInfo getNewsFromUrl(WebSite webSite, String url) throws Exception {
		VoaNewsInfo news_info = new VoaNewsInfo();
		news_info.setWebsite(webSite);
		news_info.setUrl(url);
		try {
			Document documentResult = JsoupHelper.getDocumentResult(url);
			String mp3 = documentResult.select("#myMusic").attr("src");
			Elements select = documentResult.select("#HidedivItems table tr");
			List<VINFO> list = new ArrayList<VINFO>();
			for (Element element : select) {
				VINFO info = new VINFO();
				String en = element.text();
				String obj = element.nextElementSibling().nextElementSibling().toString();
				String ch = PatternUtil.getFirstPatternGroup(obj, "obj\\[3\\]\\='(.*?)'");
				String time = PatternUtil.getFirstPatternGroup(obj, "obj\\[0\\]\\=(\\d+)");
				info.setCh(ch);
				info.setEn(en);
				info.setTime(BasicNumberUtil.getNumber(time));
				list.add(info);
			}
			String jsonString = com.alibaba.fastjson.JSONArray.toJSONString(list);
			news_info.setHtml_content(jsonString);
			news_info.setMp3(mp3);
		} catch (Exception e) {
			log.error(url, e);
			e.printStackTrace();
		}

		return news_info;
	}

	@Override
	public String getPage(int i) {
		return String.format(webSite.getFormat(), i);
	}

	@Override
	protected boolean isFresh(NewsInfo t_info) {
		return true;
	}

	@Override
	public List<VoaNewsInfo> getAllNewsWithContent() {
		return getAllNews(webSite);
	}

	public NewsInfo getNewsFromUrl(String url) throws Exception {
		return getNewsFromUrl(webSite, url);
	}
}
