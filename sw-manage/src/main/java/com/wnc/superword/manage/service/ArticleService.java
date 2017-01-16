package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.news.api.mine.zhibo8.HtmlContentHelper;
import com.wnc.news.api.mine.zhibo8.Zb8News;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.mapper.ArticleMapper;
import com.wnc.superword.manage.pojo.zb8.Article;
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
			new HtmlContentHelper().initHtmlContent(zb8FromArticle);
			newsWordsAnalyse.decorateZb8News(zb8FromArticle);
			article.setChsContent(zb8FromArticle.getChs_content());
			article.setEngContent(zb8FromArticle.getEng_content());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return article;
	}
}
