package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.basic.BasicDateUtil;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.mapper.NewsMapper;
import com.wnc.superword.manage.pojo.News;
import com.wnc.superword.manage.task.NewsWordsAnalyse;
import com.wnc.utils.EasyUIResult;

@Service
public class NewsService extends BaseService<News> {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsWordsAnalyse newsWordsAnalyse;

	public EasyUIResult queryList(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_MAIN);
		// 设置分页参数
		PageInfo<News> pageInfo = null;
		try {
			PageHelper.startPage(page, rows);

			List<News> news = this.newsMapper.queryList();
			pageInfo = new PageInfo<News>(news);
		} catch (Exception e) {
			DataSourceTypeManager.reset();
			e.printStackTrace();
		}
		if (pageInfo != null)
			return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
		return null;
	}

	public void saveNews(News news) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_MAIN);

		news.setId(null);
		news.setCreateTime(BasicDateUtil.getCurrentDateTimeString());
		news = newsWordsAnalyse.decorateNews(news);
		DataSourceTypeManager.reset();

		this.newsMapper.insertSelective(news);
	}

}
