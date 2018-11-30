package yuma140902.uptodatemod.util;

public class StringUtil {
	private StringUtil() {}
	
	public static String add(String sep, String value) {
		return value.isEmpty() ? "" : sep + value;
	}
}
