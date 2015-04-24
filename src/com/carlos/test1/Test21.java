package com.carlos.test1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Test21 {

	@Test
	public void test1() {
		String msg = "";

		getErrorMsg(msg);
		
		getTimeByDateString("2014-10-29 12:15");
	}

	public static long getTimeByDateString(String dateString) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e1) {
			}

		}

		if (null == date) {
			System.out.println("无法解析日期:dateString=" + dateString);
			return System.currentTimeMillis();
		} else {
			System.out.println("解析出来的日期为"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
							.format(date));
			return date.getTime() + "1234567890";
		}
	}

	private void getErrorMsg(String content) {
		Pattern p = Pattern.compile("<!--cmcccs|(.*)-->");
		Matcher m = p.matcher(content);
		if (m.find() && m.groupCount() > 0) {
			String tempResult = m.group(1);
			String[] temps = tempResult.split("\\|");
			if (temps.length > 4) {
				String errorMsg = temps[3] + temps[4];
				String errorCodeStr = temps[2];
				int errorCode = -1;
				if (null != errorCodeStr) {
					try {
						errorCode = Integer.parseInt(errorCodeStr);
					} catch (NumberFormatException e) {
					}
					System.out.println("errorCode=" + errorCode);
				}
				System.out.println("errorMsg=" + errorMsg);
			}
			System.out.println("tempResult=" + tempResult);
		}
	}

}
