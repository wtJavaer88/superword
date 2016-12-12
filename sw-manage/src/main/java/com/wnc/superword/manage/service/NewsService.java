package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.basic.BasicDateUtil;
import com.wnc.superword.manage.mapper.NewsMapper;
import com.wnc.superword.manage.pojo.News;
import com.wnc.superword.manage.util.NewsWordsAnalyse;
import com.wnc.utils.EasyUIResult;

@Service
public class NewsService extends BaseService<News> {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsWordsAnalyse newsWordsAnalyse;

	public EasyUIResult queryList(Integer page, Integer rows) {
		// 设置分页参数
		PageHelper.startPage(page, rows);

		List<News> news = this.newsMapper.queryList();
		PageInfo<News> pageInfo = new PageInfo<News>(news);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}

	public void saveNews(News news) {
		news.setId(null);
		news.setCreateTime(BasicDateUtil.getCurrentDateTimeString());
		news = newsWordsAnalyse.decorateNews(news);
		this.newsMapper.insertSelective(news);
	}

}
