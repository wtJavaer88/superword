package com.wnc.news.api.mine.zhibo8.comments_analyse;

import com.wnc.news.api.common.Comment;

public class BadAnalyse extends BaseAnalyse {

	public BadAnalyse(Comment comment) {
		super(comment);
		this.quality_tip = "争议不断";
		this.priority = CommentQuality.Bad.getQuality();
	}

	@Override
	public boolean isFit() {
		int i = comment.getUp() / comment.getDown();
		if (i >= 5 && i < 10) {
			return true;
		}
		return false;
	}

}