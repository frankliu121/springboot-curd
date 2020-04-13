package lf.ssm.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @version 1.0
 * @author pastry
 */
public class DateUtil {

	public static String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	public static String DATE_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";

	public static String DATE_FORMAT_QUERY = "yyyy-MM-dd HH:mm";

	public static String DATE_FORMAT_DATE = "yyyy年MM月dd日";

	public static String DATE_FORMAT = "yyyyMMdd";

	public static String DATE_FORMAT_yyyy_MM_dd = "yyyy/MM/dd";

	public static String DATE_FORMAT_YYYY_MM = "yyyyMM";

	public static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static String DATE_FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

	public static String DATE_FORMAT_YYYYMMDDHHmmss = "yyyyMMddHHmmss";

	public static String DATE_FORMAT_YYYYMMDDHH = "yyyyMMddHH";

	public static String DATE_FORMAT_YYYYMMDDHH_POINT = "yyyy.MM.dd";

	public static String DATE_FORMAT_NORMAL_CN = "yyyy年MM月dd日HH:mm:ss";

	public static String DATE_FORMAT_YYYYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";

	public static String DATE_FORMAT_FULL_WORLD = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/**
	 * 一小时，多五分钟
	 */
	public static Long DAY_OF_MILLIS_TIME = 24 * 60 * 60 * 1000L + 5 * 60 * 1000L;

	// 用来全局控制 上一周，本周，下一周的周数变化
	private static int weeks = 0;

	/**
	 * 将字符串转换为Date日期
	 * @param strDate 日期字符串
	 * @return
	 */
	public static Date stringToDate(String strDate, String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			return format.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串转换为Date日期
	 * @param strDate 日期字符串
	 * @return
	 */
	public static Date worldStrToDate(String strDate, String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			return format.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取传入时间的前一天整点时间戳
	 * @param d
	 * @return
	 */
	public static long getYesterdayTimestamp(Date d) {
		Date dNow = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTimeInMillis()/1000;
	}

	/**
	 * 获取传入时间的前一天,以format显示
	 * @param d 		日期
	 * @param format 	日期格式
	 * @return
	 */
	public static String getYesterdayTimestamp(Date d, String format) {
		Date dNow = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formats = new SimpleDateFormat(format);
		return formats.format(new Date(calendar.getTimeInMillis()));
	}

	/** 获取当前月天数 **/
	public static int getDayOfMonth(){
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		int day = calendar.getActualMaximum(Calendar.DATE);
		return day;
	}

	/** 获取传入月份天数 **/
	public static int getDayOfMonth(int month){

		Calendar time=Calendar.getInstance();
		time.clear();
		time.set(Calendar.YEAR, time.get(Calendar.YEAR));
		//year年
		time.set(Calendar.MONTH,month-1);
		//Calendar对象默认一月为0,month月
		return time.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/** 获取当前月份 **/
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/** 获取当前年份 **/
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/** 获取当前几号 **/
	public static int getCurrentDate() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	/**
	 * 判断当前时间是否在时间段内
	 * @param startDate		开始时间
	 * @param endDate		结束时间
	 * @param currentDate	当前时间
	 */
	public static boolean isBetweenDate(Date startDate, Date endDate, Date currentDate) {
		if(currentDate.compareTo(startDate) >= 0 && endDate.compareTo(currentDate) >= 0) {
			return true;
		}
		return false;
	}

	public static Long timeDifference(Date d){
		Date dNow = new Date();
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long between = 0;
		try {
			Date begin = dNow;
			between = (d.getTime() - begin.getTime())/1000;// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return between;
	}

	public static Calendar formatRDate(String strDate) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Calendar cal = Calendar.getInstance();
		try {
			date = sdf2.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		return cal;
	}

	public static String dateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_FULL);
		return format.format(date);
	}

	public static String currentSSStr(Date d, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(d);
	}

	/**
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_QUERY);
		return format.format(date);
	}

	public static String dateToStrNormal(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		return format.format(date);
	}

	public static Date strToDate(String strDate) {
		SimpleDateFormat dtFormat = null;
		try {
			if (strDate.length() == DATE_FORMAT_QUERY.length()) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_QUERY);
			} else if (strDate.length() == DATE_FORMAT_YYYY_MM_DD.length()) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
			} else if (strDate.length() == DATE_FORMAT_NORMAL.length()) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_NORMAL);
			}

			return dtFormat.parse(strDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date stirngToDate(String strDdate, String format) {
		SimpleDateFormat dtFormat = null;

		try {
			if (format.equals(DATE_FORMAT_FULL)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_FULL);
			} else if (format.equals(DATE_FORMAT_NORMAL)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_NORMAL);
			}  else if (format.equals(DATE_FORMAT_QUERY)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_QUERY);
			} else if (format.equals(DATE_FORMAT_DATE)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_DATE);
			}  else if (format.equals(DATE_FORMAT)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT);
			} else if (format.equals(DATE_FORMAT_YYYY_MM_DD)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
			}else if (format.equals(DATE_FORMAT_YYYYMMDDHHmmss)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHmmss);
			}else if (format.equals(DATE_FORMAT_yyyy_MM_dd)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd);
			}else if (format.equals(DATE_FORMAT_FULL_WORLD)) {
                dtFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd);
            }else if (format.equals(DATE_FORMAT_FULL_WORLD)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_FULL_WORLD);
			}

			return dtFormat.parse(strDdate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取时间描述
	 */
	public static String getDateSpoken() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 6 && hour < 8) {
			return "早上";
		} else if (hour >= 8 && hour < 11) {
			return "上午";
		} else if (hour >= 11 && hour < 13) {
			return "中午";
		} else if (hour >= 13 && hour < 18) {
			return "下午";
		} else {
			return "晚上";
		}
	}

	public static String getNowDate() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DATE);
		return format.format(new Date());
	}


	public static String getNowDate(String dateFormate) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormate);
		return format.format(new Date());
	}

	public static String getNowDay() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(new Date());
	}

	/**
	 * 获取明天
	 */
	public static String getTomorrowDay() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, 1);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(cd.getTime());
	}

	/**
	 * 获取昨天
	 */
	public static Date getYesterday() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, -1);
		return cd.getTime();
	}


	/**
	 * 获取昨天
	 */
	public static Date getTomorrow() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, 1);
		return cd.getTime();
	}

	/**
	 * 几小时前
	 */
	public static Date getBeforeHour(int hour) {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.HOUR, hour);
		return cd.getTime();
	}

	/**
	 * 获取几天前
	 * 负数：前几天
	 * 证书：后几天
	 */
	public static Date getBeforDay(Integer day) {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, day * (-1));
		return cd.getTime();
	}

	/**
	 * 获取当天凌晨时间
	 */
	public static Date getNowDayZero() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		return  cal.getTime();
		// return format.format(cal.getTime());
	}

	/**
	 * 获取昨天
	 */
	public static String getYesterdayStr() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, -1);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(cd.getTime());
	}


	/**
	 * 获取几分钟前
	 */
	public static Date getBeforeMin(int min) {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.MINUTE, min);
		return cd.getTime();
	}

	/**
	 * 获取几分钟前(正为后,负为前)
	 */
	public static Date getBeforeMin(int min,Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.MINUTE, min);
		return cd.getTime();
	}

	/**
	 * 获得本年第一天的日期
	 */
	public static String getCurrentYearFirstDate() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		// DateFormat df = DateFormat.getDateInstance();
		String preYearDay = format.format(yearDay);
		return preYearDay;
	}

	/**
	 * 获取当年的第一天
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst(){
		Calendar currCal=Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast(){
		Calendar currCal=Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	public static Date getExpireDate(int month) {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.MONTH, month);
		return cd.getTime();
	}

	public static String getCNDate(Date lgesSigndate) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NORMAL_CN);
		return format.format(lgesSigndate);
	}

	public static String strDate2NowDay(String strDate) {
		Date date = strToDate(strDate);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(date);
	}

	public static String strDate2NowDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(date);
	}

	// 获得当前日期与本周一相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	// 获得上周星期一的日期
	public static String getPreviousMonday() {
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		String preMonday = format.format(monday);
		return preMonday;
	}

	// 获得当前周星期一的日期
	public static String getCurrentMonday() {
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		String preMonday = format.format(monday);
		return preMonday;
	}

	// 获得下周星期一的日期
	public static String getNextMonday() {
		weeks++;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		String preMonday = format.format(monday);
		return preMonday;
	}

	/**
	 * 返回后几天的日期
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date getNextDate(Date date, int i ) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}


	// 获得当周的周日的日期
	public static String getSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		String preMonday = format.format(monday);
		return preMonday;
	}
	/**
	 * 得到某年某月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到当年1月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, 1-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到当年12月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, 12-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到某年某月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}


	/**
	 * 得到某年某月的前某个月的月数
	 * @param pre 前几个月
	 * @return
	 */
	public static int getLastDayOfMonth(int pre) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -pre);
		// 因为是0~11月  所以 加1 好是 1~12月
		return (c.get(Calendar.MONTH)+1);
	}

	/**
	 * 得到某年某月的前某个月的月数
	 * @param pre 前4个月
	 * @return
	 */
	public static String getPreMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -4);
		return new SimpleDateFormat("yyyy-MM-01").format(c.getTime());
	}

	/**
	 * 获取当前月的第一天
	 * @param pre 当前月1号  yyyy-MM-01
	 * @return
	 */
	public static String getCurrentMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -0);
		return new SimpleDateFormat("yyyy-MM-01").format(c.getTime());
	}

	/**
	 * 获取当前月的最后一天
	 * @param pre 当前月1号  yyyy-MM-01
	 * @return
	 */
	public static String getCurrentMonthLastDay() {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为1号,当前日期既为本月第一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
	}

	/**
	 * 判断是否是周末
	 * @return
	 */
	public static boolean isWeekend(){
		Calendar cal = Calendar.getInstance();
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){// week ==6 ||  0代表周日，6代表周六
			return true;
		}
		return false;
	}

	/**
	 * 获取今天  N年前/后的时间
	 * @param years
	 * @return
	 */
	public static Date todayAfterYear(int years){
		Calendar calendar = Calendar.getInstance();

		Date date = new Date(System.currentTimeMillis());

		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);

		return date = calendar.getTime();
	}

	/**
	 * 获取某天  N年前/后的时间
	 * @param years
	 * @return
	 */
	public static Date thisDayAfterYear(int years, Date date){
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);

		return date = calendar.getTime();
	}

	public static String dateToStr(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}


	/**
	 * 获取昨天的16:00
	 * @return
	 */
	public static long getBeginTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}

	/**
	 * 获取当天的16:00
	 * @return
	 */
	public static long getEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTimeInMillis();
	}


	/**
	 *  获取当天的16:00
	 * @return
	 */
	public static Date getStartDateTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		return c.getTime();
	}


	public static Date getEndDateTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}


	public static Date getTodayEnd(){
		return DateUtil.stringToDate(DateUtil.getNowDate(DateUtil.DATE_FORMAT_YYYY_MM_DD + " 23:59:59"), DateUtil.DATE_FORMAT_NORMAL);
	}

	// 获取传入时间的周一
	public static Date getDayMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	// 获取传入时间的周日
	public static Date getDaySunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}

	// 获取某个时间的周一和周日
	public static Date[] getMondayAndSunday(Date startDate, Date endDate) {
		Date[] dates = new Date[3];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		if(calendar.getTime().after(endDate)) {
			dates[1] = endDate;
		} else {
			dates[1] = calendar.getTime();
		}

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		if(calendar.getTime().before(startDate)) {
			dates[0] = startDate;
		} else {
			dates[0] = calendar.getTime();
		}

		calendar.add(Calendar.WEEK_OF_MONTH, 1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dates[2] = calendar.getTime();
		return dates;
	}

	// 获取2个日期天数
	public static int daysOfTwo(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}


	/**
	 * 判断是否为周一
	 */
	public static boolean isMonday(String dateStr) {
		Date d = stringToDate(dateStr, DATE_FORMAT_YYYY_MM_DD);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(week == 1) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 判断是否为1号
	 */
	public static boolean isMonthNo1(String dateStr) {
		if(StringUtils.isNotBlank(dateStr)) {
			StringBuilder sb = new StringBuilder();
			sb.append(dateStr.substring(0, 8)).append("01");
			if(sb.toString().equals(dateStr)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前日期上一个月日期
	 */
	public static String getLastMonthDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		return formats.format(new Date(calendar.getTimeInMillis()));
	}

	/**
	 * 按指定格式化日期
	 * @author liufeng
	 * @date 2020/4/10 21:50
	 * @return java.lang.String
	 */
	public static String formatDate(Date date,String format){
		SimpleDateFormat formats = new SimpleDateFormat(format);
		return formats.format(date);
	}

	/**
	 * 按yyyy-MM-dd HH:mm:ss格式化日期
	 * @author liufeng
	 * @date 2020/4/10 21:50
	 * @return java.lang.String
	 */
	public static String formatDate(Date date){
		if (date == null){
			return "";
		}
		SimpleDateFormat formats = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		return formats.format(date);
	}

	/**
	 * 格式化传入的日期
	 * @param d 		日期
	 * @param format 	日期格式
	 * @return
	 */
	public static String formatDate(String d, String format) {
		SimpleDateFormat formats = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		Date date=null;
		try {
			date=formats.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat(format).format(date);
	}


	/*--------时间戳方法start--------*/

	/**
	 *  获取当前时间到1970.1.1的秒数
	 */
	public static long getCurrSeconds() {
		return new Date().getTime() / 1000;
	}

	/**
	 * 返回从1970年1月1日到现在的总毫秒数 ,时间戳
	 */
	public static long getCurrMiliseconds(){
		return System.currentTimeMillis();
	}

	/**
	 * 返回Date类型，milisecond,毫秒
	 */
	public static Date getDate(long milisecond) throws ParseException{
		SimpleDateFormat formats = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		String d = formats.format(milisecond);
		Date date=formats.parse(d);
		return date;

	}

	/**
	 * 返回Date类型，milisecond,毫秒
	 */
	public static Date getDateByTime(long milisecond) throws ParseException{
		SimpleDateFormat formats = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHmmssSSS);
		String d = formats.format(milisecond);
		Date date=formats.parse(d);
		return date;

	}

	/**
	 *
	 * <br>获取当天剩余的秒数</br>
	 *
	 * @author    515120
	 * @email     yusai@dafycredit.com
	 * @date      2017年6月19日 下午7:37:13
	 * @version   1.0
	 * @since     1.0
	 * @return
	 */
	public static int getSecondsRemainingofNowDate(){
		Calendar curDate = Calendar.getInstance();
		Calendar tommorowDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE) + 1, 0, 0, 0);
		return (int)(tommorowDate.getTimeInMillis()-curDate.getTimeInMillis())/1000;
	}

	/**
	 * <br>TODO(获取响应时间)</br>
	 *
	 * @author    516649
	 * @email     zhouqian@dafycredit.com
	 * @date      2017年8月21日 上午11:18:52
	 * @version   1.0
	 * @since     1.0
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getSpendTime(long start ,long end) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(end - start);
		String spendTime =String.format("%s分%s秒%s毫秒", c.get(Calendar.MINUTE),c.get(Calendar.SECOND),c.get(Calendar.MILLISECOND));
		return spendTime;
	}

	/**
	 * 时间戳是否在24小时之内
	 * @param millisTime
	 * @return
	 */
	public static Boolean isIn24Hour(Long millisTime) {
		Long currentTime = System.currentTimeMillis();
		return currentTime - millisTime <= DAY_OF_MILLIS_TIME;
	}

	/**
	 * 判断time是否在from，to之内
	 *
	 * @param time 指定日期
	 * @param from 开始日期
	 * @param to   结束日期
	 * @return
	 */
	public static boolean belongCalendar(Date time, Date from, Date to) {
		Calendar date = Calendar.getInstance();
		date.setTime(time);

		Calendar after = Calendar.getInstance();
		after.setTime(from);

		Calendar before = Calendar.getInstance();
		before.setTime(to);

		if (date.after(after) && date.before(before)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 返回时间戳 
	 */
	public static long getMiliseconds(Date date){
		return date.getTime();
	}
	/*--------时间戳方法end--------*/

	public static Date addDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(new Date().getTime());
//		System.out.println(getCurrentDate());
//		System.out.println(getYesterdayStr());
//		System.out.println(daysOfTwo(strToDate("2016-01-14"), strToDate("2016-02-29")));
//		System.out.println("2016-01-02".substring(0, 8));
//		long aa=getCurrMiliseconds();
//		System.out.println(aa);
//		System.out.println(getDate(aa));
//		Date date=new Date();
//		System.out.println(getMiliseconds(date));
//		Date toDate=  DateUtil.stirngToDate("2016/07/30", "yyyy/MM/dd");
//	    System.out.println(DateUtil.stirngToDate("2017/07/06", "yyyy/MM/dd"));
//		System.out.println(DateUtil.dateToStr(new Date(), DateUtil.DATE_FORMAT));
//		System.out.println(daysOfTwo(new Date(), toDate));
//
//		System.out.println(date2Str(DateUtil.getCurrYearFirst()) );
//		System.out.println(date2Str(DateUtil.getCurrYearLast()) );
//		System.out.println(DateUtil.getMiliseconds(DateUtil.getCurrYearFirst()));
//		System.out.println(DateUtil.getMiliseconds(DateUtil.getCurrYearLast()));
//		DateUtil.getCurrYearFirst();
//		DateUtil.getCurrYearLast();

//		System.out.print(DateUtil.getBeginTime(new Date()));


//		DateUtil.dateToStrNormal(DateUtil.getBeginTime(new Date()));


//		System.out.println(DateUtil.dateToStrNormal(DateUtil.getStartDateTime(new Date())));
//		System.out.println(DateUtil.dateToStrNormal(DateUtil.getStartDateTime(DateUtil.getTomorrow())));

	//	String date = "2018-12-18";
	//	System.out.println(DateUtil.dateToStr(DateUtil.addDay(DateUtil.stringToDate(date, "yyyy-MM-dd"), -7), "yyyy-MM-dd"));

//		System.out.print(DateUtil.getBeginTime(new Date()));


	}
}