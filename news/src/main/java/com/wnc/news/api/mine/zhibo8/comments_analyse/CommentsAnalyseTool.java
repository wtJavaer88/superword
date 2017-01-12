package com.wnc.news.api.mine.zhibo8.comments_analyse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wnc.news.api.common.Comment;
import com.wnc.news.api.mine.zhibo8.Zb8CommentsUtil;

public class CommentsAnalyseTool {
	Comment comment;
	String url;

	public CommentsAnalyseTool() {
	}

	public CommentsAnalyseTool(String url) {
		this.url = url;
	}

	public static void main(String[] args) throws Exception {
		String url2 = "http://news.zhibo8.cc/nba/2017-01-12/83002.htm";
		url2 = "http://news.zhibo8.cc/nba/2017-01-12/83005.htm";
		new CommentsAnalyseTool(url2).analyse();
	}

	public void analyse() throws Exception {
		List<Comment> hotComments = Zb8CommentsUtil.getHotComments(url);
		System.out.println("开始排序");
		List<Comment> resortComments = resortComments(hotComments);
		for (Comment comment : resortComments) {
			System.out.println(comment.getPriority() + " / " + comment.getContent() + " " + comment.getUp() + " "
					+ comment.getDown());
		}
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

	public List<Comment> resortComments(List<Comment> comments) {
		for (Comment comment : comments) {
			analyse(comment);
		}
		Collections.sort(comments, new Comparator<Comment>() {
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
		return comments;
	}
}
