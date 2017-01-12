package com.wnc.news.api.mine.zhibo8.comments_analyse;

import com.wnc.news.api.common.Comment;

public class GreatAnalyse extends BaseAnalyse {

	public GreatAnalyse(Comment comment) {
		super(comment);
		this.quality_tip = "大受欢迎";
		this.priority = 10;
	}

	@Override
	public boolean isFit() {
		int i = comment.getUp() / comment.getDown();
		if (i >= 20 && i < 50) {
			return true;
		}
		return false;
	}

}