package org.txazo.java.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

	/** 一小时的毫秒数 */
	private static final int DATE_HOUR_MILLIS = 3600000;
	/** 一天的毫秒数 */
	private static final int DATE_DAY_MILLIS = 86400000;

	/** 默认DateFormat */
	private static final DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 返回当前日期
	 */
	public static String getCurrentDateTime() {
		return defaultDateFormat.format(new Date());
	}

	/**
	 * 两日期相隔的小时数
	 */
	public static int differHoursOfDate(Date pre, Date rear) {
		long c = rear.getTime() - pre.getTime();
		return (int) c / DATE_HOUR_MILLIS;
	}

	/**
	 * 两日期相隔的天数
	 */
	public static int differDaysOfDate(Date pre, Date rear) {
		long c = rear.getTime() - pre.getTime();
		return (int) c / DATE_DAY_MILLIS;
	}

}
