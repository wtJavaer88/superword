package com.wnc.superword.manage.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.wnc.superword.manage.pojo.zb8.ArticleComment;

public interface CommentMapper extends Mapper<ArticleComment> {
	List<ArticleComment> queryList();
}
