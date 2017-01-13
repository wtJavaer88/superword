package com.wnc.news.api.mine.zhibo8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wnc.basic.BasicDateUtil;

public class NewsExtract {
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

	public List<Zb8News> getNewsAfterDay(String someday, int count, SportType type) throws Exception {
		String day2 = formatDay(BasicDateUtil.getDateAfterDayDateString(someday.replace("-", ""), count));
		return getNewsBetweenTwoDays(someday, day2, type);
	}

	public List<Zb8News> getNewsAfterDay(String someday, SportType type) throws Exception {
		String day2 = BasicDateUtil.getCurrentDateString();
		day2 = formatDay(day2);
		return getNewsBetweenTwoDays(someday, day2, type);
	}

	public List<Zb8News> getNewsBetweenTwoDays(String day1, String day2, SportType type) throws Exception {
		if (day1.length() == 8) {
			day1 = day1.substring(0, 4) + "-" + day1.substring(4, 6) + "-" + day1.substring(6);
		}
		if (day2.length() == 8) {
			day2 = day2.substring(0, 4) + "-" + day2.substring(4, 6) + "-" + day2.substring(6);
		}
		List<Zb8News> list = Collections.synchronizedList(new ArrayList());
		if (BasicDateUtil.dateAfterDate(day1, day2, "yyyy-mm-dd")) {
			return list;
		}
		String loopDay = day1;
		while (loopDay.compareTo(day2) <= 0) {
			executor.execute(new DayNewsThread(loopDay, list, type));
			loopDay = getNextDay(loopDay);
		}
		executor.shutdown();
		waiting(executor);
		return list;
	}

	public List<Zb8News> getNewsByYearMonth(int year, int month, SportType type) throws Exception {
		int days = 31;
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			days = 30;
		}
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				days = 29;
			} else {
				days = 28;
			}
		}
		return getNewsBetweenTwoDays(year + "-" + alignInt(month) + "-" + "01",
				year + "-" + alignInt(month) + "-" + alignInt(days), type);
	}

	public List<Zb8News> getZuqiuNewsByDay(String day) throws Exception {
		List<Zb8News> list = Collections.synchronizedList(new ArrayList());
		new DayNewsThread(day, list, SportType.Zuqiu).run();
		return list;
	}

	public List<Zb8News> getNBANewsByDay(String day) throws Exception {
		List<Zb8News> list = Collections.synchronizedList(new ArrayList());
		new DayNewsThread(day, list, SportType.NBA).run();
		return list;
	}

	private String getNextDay(String someday) {
		someday = BasicDateUtil.getDateAfterDayDateString(someday.replace("-", ""), 1);
		someday = formatDay(someday);
		return someday;

	}

	private String formatDay(String someday) {
		someday = someday.substring(0, 4) + "-" + someday.substring(4, 6) + "-" + someday.substring(6, 8);
		return someday;
	}

	private String alignInt(int number) {
		return number >= 10 ? number + "" : "0" + number;
	}

	private void waiting(ThreadPoolExecutor executor) {
		try {
			boolean loop = true;
			do { // 等待所有任务完成
				loop = !executor.awaitTermination(2, TimeUnit.SECONDS); // 阻塞，直到线程池里所有任务结束
			} while (loop);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
