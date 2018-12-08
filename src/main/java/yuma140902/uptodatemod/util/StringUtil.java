package yuma140902.uptodatemod.util;

public class StringUtil {
	private StringUtil() {}
	
	public static String surfix(String sep, String value) {
		return value.isEmpty() ? "" : sep + value;
	}
	
	public static String prefix(String value, String sep) {
		return value.isEmpty() ? "" : value + sep;
	}
}
