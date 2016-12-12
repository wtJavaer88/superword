package com.wnc.superword.manage.db;

public class DataSourceTypeManager {
	private static final ThreadLocal<String> dataSourceTypes = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return DataSourceType.DATASOURCE_MAIN.getName();
		}
	};

	public static String get() {
		String string = dataSourceTypes.get();
		System.out.println("get..." + string);
		return string;
	}

	public static void set(DataSourceType dataSourceType) {
		dataSourceTypes.set(dataSourceType.getName());
	}

	public static void reset() {
		set(DataSourceType.DATASOURCE_MAIN);
	}
}