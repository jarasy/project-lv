package com.jarasy.lv.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: DateUtil
 * @Description: 日期工具类
 * @author zjh
 * @date 2017/06/07 10:20
 * @version v1.0
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static String DF_yyyyMMdd = "yyyyMMdd";
	public static String DF_YYYYMMDD = "yyyy-MM-dd";
	public static String DF_YYYYMM = "yyyy-MM";
	public static String DF_YYYYmm = "yyyyMM";
	public static String DF_YYYY = "yyyy";
	public static String DF_YYYYMMDDHH = "yyyy-MM-dd HH";
	public static String DF_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static String DF_YYYYMMDDHHMMSSS = "yyyy-MM-dd HH:mm:sss";
	public static String DF_YYYYMMDDHHMMSS_CH = "yyyy年MM月dd日 HH时mm分ss秒";
	public static String DF_YYYYMMDD_CH = "yyyy年MM月dd日";
	public static String DF_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static Date parseDate(String str,
										   String[] parsePatterns) throws ParseException {
		return DateUtils.parseDate(str,
                parsePatterns);
	}

	public static Date parseDate(String str) {
		Date date = null;
		if(null != str && !"".equals(str)){
			try {
				date = DateUtil.parseDate(str, new String[]{DF_YYYYMMDD});
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		return date;
	}

	public static String formatDate(Date date, String pattern) {
		if(null!=date){
			DateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}else{
			return "";
		}
	}

	/**
	 * 获取生日,输入的字符串按照yyyy-MM-dd进行parse并转换成为时�?
	 * 
	 * @param str
	 *            传入参数
	 * @return 返回结果
	 */
	public static Date getBirthDay(String str) {
		Date date = null;
		try {
			date = DateUtil.parseDate(str, new String[]{DF_YYYYMMDD});
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return date;
	}
	
	/**    
     * @param startDate 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDate 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年    
     * @return    
     * 举例：  
     * compareDate("2009-09-12", null, 0);//比较天  
     * compareDate("2009-09-12", null, 1);//比较月  
     * compareDate("2009-09-12", null, 2);//比较年  //不足一年算一年
     */    
	public static int compareDate(Date startDate,Date endDate,int stype){
		if (null == startDate){
			return 0;
		}
		String startDateStr = formatDate(startDate,DF_YYYYMMDD);
		String endDateStr = formatDate(endDate,DF_YYYYMMDD);
		
		return compareDate(startDateStr,endDateStr,stype);
	}
	
	 /**    
     * @param startDateStr 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDateStr 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年    
     * @return    
     * 举例：  
     * compareDate("2009-09-12", null, 0);//比较天  
     * compareDate("2009-09-12", null, 1);//比较月  
     * compareDate("2009-09-12", null, 2);//比较年  
     */    
	public static int compareDate(String startDateStr,String endDateStr,int stype){
		if (null == startDateStr){
			return 0;
		}
	    int n = 0;     
	    String[] u = {"天","月","年"};
	    String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";
	    
	    endDateStr = endDateStr==null?getCurrentDate("yyyy-MM-dd"):endDateStr;
	         
	    DateFormat df = new SimpleDateFormat(formatStyle);
	    
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    
	    try {     
	        c1.setTime(df.parse(startDateStr));     
	        c2.setTime(df.parse(endDateStr));     
	    } catch (Exception e3) {
	    	df = new SimpleDateFormat("yyyyMMdd");//特殊情况,执业时间起始
	    	try {
				c1.setTime(df.parse(startDateStr));
				 c2.setTime(df.parse(endDateStr)); 
			} catch (ParseException e) {
				System.out.println("wrong occured again!");
			}     
//	        System.out.println("wrong occured");     
	    }     
	    //List list = new ArrayList();     
	    while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果     
	        //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来     
	        n++;     
	        if(stype==1){     
	            c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
	        }     
	        else{     
	            c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
	        }     
	    }     
	    n = n-1;     
	    if(stype==2){     
	        n = (int)n/365;     
	        n = n==0?1:n;//不足一年算一年
	    }        
//	    System.out.println(startDateStr+" -- "+endDateStr+" 相差多少"+u[stype]+":"+n);
	    
	    return n;     
	}      
	
	/**
	 * 当前格式化时间 
	 * @param format "yyyy-MM-dd"
	 * @return
	 */
	public static String getCurrentDate(String format){
	   Calendar day= Calendar.getInstance();
	   day.add(Calendar.DATE,0);
	   SimpleDateFormat sdf=new SimpleDateFormat(format);//"yyyy-MM-dd"
	   
	   return sdf.format(day.getTime());   
	}

	/**
	 * 年份添加
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date dateAddByYear(Date date, int year) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.YEAR, year);// 把年份往后增加月份.整数往后推,负数往前移动
		date = calendar.getTime();
		return date;
	}
	/**
	 * 月份添加
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddByMonth(Date date, int month) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, month);// 把日期往后增加月份.整数往后推,负数往前移动
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 天数添加
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date dateAddByDay(Date date, int days) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DAY_OF_YEAR, days);// 把日期往后增加天数.整数往后推,负数往前移动
		return calendar.getTime();
	}

	/**
	 * 天数添加
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date dateTimeAddBySecond(Date date, int second) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.SECOND, second);// 把日期往后增加天数.整数往后推,负数往前移动
		return calendar.getTime();
	}

	/**
	 * Parse a datetime string.
	 * @param param datetime string, pattern: "yyyy-MM-dd HH:mm:ss".
	 * @return java.util.Date
	 */
	public static Date parse(String param) {
		Date date = null;
		if (param == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(param);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			return date;
		}
	}
	
	public static Date getDatea(){
		
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：判断日期是否周末双休日
	 * 
	 * @param date 
	 * @return flag
	 *
	 */
	public static boolean isWeekends(Date date){
		boolean flag = true;
		
		Calendar cal = Calendar.getInstance(); //日历
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//周几
		if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY){
			flag = false;
		}

		return flag ;
	}

	/**
	 *
	 * 验证日期是否是节假日
	 * @param date  传入需要验证的日期
	 * @return
	 * return boolean    返回类型  返回true是节假日，返回false不是节假日
	 * throws
	 */
	public static boolean isHoliday(Date date,List<Date> holidayList) {
		//判断日期是否是节假日
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for (Date tempdate : holidayList) {
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.setTime(tempdate);
			if (tempCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
					tempCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
					tempCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * 验证日期是否是调休
	 * @param date  传入需要验证的日期
	 * @return
	 * return boolean    返回类型  返回true是调休，返回false不是调休
	 * throws
	 */
	public static boolean isOffDay(Date date,List<Date> offdayList) {
		//判断日期是否是调休
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for (Date tempdate : offdayList) {
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.setTime(tempdate);
			if (tempCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
					tempCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
					tempCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * 方法描述：取得当前日期为周几
	 * 
	 * @param date 
	 * @return flag
	 *
	 */
	public static int getDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance(); //日历
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);//周几
	}
	
	/**
	 * 
	 * 方法描述：判断时间是否在时间范围内
	 * 
	 * @param date minDate maxDate
	 * @return flag
	 *
	 */
	public static boolean isInTimeRange(Date date,Date minDate,Date maxDate){
		boolean flag = true;
		
		Calendar cal = Calendar.getInstance(); //日历
		cal.setTime(date);
		Calendar minCal = Calendar.getInstance(); //日历s
		minCal.setTime(minDate);
		Calendar maxCal = Calendar.getInstance(); //日历e
		maxCal.setTime(maxDate);
		 
		if(cal.before(minCal) || cal.after(maxCal)){
	    	flag = false;
	    }
		
		return flag ;
		
	}
	
	
	
	/**
	 * 
	 * 方法描述：得到当前月份的第一天
	 * 
	 * @author 
	 * @return 返回第一天的日期（字符型如：2010-05-01）
	 *
	 */
	@SuppressWarnings("all")
	public static String getCurrentMonthFirstDay(){
		
		Calendar c = Calendar.getInstance();
        c.set(c.DATE, 1);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // System.out.println("当前月第一天>>"+df.format(c.getTime())) ;
        
		return df.format(c.getTime());
		
	}
	
	
	/**
	 * 
	 * 方法描述：得到当前月份的最后一天
	 * 
	 * @author 
	 * @return 返回最后一天的日期（字符型如：2010-05-31）
	 */
	@SuppressWarnings("all")
	public static String getCurrentMonthEndDay(){
	  
	    Calendar cal = Calendar.getInstance(); //日历
        // 当前月＋1，即下个月
        cal.add(cal.MONTH, 1); //得到下个月的月份
        // 将下个月1号作为日期初始值
        cal.set(cal.DATE, 1);
        // 下个月1号减去一天，即得到当前月最后一天
        cal.add(cal.DATE, -1);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String day_end = df.format(cal.getTime());
       
        //System.out.println("当前月最后一天>>>" + day_end); 
        
        return day_end;
	}
	
	/**
	 * 
	 * 方法描述：得到当前月份数值
	 * 
	 * @author guojin
	 * @return 返回当前月份数值（字符型如：4）
	 */
	@SuppressWarnings("all")
	public static int getCurrentMonth(){
	  
	    Calendar cal = Calendar.getInstance(); //日历
	    
	    return cal.get(cal.MONTH)+1;
	}
	
	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat(DF_YYYYMMDD_CH);
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static String StrZH(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat(DF_YYYYMMDD);
	   Date date = null;
	   try {
	    date = format.parse(str);
	    SimpleDateFormat format2 = new SimpleDateFormat(DF_YYYYMMDD_CH);
		   str = format2.format(date);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return str;
	}

	/**
	 * 获取近七天日期 日期格式yyyyMMdd
	 * @return
	 * @remark rwl 20161020
	 */
	public static String[] getRecentlySevenDay(){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] strs = new String[7];
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, - 0);
		Date monday = c.getTime();
		String preday = sdf.format(monday);
		strs[0] = preday;
		for(int i = 1; i < 7; i++){
			c.add(Calendar.DATE, - 1);
			monday = c.getTime();
			preday = sdf.format(monday);
			strs[i] = preday;
		}

		return strs;
	}

	/**
	 * 获取某时间之前第60天的日期
	 * @param someDate
	 * @return
	 * @remark rwl 20161118
	 */
	public static String getBeforeDate(String someDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(someDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = new GregorianCalendar();

		calendar.setTime(date);

		calendar.add(calendar.DATE, -60);
		date=calendar.getTime();
		String beforeDate = sdf.format(date);
		return beforeDate;
	}


	/**
	 * 格式化时间为UTF 时间
	 * @param date
	 * @return
	 */
	public static String formatUTCTime(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(date);
	}

	public static String formatUTCTime() {
		return formatUTCTime(new Date());
	}


	/**
	 * 时间转换 Created by piqian.chen on 2018/7/17
	 * @param source 字符串格式时间
	 */
	public static Date convertToDate(String source) {
		Date date = null;
		if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
			date = parseDate(source, "yyyy-MM-dd");
		}else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")){
			date = parseDate(source, "yyyy-MM-dd HH:mm:ss");
		} else if (source.matches("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}$")){
			date = parseDate(source, "yyyy/MM/dd");
		} else if (source.matches("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}$ \\d{1,2}:\\d{1,2}:\\d{1,2}$")){
			date = parseDate(source, "yyyy/MM/dd HH:mm:ss");
		} else if (source.matches("[0-9]+")){  //时间戳格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = parseDate(sdf.format(Long.parseLong(source)), "yyyy-MM-dd HH:mm:ss");
		} else if(StringUtils.isNoneBlank(source)){
			logger.error("前台时间传参为："+source+"，后台不支持此时间格式。已将时间设置为null", new RuntimeException("转换时间失败"));
		}
		return date;
	}

	private static Date parseDate(String dateStr, String format) {
		Date date=null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			date = dateFormat.parse(dateStr);
		}catch (ParseException e){
			logger.error("时间转换出错", e);
		}
		return date;
	}

	/**
	 * 当前月开始时间
	 * Created by piqian.chen on 2018/10/16
	 */
	public static Date getMonthStart() {
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		return cale.getTime();
	}

	/**
	 * 当前月结束时间
	 * Created by piqian.chen on 2018/10/16
	 */
	public static Date getMonthEnd() {
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		return cale.getTime();
	}

	/**
	 * 当前月结束时间
	 * Created by piqian.chen on 2018/10/16
	 */
	public static Date getMonthEnd(int year, int month) {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.YEAR, year);
		cale.set(Calendar.MONTH, month-1);
		cale.set(Calendar.DAY_OF_MONTH,cale.getActualMaximum(Calendar.DATE));
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		return cale.getTime();
	}

}
