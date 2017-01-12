package com.wnc.news.api.mine.zhibo8;

public enum SportType {
	Zuqiu("zuqiu"), NBA("nba");
	String desc;

	public String getDesc() {
		return desc;
	}

	SportType(String desc) {
		this.desc = desc;
	}
}
