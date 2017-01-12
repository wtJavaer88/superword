package com.wnc.news.api.mine.zhibo8.comments_analyse;

import com.wnc.news.api.common.Comment;

public class AwfulAnalyse extends BaseAnalyse {

	public AwfulAnalyse(Comment comment) {
		super(comment);
		this.quality_tip = "开始火拼";
		this.priority = 40;
	}

	@Override
	public boolean isFit() {
		int i = comment.getUp() / comment.getDown();
		if (i < 5) {
			return true;
		}
		return false;
	}

}