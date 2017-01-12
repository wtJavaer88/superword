package com.wnc.news.api.mine.zhibo8.comments_analyse;

import com.wnc.news.api.common.Comment;

public class SuperAnalyse extends BaseAnalyse {

	public SuperAnalyse(Comment comment) {
		super(comment);
		this.quality_tip = "人气爆棚";
		this.priority = 0;
	}

	@Override
	public boolean isFit() {
		if (comment.getDown() == 0 || comment.getUp() / comment.getDown() >= 50) {
			return true;
		}
		return false;
	}

}
