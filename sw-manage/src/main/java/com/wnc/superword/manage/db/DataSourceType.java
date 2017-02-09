package com.wnc.superword.manage.db;

public enum DataSourceType {
	DATASOURCE_MAIN("ds_main"), DATASOURCE_DICT("ds_dict"), DATASOURCE_ZB8("ds_zb8"), DATASOURCE_XXIN(
			"ds_xxin"), DATASOURCE_BBEI("ds_bbei");
	private String name;

	DataSourceType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}