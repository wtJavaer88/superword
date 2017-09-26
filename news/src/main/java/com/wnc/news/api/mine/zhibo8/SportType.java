package com.wnc.news.api.mine.zhibo8;

public enum SportType {
	Soccer(1, "soccer"), Zuqiu(1, "zuqiu"), NBA(2, "nba");
	int id;
	String desc;

	public String getDesc() {
		return desc;
	}

	public int getId() {
		return id;
	}

	SportType(int id, String desc) {
		this.desc = desc;
	}
}
