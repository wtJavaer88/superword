package translate.site.baidu;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.wnc.basic.BasicStringUtil;

import translate.abs.IJSONResource;
import translate.abs.ITranslate;
import translate.util.JsoupHelper;

public class BaiduTranslate implements ITranslate, IJSONResource {
	protected final String API = "http://fanyi.baidu.com/v2transapi?from=%s&query=%s&to=%s";
	protected String from = "en";
	protected String to = "zh";
	protected String engKeyword;
	protected String[] languages = { "auto", "ara", "de", "ru", "fra", "kor", "nl", "pt", "jp", "th", "wyw", "spa",
			"el", "it", "en", "yue", "zh", };
	private JSONObject jsonObject;

	// 'auto' => '自动检测',
	// 'ara' => '阿拉伯语',
	// 'de' => '德语',
	// 'ru' => '俄语',
	// 'fra' => '法语',
	// 'kor' => '韩语',
	// 'nl' => '荷兰语',
	// 'pt' => '葡萄牙语',
	// 'jp' => '日语',
	// 'th' => '泰语',
	// 'wyw' => '文言文',
	// 'spa' => '西班牙语',
	// 'el' => '希腊语',
	// 'it' => '意大利语',
	// 'en' => '英语',
	// 'yue' => '粤语',
	// 'zh' => '中文'
	public BaiduTranslate(String engKeyword) {
		this.engKeyword = engKeyword;
	}

	public BaiduTranslate(String from, String to, String engKeyword) {
		checkLanguage(from, to);
		this.from = from;
		this.to = to;
		this.engKeyword = engKeyword;
	}

	private void checkLanguage(String from, String to) {
		if (BasicStringUtil.isNull2String(from, to)) {
			throw new IllegalArgumentException();
		}
		if (from.equals(to)) {
			throw new IllegalArgumentException("转换的两种语言不能一样");
		}
		List<String> lanList = Arrays.asList(languages);
		if (!lanList.contains(from)) {
			throw new IllegalArgumentException(from + "不是支持的语言");
		}
		if (!lanList.contains(to)) {
			throw new IllegalArgumentException(to + "不是支持的语言");
		}
	}

	public String getApiLink() {
		return String.format(API, from, this.engKeyword, to);
	}

	public JSONObject getJsonObject() throws Exception {
		if (jsonObject != null) {
			return jsonObject;
		}
		return JSONObject.parseObject(JsoupHelper.getJsonResult(getApiLink()));
	}

	public String getWebUrlForMobile() {
		return "http://fanyi.baidu.com/translate?lang=auto2en#en/zh/" + engKeyword;
	}

}
