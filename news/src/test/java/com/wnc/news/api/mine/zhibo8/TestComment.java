package com.wnc.news.api.mine.zhibo8;

import java.util.List;

import com.wnc.news.api.common.Comment;
import com.wnc.news.api.mine.zhibo8.comments_analyse.Zb8CommentsAnalyseTool;
import com.wnc.news.api.mine.zhibo8.comments_analyse.GoodAnalyse;

public class TestComment {
	// @Test
	public void getHotCommentsByDay() throws Exception {
		String day = "2017-01-01";
		List<Zb8News> nbaNewsByDay = new NewsExtract().getNewsAfterDay(day, SportType.Zuqiu);
		System.out.println(nbaNewsByDay.size());
		for (Zb8News zb8News : nbaNewsByDay) {
			String url = zb8News.getUrl();
			System.out.println(url);
			System.out.println("评论总数:" + Zb8CommentsUtil.getAllCommentsCount(url));
			List<Comment> resortComments = new Zb8CommentsAnalyseTool(url).getTop5Comments(10000000);
			// System.out.println(resortComments);
			for (int i = 0; i < 5 && i < resortComments.size(); i++) {
				Comment cm = resortComments.get(i);
				if (cm.getPriority() <= GoodAnalyse.getPriority()) {
					System.out.println(cm.getUserName() + " / " + cm.getContent());
				}
			}
		}
	}
}
