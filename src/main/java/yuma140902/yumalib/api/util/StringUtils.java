package yuma140902.yumalib.api.util;

public class StringUtils {
	public static String suffix(String sep, String value) {
		return value.isEmpty() ? "" : sep + value;
	}
	
	public static String prefix(String value, String sep) {
		return value.isEmpty() ? "" : value + sep;
	}
}
