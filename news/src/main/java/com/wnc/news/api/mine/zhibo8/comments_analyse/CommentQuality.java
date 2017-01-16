package com.wnc.news.api.mine.zhibo8.comments_analyse;

public enum CommentQuality {
	Super(0), Great(10), Good(20), Bad(30), Awful(40), Default(100);
	public int getQuality() {
		return q;
	}

	int q;

	CommentQuality(int q) {
		this.q = q;
	}
}
