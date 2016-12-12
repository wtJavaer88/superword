package translate.abs;

public interface ITranslate {
	public String getApiLink();

	/**
	 * 获取网页地址,方便在手机上直接打开, 自己解析太累
	 * 
	 * @return
	 */
	public String getWebUrlForMobile();
}
