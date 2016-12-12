package com.wnc.superword.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.mapper.DictMapper;
import com.wnc.superword.manage.util.WordSplit;

import word.DicWord;
import word.Topic;

@Service
public class DictService {

	@Autowired
	private DictMapper dictMapper;

	static List<DicWord> cetDicWords;

	public List<DicWord> findAllDicWord() {
		if (cetDicWords == null)
			cetDicWords = dictMapper.findAllDicWord();
		return cetDicWords;
	}

	public List<Topic> findCETWords(String dialog) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_DICT);
		List<Topic> finds = new ArrayList<Topic>();
		try {
			List<String> wordAndChars = WordSplit.getUniqueWords(dialog);
			for (String word : wordAndChars) {
				for (DicWord dw : findAllDicWord()) {
					final Topic topic = new Topic();
					topic.setTopic_id(dw.getTopic_id());
					topic.setTopic_base_word(dw.getBase_word());
					topic.setMean_cn(dw.getCn_mean());
					topic.setBookName(dw.getBook_name());

					if (hasFind(finds, word, dw.getBase_word(), topic, "base")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_er(), topic, "word_er")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_est(), topic, "word_est")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_done(), topic, "word_done")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_ing(), topic, "word_ing")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_past(), topic, "word_past")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_pl(), topic, "word_pl")) {
						break;
					} else if (hasFind(finds, word, dw.getWord_third(), topic, "word_third")) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}

		return finds;
	}

	private boolean hasFind(List<Topic> finds, String word, String dwStr, final Topic topic, String desc) {
		if (dwStr != null && dwStr.equalsIgnoreCase(word)) {
			topic.setMatched_word(word);
			topic.setState(desc);
			if (!finds.contains(topic)) {
				finds.add(topic);
			}
			return true;
		}
		return false;
	}

	public DicWord findWord(String word) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_DICT);
		try {
			return dictMapper.findWord(word);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}
}
