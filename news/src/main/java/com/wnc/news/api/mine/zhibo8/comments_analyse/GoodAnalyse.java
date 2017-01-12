package com.wnc.news.api.mine.zhibo8.comments_analyse;

import com.wnc.news.api.common.Comment;

public class GoodAnalyse extends BaseAnalyse {

	public GoodAnalyse(Comment comment) {
		super(comment);
		this.quality_tip = "语言得体";
		this.priority = 20;
	}

	@Override
	public boolean isFit() {
		int i = comment.getUp() / comment.getDown();
		if (i >= 10 && i < 20) {
			return true;
		}
		return false;
	}

}