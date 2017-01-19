package com.wnc.superword.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.basic.BasicDateUtil;
import com.wnc.basic.BasicStringUtil;
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

	public List<Article> queryList(Integer page, Integer rows, String day, String keyword, boolean is_translate) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			String whereSql = getWhereSql(day, keyword, is_translate);
			Map map = new HashMap<>();
			map.put("start", (page - 1) * rows);
			map.put("rows", rows);
			map.put("whereSql", whereSql);
			return this.articleMapper.listBySql(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

	private String getWhereSql(String day, String keyword, boolean is_translate) {
		String whereSql = "WHERE 1=1 ";
		if (day != null && BasicStringUtil.isNotNullString(day.trim())) {
			whereSql += " and day='" + day + "'";
		}
		if (keyword != null && BasicStringUtil.isNotNullString(keyword.trim())) {
			String orSql = "1=2";
			for (String s : keyword.split(",")) {
				orSql += " or keyword like '%" + s + "%'";
			}
			orSql = "(" + orSql + ")";
			whereSql += " and " + orSql;
		}
		if (is_translate) {
			whereSql += " and (eng_content IS NOT NULL  and (from_url not like 'http://www.marca.com/%' or from_url like 'http://www.marca.com/en/%'))";
		}
		return whereSql;
	}

	public Long getTotal(String day, String keyword, boolean is_translate) {
		Map map = new HashMap<>();
		String whereSql = getWhereSql(day, keyword, is_translate);
		map.put("whereSql", whereSql);
		return articleMapper.selectCountBySql(map);
	}

	public synchronized PageInfo<Article> queryNullComments(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Example example = new Example(Article.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andEqualTo("comments", "0");
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

	public synchronized List<Article> queryNullContent(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			Example example = new Example(Article.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andIsNull("chsContent");
			createCriteria.andIsNull("engContent");
			createCriteria.andIsNotNull("fromUrl");
			createCriteria.andGreaterThan("day", "2016-11-11");
			createCriteria.andNotLike("fromUrl", "http://www.marca.com/%");
			Criteria createCriteria2 = example.createCriteria();
			createCriteria2.andLike("fromUrl", "http://www.marca.com/en/%");
			createCriteria2.andIsNull("chsContent");
			createCriteria2.andIsNull("engContent");
			createCriteria.andGreaterThan("day", "2016-11-11");
			example.or(createCriteria2);

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

	public synchronized List<Article> queryNoDecorateEngContent(Integer page, Integer rows) {
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
		}
		return article;
	}

	public synchronized int updateContent(Article article) {
		return this.update(article);
	}

	public boolean isExist(String url) {
		Example example = new Example(Article.class);
		example.createCriteria().andEqualTo("url", url);
		return articleMapper.selectByExample(example).size() > 0;
	}

	public synchronized void updateComments(Article article, List<Comment> top5Comments,
			CommentService commentService) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_ZB8);
		try {
			update(article);
			for (Comment comment : top5Comments) {
				ArticleComment articleComment = CommentAdapter.getArticleComment(comment);
				articleComment.setArticleId(article.getId());
				articleComment.setCreateTime(BasicDateUtil.getCurrentDateTimeString());
				// System.out.println(articleComment);
				commentService.save(articleComment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
	}
}
