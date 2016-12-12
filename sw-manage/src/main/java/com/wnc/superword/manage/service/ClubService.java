package com.wnc.superword.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnc.news.api.common.NewsInfo;
import com.wnc.news.api.common.TeamApi;
import com.wnc.news.api.soccer.SkySportsTeamApi;
import com.wnc.news.api.soccer.SquawkaTeamApi;
import com.wnc.superword.manage.mapper.ClubMapper;
import com.wnc.superword.manage.pojo.Club;
import com.wnc.superword.manage.util.NewsProxy;
import com.wnc.utils.EasyUIResult;

@Service
public class ClubService extends BaseService<Club> {

	@Autowired
	private ClubMapper clubMapper;
	@Autowired
	private NewsService newsService;

	public EasyUIResult queryList(Integer page, Integer rows) {
		// 设置分页参数
		PageHelper.startPage(page, rows);

		List<Club> clubs = this.clubMapper.selectByExample(new Example(Club.class));
		PageInfo<Club> pageInfo = new PageInfo<Club>(clubs);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}

	public void cacheTeamNews(String team, String websitename, int maxpages) {
		List<NewsInfo> allNewsWithContent = null;
		TeamApi teamApi = null;
		if (websitename.equalsIgnoreCase("skysport")) {
			teamApi = new SkySportsTeamApi(team);
		} else if (websitename.equalsIgnoreCase("squawka")) {
			teamApi = new SquawkaTeamApi(team);
		}
		teamApi.setMaxPages(maxpages);
		allNewsWithContent = teamApi.getAllNewsWithContent();
		if (allNewsWithContent != null) {
			System.out.println("缓存" + team + "结果数" + allNewsWithContent.size());
			for (NewsInfo newsInfo : allNewsWithContent) {
				System.out.println(newsInfo.getTitle());
				newsService.saveNews(new NewsProxy(newsInfo).getRealNews());
			}
		}
	}
}
