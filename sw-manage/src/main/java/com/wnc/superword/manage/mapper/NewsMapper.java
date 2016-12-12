package com.wnc.superword.manage.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.wnc.superword.manage.pojo.News;

public interface NewsMapper extends Mapper<News> {

	List<News> queryList();

}
