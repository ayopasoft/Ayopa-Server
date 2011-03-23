package com.ayopa.server.utils;

import org.apache.commons.lang.xwork.StringUtils;

public class JsonUtils {

	public static String sanitizeJsonpParam(String s) {
		if ( StringUtils.isEmpty(s)) return null;
		if ( !StringUtils.startsWithIgnoreCase(s,"jsonp")) return null;
		if ( StringUtils.length(s) > 128 ) return null;
		if ( !s.matches("^jsonp\\d+$")) return null;
		return s;
	}

}
