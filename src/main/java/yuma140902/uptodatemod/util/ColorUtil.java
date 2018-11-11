package yuma140902.uptodatemod.util;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class ColorUtil {
	private ColorUtil() {}
	
	public static String metaToString(int meta) {
		switch(meta) {
			case Stat.COLOR_META_BLACK:
				return "black";
			case Stat.COLOR_META_BLUE:
				return "blue";
			case Stat.COLOR_META_BROWN:
				return "brown";
			case Stat.COLOR_META_CYAN:
				return "cyan";
			case Stat.COLOR_META_GRAY:
				return "gray";
			case Stat.COLOR_META_GREEN:
				return "green";
			case Stat.COLOR_META_LIGHT_BLUE:
				return "light_blue";
			case Stat.COLOR_META_LIME:
				return "lime";
			case Stat.COLOR_META_MAGENTA:
				return "magenta";
			case Stat.COLOR_META_ORANGE:
				return "orange";
			case Stat.COLOR_META_PINK:
				return "pink";
			case Stat.COLOR_META_PURPLE:
				return "purple";
			case Stat.COLOR_META_RED:
				return "red";
			case Stat.COLOR_META_SILVER:
				return "silver";
			case Stat.COLOR_META_WHITE:
				return "white";
			case Stat.COLOR_META_YELLOW:
				return "yellow";
				
			default: return "unknown_color";
		}
	}
}
