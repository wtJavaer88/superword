package com.wnc.superword.manage.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.wnc.superword.manage.pojo.zb8.Article;

public interface ArticleMapper extends Mapper<Article> {
	List<Article> queryList();

	List<Article> listBySql(Map map);

	Long selectCountBySql(Map map);
}
