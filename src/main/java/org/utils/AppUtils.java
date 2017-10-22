package org.utils;

import java.util.Calendar;
import java.util.Date;

public class AppUtils {

	public static boolean isAfterMidday (Date date) {

		Calendar now = Calendar.getInstance();
		now.setTime(date);

		int hour = now.get(Calendar.HOUR_OF_DAY);

		if (hour > 12) {
			return true;
		}

		return false;
	}

	public static boolean isBeforeMidday (Date date) {

		Calendar now = Calendar.getInstance();
		now.setTime(date);

		int hour = now.get(Calendar.HOUR_OF_DAY);

		if (hour < 12) {
			return true;
		}

		return false;
	}

}
