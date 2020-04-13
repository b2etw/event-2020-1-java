package tw.b2e.receipt.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

	/** yyyyMMdd. */
	public final static String FORMAT_DATE = "yyyyMMdd";

	/** yyyy-MM-dd. */
	public final static String FORMAT_DB_DATE = "yyyy-MM-dd";

	/** yyyy-MM-dd HH:mm:ss. */
	public final static String FORMAT_DB_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/** yyyy-MM-dd HH:mm:ss.SSSSSS. */
	public final static String FORMAT_DB_FULL_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSSSSS";

	/** HH:mm:ss. */
	public final static String FORMAT_DB_TIME = "HH:mm:ss";

	/** yyyy-MM-dd HH:mm:ss.SSS. */
	public final static String FORMAT_DB_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	/** yyyy-MM-dd HH:mm:ssSSSS. */
	public final static String FORMAT_EAI_TIMESTAMP = "yyyy-MM-dd HH:mm:ssSSSS";

	/** yyyyMMdd-HHmmss. */
	public final static String FORMAT_MCHT_TIMESTAMP = "yyyyMMdd-HHmmss";

	/** HHmmss. */
	public final static String FORMAT_TIME = "HHmmss";

	/** yyyyMMddHHmmssSSS. */
	public final static String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

	/** yyyy/MM/dd. */
	public final static String FORMAT_WEST_DATE = "yyyy/MM/dd";

	/** yyyy 年 MM 月 dd 日. */
	public final static String FORMAT_WEST_DATE_CS = "yyyy 年 MM 月 dd 日";

	/** yyyy/MM/dd HH:mm:ss. */
	public final static String FORMAT_WEST_TIMESTAMP = "yyyy/MM/dd HH:mm:ss";

	final public static String INVOICE_DATE_S = "begin";

	final public static String INVOICE_DATE_E = "end";

	final public static int INVOIDE_JAN_FEB = 1;

	final public static int INVOIDE_MAR_APR = 3;

	final public static int INVOIDE_MAY_JUN = 5;

	final public static int INVOIDE_JUL_AUG = 7;

	final public static int INVOIDE_SEP_OCT = 9;

	final public static int INVOIDE_NOV_DEC = 11;

	/**
	 * 取得系統日期(預設yyyyMMdd)
	 * 
	 * @return 格式化日期
	 */
	public static String getSysDate() {
		SimpleDateFormat formater = new SimpleDateFormat(FORMAT_DATE);
		return formater.format(new Date());
	}

	/**
	 * 取得格式化日期
	 * 
	 * @param date   日期
	 * @param format date formate pattern
	 * @return
	 */
	public static String formateDate(Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}

	/**
	 * 根據日期字串取得日期
	 * 
	 * @param dateStr 日期字串
	 * @param format  date formate pattern
	 * @return 回傳日期
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr, String format) throws ParseException {
		return parseDate(dateStr, format, false);
	}

	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
		df.applyPattern(pattern);
		return df.format(date);
	}

	public static String format(Date date, String pattern, String locale) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat df = new SimpleDateFormat(pattern, getLocale(locale));
		return df.format(date);
	}
	
	public static Locale getLocale(String country) {
		if (country == null) {
			return Locale.getDefault();
		}
		Locale[] locales = Locale.getAvailableLocales();
		for (int idx = 0; idx < locales.length; idx++) {
			if (locales[idx].getCountry().equalsIgnoreCase(country)
					|| locales[idx].getDisplayCountry().equalsIgnoreCase(
							country)) {
				return locales[idx];
			}
		}
		return Locale.getDefault();
	}

	/**
	 * <pre>
	 * 依傳入的 pattern 取得對應的日期時間字串,
	 * &#64;param pattern 模式, see the following;
	 * <h4>Date and Time Patterns</h4><p>
	 *     Date and time formats are specified by <I>date and time pattern</I> strings.
	 * Within date and time pattern strings, unquoted letters from <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to
	 * <code>'z'</code> are interpreted as pattern letters representing the components of a date or time string.
	 * Text can be quoted using single quotes (<code>'</code>) to avoid interpretation.
	 * 
	 * <code>"''"</code> represents a single quote.
	 * 
	 *     All other characters are not interpreted; they're simply copied into the
	 * output string during formatting or matched against the input string during parsing.<p>
	 * The following pattern letters are defined
	 * (all other characters from <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to <code>'z'</code> are reserved):
	 * <table border=0 cellspacing=3 cellpadding=0 summary=
	"Chart shows pattern letters, date/time component, presentation, and examples.">
	 *     <tr bgcolor="#ccccff">
	 *         <th align="left">Letter</th>
	 *         <th align="left">Date or Time Component</th>
	 *         <th align="left">Presentation</th>
	 *         <th align="left">Examples</th>
	 *     </tr>
	 *     <tr><td><code>G</code></td><td>Era designator</td><td><a href=
	"#text">Text</a></td><td><code>AD</code></td></tr>
	 *     <tr bgcolor="#eeeeff"><td><code>y</code></td><td>Year</td><td><a href=
	"#year">Year</a></td><td><code>1996</code>; <code>96</code></td></tr>
	 *     <tr><td><code>M</code></td><td>Month in year</td><td><a href=
	"#month">Month</a></td><td><code>July</code>; <code>Jul</code>; <code>07</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>w</code></td><td>Week in year</td><td><a href=
	"#number">Number</a></td><td><code>27</code></td></tr>
	 *     <tr><td><code>W</code></td><td>Week in month</td><td><a href=
	"#number">Number</a></td><td><code>2</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>D</code></td><td>Day in year</td><td><a href=
	"#number">Number</a></td><td><code>189</code></td></tr>
	 *     <tr><td><code>d</code></td><td>Day in month</td><td><a href=
	"#number">Number</a></td><td><code>10</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>F</code></td><td>Day of week in month</td><td><a href=
	"#number">Number</a></td><td><code>2</code></td></tr>
	 *     <tr><td><code>E</code></td><td>Day in week</td><td><a href=
	"#text">Text</a></td><td><code>Tuesday</code>; <code>Tue</code></td></tr>
	 *     <tr bgcolor="#eeeeff"><td><code>a</code></td><td><a href=
	"#text">Text</a></td><td><code>PM</code></td></tr>
	 *     <tr><td><code>H</code></td><td>Hour in day (0-23)</td><td><a href=
	"#number">Number</a></td><td><code>0</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>k</code></td><td>Hour in day (1-24)</td></td><td><a href=
	"#number">Number</a></td><td><code>24</code></td></tr>
	 *     <tr><td><code>K</code></td><td>Hour in am/pm (0-11)</td><td><a href=
	"#number">Number</a></td><td><code>0</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>h</code></td><td>Hour in am/pm (1-12)</td><td><a href=
	"#number">Number</a></td><td><code>12</code></td></tr>
	 *     <tr><td><code>m</code></td><td>Minute in hour</td><td><a href=
	"#number">Number</a></td><td><code>30</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>s</code></td><td>Second in minute</td><td><a href=
	"#number">Number</a></td><td><code>55</code></td></tr>
	 *     <tr><td><code>S</code></td><td>Millisecond</td><td><a href=
	"#number">Number</a></td><td><code>978</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>z</code></td><td>Time zone</td><td><a href=
	"#timezone">General time zone</a></td><td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code></td></tr>
	 *     <tr><td><code>Z</code></td><td>Time zone</td><td><a href=
	"#rfc822timezone">RFC 822 time zone</a></td><td><code>-0800</code></td></tr>
	 * </table>
	 * <h4>Examples</h4>
	 *     The following examples show how date and time patterns are interpreted in the U.S. locale.
	 * The given date and time are 2001-07-04 12:08:56 local time in the U.S. Pacific Time time zone.
	 * <table border=0 cellspacing=3 cellpadding=0 summary=
	"Examples of date and time patterns interpreted in the U.S. locale">
	 *     <tr bgcolor="#ccccff"><th align=left>Date and Time Pattern</th><th align=
	left>Result</th></tr>
	 *     <tr><td><code>"yyyy.MM.dd G 'at' HH:mm:ss z"</code></td><td><code>2001.07.04 AD at 12:08:56 PDT</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>"EEE, MMM d, ''yy"</code></td><td><code>Wed, Jul 4, '01</code></td></tr>
	 *     <tr><td><code>"h:mm a"</code></td><td><code>12:08 PM</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>"hh 'o''clock' a, zzzz"</code></td><td><code>12 o'clock PM, Pacific Daylight Time</code></td></tr>
	 *     <tr><td><code>"K:mm a, z"</code></td><td><code>0:08 PM, PDT</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>"yyyyy.MMMMM.dd GGG hh:mm aaa"</code></td><td><code>02001.July.04 AD 12:08 PM</code></td></tr>
	 *     <tr><td><code>"EEE, d MMM yyyy HH:mm:ss Z"</code></td><td><code>Wed, 4 Jul 2001 12:08:56 -0700</code></td></tr>
	 *     <tr bgcolor=
	"#eeeeff"><td><code>"yyMMddHHmmssZ"</code></td><td><code>010704120856-0700</code></td></tr>
	 * </table>
	 * &#64;return
	 * </pre>
	 */
	public static String getSystemTime(String pattern) {
		SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
		df.applyPattern(pattern);
		return df.format(new Date());
	}

	/**
	 * 回傳時間字串格式, (HHmmss)
	 * 
	 * @return
	 */
	public static String getSystemTime() {
		return getSystemTime("HHmmss");
	}

	/**
	 * 回傳時間字串格式, (ddHH)
	 * 
	 * @return
	 */
	public static String getDateTime() {
		return getSystemTime("ddHH");
	}

	/**
	 * 回傳曰期字串格式, (yyyyMMdd)
	 * 
	 * @return
	 */
	public static String getSystemDate() {
		return getSystemTime("yyyyMMdd");
	}

	/**
	 * 回傳曰期字串格式, (yyyyMM)
	 * 
	 * @return
	 */
	public static String getSystemMonth() {
		return getSystemTime("yyyyMM");
	}

	/**
	 * 回傳曰期時間字串格式, (yyyyMMddHHmmssSSS)
	 * 
	 * @return
	 */
	public static String getSystemDateTime() {
		return getSystemTime("yyyyMMddHHmmssSSS");
	}

	/**
	 * 回傳民國日期字串格式, (yyyy/mm/dd -> yyy年MM月dd日)
	 * 
	 * @return
	 */
	public static String getRepublicDate(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, dateString.indexOf("/"))) - 1911);
		String month = dateString.substring(dateString.indexOf("/") + 1, dateString.lastIndexOf("/"));
		String day = dateString.substring(dateString.lastIndexOf("/") + 1, dateString.length());
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 回傳民國日期字串格式, (yyyy-mm-dd -> yyy年MM月dd日)
	 * 
	 * @return
	 */
	public static String getRepublicDate2(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, dateString.indexOf("-"))) - 1911);
		String month = dateString.substring(dateString.indexOf("-") + 1, dateString.lastIndexOf("-"));
		String day = dateString.substring(dateString.lastIndexOf("-") + 1, dateString.length());
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 回傳民國日期字串格式, (yyyymmdd -> yyyMMdd)
	 * 
	 * @return
	 */
	public static String getRepublicDate3(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, 4)) - 1911);
		String month_day = dateString.substring(4);
		return year + month_day;
	}

	/**
	 * 回傳民國日期字串格式, (yyyymmdd -> yyy年MM月dd)
	 * 
	 * @return
	 */
	public static String getRepublicDate4(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, 4)) - 1911);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6);
		return String.format("%s年%s月%s日", year, month, day);
	}

	/**
	 * 回傳民國日期字串格式, (yyyymmdd -> yyy/MM/dd)
	 * 
	 * @return
	 */
	public static String getRepublicDate5(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, 4)) - 1911);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6);
		return String.format("%s/%s/%s", year, month, day);
	}

	/**
	 * 回傳民國發票期別字串格式, (yyyymmdd -> ${yyy}年${起月}-${迄月}月)
	 * 
	 * @return
	 */
	public static String getRepublicPeriod(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, 4)) - 1911);
		int month = Integer.parseInt(dateString.substring(4, 6));
		return String.format("%s年%s-%s月", year, month % 2 == 0 ? month - 1 : month, month % 2 == 0 ? month : month + 1);
	}

	/**
	 * 回傳民國日期字串格式, (yyyMMdd -> yyyymmdd)
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getAnnoDominiDate(String dateString) {
		String year = String.valueOf(Integer.parseInt(dateString.substring(0, 3)) + 1911);
		String month_day = dateString.substring(3);
		return year + month_day;
	}

	public static Date parseDate(String dateString, String pattern, boolean isLenient) throws ParseException {
		SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
		df.setLenient(isLenient);
		df.applyPattern(pattern);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			throw e;
		}
	}

	/**
	 * <PRE>
	 * 檢查日期是否有效.
	 * <h4>Date and Time Patterns</h4><p>
	 * Date and time formats are specified by <em>date and time pattern</em> strings.
	 * Within date and time pattern strings, unquoted letters from <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to <code>'z'</code>
	 * are interpreted as pattern letters representing the components of a date or time string.
	 * Text can be quoted using single quotes (<code>'</code>) to avoid interpretation.
	 * <code>"''"</code> represents a single quote.
	 * All other characters are not interpreted; they're simply copied into the output string
	 * during formatting or matched against the input string during parsing.<p>
	 * The following pattern letters are defined
	 * (all other characters from <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to <code>'z'</code> are reserved):<blockquote>
	 * <table border=0 cellspacing=3 cellpadding=0 summary=
	"Chart shows pattern letters, date/time component, presentation, and examples.">
	 *     <tr bgcolor="#ccccff">
	 *         <th align=left>Letter</th><th align=
	left>Date or Time Component</th><th align=left>Presentation</th><th align=
	left>Examples</th>
	 *     </tr>
	 *     <tr>
	 *         <td><code>G</code></td><td>Era designator</td><td><a href=
	"#text">Text</a></td><td><code>AD</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>y</code></td><td>Year</td><td><a href=
	"#year">Year</a></td><td><code>1996</code>; <code>96</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>M</code></td><td>Month in year</td><td><a href=
	"#month">Month</a></td><td><code>July</code>; <code>Jul</code>; <code>07</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>w</code></td><td>Week in year</td><td><a href=
	"#number">Number</a></td><td><code>27</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>W</code></td><td>Week in month</td><td><a href=
	"#number">Number</a></td><td><code>2</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>D</code></td><td>Day in year</td><td><a href=
	"#number">Number</a></td></td><td><code>189</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>d</code></td><td>Day in month</td><td><a href=
	"#number">Number</a></td><td><code>10</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>F</code></td><td>Day of week in month</td><td><a href=
	"#number">Number</a></td><td><code>2</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>E</code></td><td>Day in week</td><td><a href=
	"#text">Text</a></td><td><code>Tuesday</code>; <code>Tue</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>a</code></td><td>Am/pm marker</td><td><a href=
	"#text">Text</a></td><td><code>PM</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>H</code></td><td>Hour in day (0-23)</td><td><a href=
	"#number">Number</a></td><td><code>0</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>k</code></td><td>Hour in day (1-24)</td><td><a href=
	"#number">Number</a></td><td><code>24</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>K</code></td><td>Hour in am/pm (0-11)</td><td><a href=
	"#number">Number</a></td><td><code>0</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>h</code></td><td>Hour in am/pm (1-12)</td><td><a href=
	"#number">Number</a></td><td><code>12</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>m</code></td><td>Minute in hour</td><td><a href=
	"#number">Number</a></td><td><code>30</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>s</code></td><td>Second in minute</td><td><a href=
	"#number">Number</a></td><td><code>55</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>S</code></td><td>Millisecond</td><td><a href=
	"#number">Number</a></td><td><code>978</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>z</code></td><td>Time zone</td><td><a href=
	"#timezone">General time zone</a></td><td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>Z</code></td><td>Time zone</td><td><a href=
	"#rfc822timezone">RFC 822 time zone</a></td><td><code>-0800</code></td>
	 *     </tr>
	 * </table>
	 * </blockquote>
	 * <h4>Examples</h4>
	 * The following examples show how date and time patterns are interpreted in the U.S. locale.
	 * The given date and time are 2001-07-04 12:08:56 local time in the U.S. Pacific Time time zone.<blockquote>
	 * <table border=0 cellspacing=3 cellpadding=0 summary=
	"Examples of date and time patterns interpreted in the U.S. locale">
	 *     <tr bgcolor="#ccccff">
	 *         <th align=left>Date and Time Pattern</th><th align=left>Result</th>
	 *     </tr>
	 *     <tr>
	 *         <td><code>"yyyy.MM.dd G 'at' HH:mm:ss z"</code></td><td><code>2001.07.04 AD at 12:08:56 PDT</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>"EEE, MMM d, ''yy"</code></td><td><code>Wed, Jul 4, '01</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>"h:mm a"</code></td><td><code>12:08 PM</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>"hh 'o''clock' a, zzzz"</code></td><td><code>12 o'clock PM, Pacific Daylight Time</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>"K:mm a, z"</code></td><td><code>0:08 PM, PDT</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>"yyyyy.MMMMM.dd GGG hh:mm aaa"</code></td><td><code>02001.July.04 AD 12:08 PM</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>"EEE, d MMM yyyy HH:mm:ss Z"</code></td><td><code>Wed, 4 Jul 2001 12:08:56 -0700</code></td>
	 *     </tr>
	 *     <tr bgcolor="#eeeeff">
	 *         <td><code>"yyMMddHHmmssZ"</code></td><td><code>010704120856-0700</code></td>
	 *     </tr>
	 *     <tr>
	 *         <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSZ"</code></td><td><code>2001-07-04T12:08:56.235-0700</code></td>
	 *     </tr>
	 * </table>
	 * </blockquote>
	 * &#64;param dateString
	 * &#64;param pattern
	 * </PRE>
	 */
	public static boolean isValidDate(String dateString, String pattern) {
		boolean ret = true;
		try {
			parseDate(dateString, pattern);
		} catch (ParseException e) {
			ret = false;
		}
		return ret;
	}

	public static Date addDays(String dateString, String pattern, int days) {
		try {
			return addDays(parseDate(dateString, pattern), days);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Date addMonths(String dateString, String pattern, int months) {
		try {
			return addMonths(parseDate(dateString, pattern), months);
		} catch (ParseException ex) {
			return null;
		}
	}

	public static Date addYears(String dateString, String pattern, int years) {
		try {
			return addYears(parseDate(dateString, pattern), years);
		} catch (ParseException ex) {
			return null;
		}
	}

	public static Date addSeconds(Date date, int seconds) {
		return add(date, Calendar.SECOND, seconds);
	}

	public static Date addDays(Date date, int days) {
		return add(date, Calendar.DATE, days);
	}

	public static Date addMonths(Date date, int months) {
		return add(date, Calendar.MONTH, months);
	}

	public static Date addYears(Date date, int years) {
		return add(date, Calendar.YEAR, years);
	}

	public static Date add(Date date, int type, int dates) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, dates);
		return calendar.getTime();
	}

	/*
	 * 取得系統年份
	 */
	public synchronized static String getSystemYear() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR));
	}

	public synchronized static String diffTime(long s, long e) {
		long ttTime = (e - s);
		String ttDifSS = "000" + ttTime;
		String ttDifSC = "00" + (ttTime / 1000) % 60;
		String ttDifMI = "00" + (ttTime / 1000 / 60) % 60;
		String ttDifHH = "00" + (ttTime / 1000 / 60 / 60) % 60;
		StringBuilder msg = new StringBuilder();
		msg.append(ttDifHH.substring(ttDifHH.length() - 2)).append(":").append(ttDifMI.substring(ttDifMI.length() - 2))
				.append(":").append(ttDifSC.substring(ttDifSC.length() - 2)).append(".")
				.append(ttDifSS.substring(ttDifSS.length() - 3));
		return msg.toString();
	}

	/**
	 * 取得現在執行時間, 以 long 格式回傳
	 * 
	 * @return
	 */
	public static long getNowTime() {
		long nowTime = 0;
		try {
			nowTime = DateUtil.parseDate(DateUtil.getSystemTime("yyMMddHHmmssSSS"), "yyMMddHHmmssSSS").getTime();
		} catch (ParseException e) {
			log.error("", e);
		}
		return nowTime;
	}

	/**
	 * 取得資料庫用的現在時間
	 * 
	 * @return
	 */
	public synchronized static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * <PRE>
	 * 將傳入的日期字串, 轉成 Timestamp 後回傳.
	 * &#64;param ymdhms 西元年月日時分秒格式, 必須為: YYYY-MM-DD HH24:MI:SS.sss,
	 * &#64;return 回傳轉成 Timestamp 的資料
	 * </PRE>
	 */
	public synchronized static Timestamp str2Timestamp(String ymdhms) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		ts = Timestamp.valueOf(ymdhms);
		return ts;
	}

	/**
	 * <PRE>
	 * 將傳入的日期物件, 轉成 Timestamp 後回傳.
	 * &#64;param date 日期物件,
	 * &#64;return 回傳轉成 Timestamp 的資料
	 * </PRE>
	 */
	public synchronized static Timestamp date2Timestamp(Date date) {
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}

	public static boolean isLeapYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(1);

		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	public static boolean isLeapYear(String date, String dateFormat) throws Exception {
		return isLeapYear(parseDateTime(date, dateFormat));
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if ((cal1 == null) || (cal2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}

		return (cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(6) == cal2.get(6));
	}

	public static String getCurrentDateTime(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}

	public static Date getAddedDateTime(int year, int month, int day, int hour, int min, int second) {
		Calendar cal = Calendar.getInstance();
		if (year != 0)
			cal.add(1, year);
		if (month != 0)
			cal.add(2, month);
		if (day != 0)
			cal.add(5, day);
		if (hour != 0)
			cal.add(10, hour);
		if (min != 0)
			cal.add(12, min);
		if (second != 0)
			cal.add(13, second);

		return cal.getTime();
	}

	public static String getAddedDateTime(String format, int year, int month, int day, int hour, int min, int second) {
		Date currentDate = getAddedDateTime(year, month, day, hour, min, second);

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateTimeStamp = formatter.format(currentDate);
		return dateTimeStamp;
	}

	public static String transferFormat(String datetime, String srcFormat, String targetFormat) {
		try {
			SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormat);
			SimpleDateFormat targetSdf = new SimpleDateFormat(targetFormat);
			datetime = targetSdf.format(srcSdf.parse(datetime));
		} catch (Exception e) {
			log.error("", e);
		}
		return datetime;
	}

	public static Date getDateOfWeek(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(7, day);
		return cal.getTime();
	}

	public static Date getDateOfThisWeek(int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(7, day);
		return cal.getTime();
	}

	public static Date parseDateTime(String dateStr, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}

	public static int getDays(int year, int month) {
		if ((month > 12) || (month < 1)) {
			throw new IllegalArgumentException("month must between 1 - 12");
		}
		int[] day = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (((year % 4 == 0) && (year % 100 != 0)) || ((year % 400 == 0) && (month == 2))) {
			return day[(month - 1)] + 1;
		}

		return day[(month - 1)];
	}

	public static boolean isWeekendByYYYYMMDD(String yyyyMMdd) {
		int yyyy = Integer.valueOf(yyyyMMdd.substring(0, 4));
		int mm = Integer.valueOf(yyyyMMdd.substring(4, 6));
		int dd = Integer.valueOf(yyyyMMdd.substring(6, 8));

		return isWeekend(yyyy, mm, dd);
	}

	/**
	 * 傳入日期判斷是否為禮拜六、日
	 * 
	 * @return
	 */
	public static boolean isWeekend(int year, int month, int d) {
		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, 1);

		int week = date.get(Calendar.DAY_OF_WEEK) - 1;

		if ((week + d) % 7 == 0 || (week + d) % 7 == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static String getLastDateOfMonth(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		Date convertedDate = dateFormat.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formateDate(c.getTime(), "yyyyMMdd");
	}

	/**
	 * 取得傳入該月份的第一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		return calendar.getTime();
	}

	/**
	 * 取得傳入該月份的最後一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	/**
	 * This method generates a string representation of a date based on the System
	 * Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return format(aDate, FORMAT_WEST_DATE);
	}

	/**
	 * @param strDate
	 * @param splitStr
	 * @return
	 */
	public static boolean isDate(String strDate, String splitStr) {
		if (strDate != null && !strDate.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy" + splitStr + "MM" + splitStr + "dd");
			df.setLenient(false);

			try {
				df.parse(strDate);
			} catch (Exception e) {
				return false;
			}
			return true;

		} else {
			return false;
		}
	}

	// 自定義驗證格式
	public static boolean isDateByRegular(String strDate, String splitStr) {
		if (strDate != null && !strDate.equals("")) {
			return strDate.matches("[1-9][0-9]{3}" + splitStr + "([1-9]|0[1-9]|1[0-2])" + splitStr
					+ "([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])");
		} else {
			return false;
		}
	}

}
