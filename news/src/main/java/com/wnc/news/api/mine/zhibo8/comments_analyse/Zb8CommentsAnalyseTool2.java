package com.wnc.news.api.mine.zhibo8.comments_analyse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.wnc.news.api.common.Comment;
import com.wnc.news.api.mine.zhibo8.Zb8CommentsUtil2;

public class Zb8CommentsAnalyseTool2 {
	String url;
	List<Comment> hotComments = new ArrayList<Comment>();
	int allCommentsCount;
	Logger logger = Logger.getLogger(Zb8CommentsAnalyseTool2.class);

	public int getHotCommentCount() {
		checkComments();
		return hotComments.size();
	}

	public int getAllCommentCount() {
		checkComments();
		return allCommentsCount;
	}

	public Zb8CommentsAnalyseTool2(String url) {
		this.url = url;
	}

	public static void main(String[] args) throws Exception {
		String url2 = "http://news.zhibo8.cc/nba/2017-01-12/83002.htm";
		url2 = "http://news.zhibo8.cc/nba/2017-01-12/83005.htm";
		new Zb8CommentsAnalyseTool2(url2).analyseComments();
	}

	private List<Comment> analyseComments() {
		checkComments();
		System.out.println("开始排序");
		resortComments();
		return hotComments;
	}

	private void checkComments() {
		try {
			if (hotComments.size() == 0)
				hotComments = Zb8CommentsUtil2.getHotComments(url);
		} catch (Exception e) {
			logger.info(this.url + " 获取热评信息失败!", e);
			e.printStackTrace();
		}
		try {
			if (allCommentsCount == 0)
				allCommentsCount = Zb8CommentsUtil2.getAllCommentsCount(url);
		} catch (Exception e) {
			logger.info(this.url + " 获取评论总数失败!", e);
			e.printStackTrace();
		}
	}

	public List<Comment> getTop5Comments(int top) {
		analyseComments();
		List<Comment> list = new ArrayList<Comment>();
		for (int i = 0; i < hotComments.size(); i++) {
			Comment cm = hotComments.get(i);
			if (i < top || cm.getPriority() < CommentQuality.Good.getQuality()) {
				System.out.println(cm.getUserName() + " / " + cm.getContent());
				list.add(cm);
			}
		}
		return list;
	}

	public void analyse(Comment comment) {
		BaseAnalyse analyse1 = new SuperAnalyse(comment);
		BaseAnalyse analyse2 = new GreatAnalyse(comment);
		BaseAnalyse analyse3 = new GoodAnalyse(comment);
		BaseAnalyse analyse4 = new BadAnalyse(comment);
		BaseAnalyse analyse5 = new AwfulAnalyse(comment);
		analyse1.setNext(analyse2);
		analyse2.setNext(analyse3);
		analyse3.setNext(analyse4);
		analyse4.setNext(analyse5);
		analyse1.proceed();
	}

	private List<Comment> resortComments() {
		for (Comment comment : hotComments) {
			analyse(comment);
		}
		Collections.sort(hotComments, new Comparator<Comment>() {
			@Override
			public int compare(Comment o1, Comment o2) {
				int i = o1.getPriority() - o2.getPriority();
				if (i == 0) {
					if (o1.getDown() == 0) {
						return -1;
					}
					if (o2.getDown() == 0) {
						return 1;
					}
					if (o1.getUp() / o1.getDown() == o2.getUp() / o2.getDown()) {
						return o2.getUp() - o1.getUp();
					}
					return (o2.getUp() / o2.getDown()) - (o1.getUp() / o1.getDown());
				}
				return i;
			}
		});
		return hotComments;
	}
}
