package com.clicklist.util;

import java.util.Collection;
import java.util.Map;

public final class ListUtil {

	public static boolean isEmpty(Collection<?> value) {
		return (value == null || (value != null && value.isEmpty()));
	}
	
	public static boolean isEmpty(Map<?, ?> value) {
		return (value == null || (value != null && value.isEmpty()));
	}
}
