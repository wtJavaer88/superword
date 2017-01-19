package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.mapper.CommentMapper;
import com.wnc.superword.manage.pojo.zb8.ArticleComment;

@Service
public class CommentService extends BaseService<ArticleComment> {

	@Autowired
	private CommentMapper commentMapper;

	public PageInfo<ArticleComment> queryList(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Example example = new Example(ArticleComment.class);
			example.setOrderByClause("create_time desc");
			// 设置分页参数
			PageHelper.startPage(page, rows);

			List<ArticleComment> list = this.commentMapper.selectByExample(example);
			return new PageInfo<ArticleComment>(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

	public List<ArticleComment> queryByArticle(Long id) {
		Example example = new Example(ArticleComment.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("articleId", id);
		example.setOrderByClause("priority asc,up desc");
		return commentMapper.selectByExample(example);
	}

	public int articleHotCount(Long id) {
		Example example = new Example(ArticleComment.class);
		example.createCriteria().andEqualTo("articleId", id);
		return commentMapper.selectByExample(example).size();
	}

	public boolean isExist(Long article_id) {
		Example example = new Example(ArticleComment.class);
		example.createCriteria().andEqualTo("articleId", article_id);
		return commentMapper.selectByExample(example).size() > 0;
	}
}
