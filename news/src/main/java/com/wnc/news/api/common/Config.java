package com.wnc.news.api.common;

public class Config {

	private static Config singletonMyAppParams = new Config();

	public static Config getInstance() {
		return singletonMyAppParams;
	}

	private Config() {
	}

	public String getSoccModelName() {
		return "";
	}

	public String getBaskModelName() {
		return "";
	}

	public String getVoaModelName() {
		return "VOA";
	}

}
