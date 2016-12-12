package translate.abs;

import translate.bean.WordExchange;

public interface IWordTranslate extends ITranslate {

	/**
	 * 基本单词释义
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBasicInfo() throws Exception;

	/**
	 * 基本实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getExampleBasic() throws Exception;

	/**
	 * 高级实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getExampleAdvance() throws Exception;

	/**
	 * 英英释义
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getEngEng() throws Exception;

	/**
	 * 短语搭配
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCollocation() throws Exception;

	/**
	 * 句型句式
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSyntax() throws Exception;

	/**
	 * 近义词
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSimilar() throws Exception;

	/**
	 * 反义词
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAntonym() throws Exception;

	/**
	 * 获取单词的各种变形
	 * 
	 * @return
	 */
	public WordExchange getWordExchange() throws Exception;

	public String getSoundStr() throws Exception;
}
