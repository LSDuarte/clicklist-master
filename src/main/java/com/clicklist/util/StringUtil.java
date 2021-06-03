package com.clicklist.util;

public class StringUtil {

	public static String likeLower(String param) {
		return "%" + param.toLowerCase() + "%";
	}

	public static String like(String param) {
		return "%" + param + "%";
	}

	public static String removeCharacters(String value) {
		value = value.replaceAll("[^0-9]", "");
		return value;
	}

}
