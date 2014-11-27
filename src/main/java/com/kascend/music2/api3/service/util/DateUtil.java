package com.kascend.music2.api3.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	public static final String FMT_LONG_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_SHORT_TIME = "yyyy-MM-dd";

	public static long toTimeStamp(String myDate, String pattern, Locale language) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, language);
		try {
			Date time = sdf.parse(myDate);
			return time.getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static long toTimeStamp(String myDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date time = sdf.parse(myDate);
			return time.getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getCurrentDate(String fmt) {
		DateFormat d = new SimpleDateFormat(fmt);
		return d.format(new Date());
	}
	
	 public static long format2UnixTime(String date,String fmt){
	    	try {
	    		return new java.text.SimpleDateFormat(fmt).parse(date).getTime()/1000; 
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return 0L;
	 }
	 
	 public static long getCurrentTime(){
	    	try {
	    		return new java.text.SimpleDateFormat(FMT_LONG_TIME)
	    		.parse(getCurrentDate(FMT_LONG_TIME)).getTime()/1000; 
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return 0L;
	 }

}
