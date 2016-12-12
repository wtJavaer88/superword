package com.wnc.superword.manage.mapper;

import java.util.List;

import word.DicWord;

public interface DictMapper {
	List<DicWord> findAllDicWord();

	DicWord findWord(String word);
}
