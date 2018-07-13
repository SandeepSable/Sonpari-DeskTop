package com.ibm.app.validators;

public class BasicValidator {

	public static boolean isNull(Object obj) {
		boolean flag = false;
		if (obj == null) {
			return true;
		}
		return flag;
	}

	public static boolean isStringEmpty(String str) {
		boolean flag = false;
		if (str == null || str.equals("")) {
			return true;
		}
		return flag;
	}

	public static Double toNumber(String str) {
		try {
			Float floatValue = Float.parseFloat(str);
			return floatValue.doubleValue();
		} catch (Exception ex) {
			return null;
		}

	}
}
