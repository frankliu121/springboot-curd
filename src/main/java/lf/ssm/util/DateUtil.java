package lf.ssm.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
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

	/**
	 * 日期转字符
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
		return sdf.format(date);
	}

	/**
	 * 日期转字符
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToStr(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
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


	public static Date getEndDateTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 获取今天的结束时间
	 * @return
	 */
	public static Date getTodayEnd(){
		return DateUtil.stringToDate(DateUtil.getNowDate(DateUtil.DATE_FORMAT_YYYY_MM_DD + " 23:59:59"), DateUtil.DATE_FORMAT_NORMAL);
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
	 * 获取指定日期的毫秒
	 * @param time
	 * @return
	 */
	public static Long getMiliseconds(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	/**
	 * 获取指定日期的毫秒数
	 */
	public static long getMiliseconds(Date date){
		return date.getTime();
	}
	/*--------时间戳方法end--------*/

	/**
	 * 日期添加天数
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}


	/**
	 * 格式化Unix to LocalDateTime
	 *
	 * @param unixTimeInMilliSecond
	 * @param pattern
	 * @return
	 */
	public static String formatUnixTimeToLocalDateTime(long unixTimeInMilliSecond, String pattern) {
		LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimeInMilliSecond), ZoneOffset.ofHours(8));
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * LocalDateTime转Date.
	 *
	 * @param time
	 * @return java.util.Date
	 */
	public static Date localDateTimeToDate(LocalDateTime time) {
		return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Date转LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime DateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}


	/**
	 * 获取指定日期的秒.
	 *
	 * @param time
	 * @return java.lang.Long
	 */
	public static Long getSecondsByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	/**
	 * 获取指定时间的指定格式.
	 *
	 * @param time
	 * @param pattern
	 * @return java.lang.String
	 */
	public static String formatTime(LocalDateTime time, String pattern) {
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 获取当前时间的指定格式.
	 *
	 * @param pattern
	 * @return java.lang.String
	 */
	public static String formatNow(String pattern) {
		return formatTime(LocalDateTime.now(), pattern);
	}

	/**
	 * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*.
	 *
	 * @param time
	 * @param number
	 * @param field
	 * @return java.time.LocalDateTime
	 */
	public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
		return time.plus(number, field);
	}

	/**
	 * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*.
	 *
	 * @param time
	 * @param number
	 * @param field
	 * @return java.time.LocalDateTime
	 */
	public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
		return time.minus(number, field);
	}

	/**
	 * 获取两个日期的差  field参数为ChronoUnit.*.
	 *
	 * @param startTime
	 * @param endTime
	 * @param field
	 * @return long
	 */
	public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
		Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
		if (field == ChronoUnit.YEARS) {
			return period.getYears();
		}
		if (field == ChronoUnit.MONTHS) {
			return period.getYears() * 12L + period.getMonths();
		}
		return field.between(startTime, endTime);
	}

	/**
	 * 获取一天的开始时间，2017,7,22 00:00.
	 *
	 * @param time
	 * @return java.time.LocalDateTime
	 */
	public static LocalDateTime getDayStart(LocalDateTime time) {
		return time.withHour(0)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);
	}

	/**
	 * 获取一天的结束时间，2017,7,22 23:59:59.999999999.
	 *
	 * @param time
	 * @return java.time.LocalDateTime
	 */
	public static LocalDateTime getDayEnd(LocalDateTime time) {
		return time.withHour(23)
				.withMinute(59)
				.withSecond(59)
				.withNano(999999999);
	}



	public static void main(String[] args) throws Exception {
		Date date = new Date();
		String s = dateToStr(date);
		System.out.println(s);

	}
}