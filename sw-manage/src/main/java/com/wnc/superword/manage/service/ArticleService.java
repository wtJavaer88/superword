package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.basic.BasicDateUtil;
import com.wnc.news.api.common.Comment;
import com.wnc.news.api.mine.zhibo8.HtmlContentHelper;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.mapper.ArticleMapper;
import com.wnc.superword.manage.pojo.zb8.Article;
import com.wnc.superword.manage.pojo.zb8.ArticleComment;
import com.wnc.superword.manage.pojo.zb8.CommentAdapter;
import com.wnc.superword.manage.pojo.zb8.Zb8NewsAdapter;
import com.wnc.superword.manage.util.NewsWordsAnalyse;

@Service
public class ArticleService extends BaseService<Article> {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	NewsWordsAnalyse newsWordsAnalyse;

	public PageInfo<Article> queryList(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Example example = new Example(Article.class);
			example.setOrderByClause("news_time desc");
			// 设置分页参数
			PageHelper.startPage(page, rows);

			List<Article> list = this.articleMapper.selectByExample(example);
			return new PageInfo<Article>(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

	public PageInfo<Article> queryNullComments(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Example example = new Example(Article.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andIsNull("comments");
			Criteria createCriteria2 = example.createCriteria();
			createCriteria2.andEqualTo("comments", "0");
			example.or(createCriteria2);
			example.setOrderByClause("news_time desc");
			// 设置分页参数
			PageHelper.startPage(page, rows);

			List<Article> list = this.articleMapper.selectByExample(example);
			return new PageInfo<Article>(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

	public List<Article> queryNullContent(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Example example = new Example(Article.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andIsNull("chsContent");
			createCriteria.andIsNull("engContent");
			createCriteria.andLessThanOrEqualTo("day", "2016-08-04");
			createCriteria.andIsNotNull("fromUrl");
			example.setOrderByClause("news_time desc");
			// 设置分页参数
			PageHelper.startPage(page, rows);

			List<Article> list = this.articleMapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

	public List<Article> queryNoDecorateEngContent(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			PageHelper.startPage(page, rows);
			Example example = new Example(Article.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andNotLike("engContent", "%<font color=\"blue\">%");
			createCriteria.andNotLike("keyword", "%西甲%");
			createCriteria.andIsNotNull("engContent");
			example.setOrderByClause("news_time desc");
			// 设置分页参数

			List<Article> list = this.articleMapper.selectByExample(example);
			System.out.println("getTotal:" + new PageInfo<>(list).getTotal());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

	/**
	 * 获取文章并翻译文章为英文
	 * 
	 * @param article
	 * @return
	 */
	public Article decorateArticle(Article article) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Zb8News zb8FromArticle = Zb8NewsAdapter.getZb8FromArticle(article);
			HtmlContentHelper htmlContentHelper = new HtmlContentHelper();
			htmlContentHelper.initEngHtmlContent(zb8FromArticle);
			if (zb8FromArticle.getEng_content() != null) {
				newsWordsAnalyse.decorateZb8News(zb8FromArticle);
				htmlContentHelper.initChsHtmlContent(zb8FromArticle);
				article.setChsContent(zb8FromArticle.getChs_content());
				article.setEngContent(zb8FromArticle.getEng_content());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return article;
	}

	public synchronized int updateContent(Article article) {
		return this.update(article);
	}

	public synchronized void updateComments(Article article, List<Comment> top5Comments,
			CommentService commentService) {
		update(article);
		for (Comment comment : top5Comments) {
			ArticleComment articleComment = CommentAdapter.getArticleComment(comment);
			articleComment.setArticleId(article.getId());
			articleComment.setCreateTime(BasicDateUtil.getCurrentDateTimeString());
			// System.out.println(articleComment);
			commentService.save(articleComment);
		}
	}
}
