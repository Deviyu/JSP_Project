package kr.or.ddit.util;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarUtil {
	GregorianCalendar calendar;
	
	public CalendarUtil() {
		calendar = new GregorianCalendar(Locale.KOREA);
	}
	
	public String getDatePath(){
		return getYear()+File.separator+getMonth();
	}
	
	public String getMonth() {
		int mon = calendar.get(Calendar.MONTH)+1;
		String mon_str = "";

		switch(mon) {
			case 10:
			case 11:
			case 12:
			mon_str += mon;
			break;
			default :
			mon_str = "0"+mon;
		}
		return mon_str;
	}
	
	public String getYear() {
		return String.valueOf(calendar.get(Calendar.YEAR));
	}
	public String getDay() {
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String day_Str = "";
		
		if(day<10) {
			day_Str = "0"+day;
		} else {
			day_Str += day;
		}
		return day_Str;
	}
	
}
