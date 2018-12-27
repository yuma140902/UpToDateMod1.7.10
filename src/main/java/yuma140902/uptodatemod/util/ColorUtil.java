package yuma140902.uptodatemod.util;

import net.minecraft.block.material.MapColor;

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
			case Stat.COLOR_META_LIGHT_GRAY:
				return "light_gray";
			case Stat.COLOR_META_WHITE:
				return "white";
			case Stat.COLOR_META_YELLOW:
				return "yellow";
				
			default: return "unknown_color";
		}
	}
	
	public static MapColor metaToMapColor(int meta) {
		switch(meta) {
			case Stat.COLOR_META_BLACK:
				return MapColor.blackColor;
			case Stat.COLOR_META_BLUE:
				return MapColor.blueColor;
			case Stat.COLOR_META_BROWN:
				return MapColor.brownColor;
			case Stat.COLOR_META_CYAN:
				return MapColor.cyanColor;
			case Stat.COLOR_META_GRAY:
				return MapColor.grayColor;
			case Stat.COLOR_META_GREEN:
				return MapColor.greenColor;
			case Stat.COLOR_META_LIGHT_BLUE:
				return MapColor.lightBlueColor;
			case Stat.COLOR_META_LIME:
				return MapColor.limeColor;
			case Stat.COLOR_META_MAGENTA:
				return MapColor.magentaColor;
			case Stat.COLOR_META_ORANGE:
				return MapColor.adobeColor;
			case Stat.COLOR_META_PINK:
				return MapColor.pinkColor;
			case Stat.COLOR_META_PURPLE:
				return MapColor.purpleColor;
			case Stat.COLOR_META_RED:
				return MapColor.redColor;
			case Stat.COLOR_META_LIGHT_GRAY:
				return MapColor.ironColor;
			case Stat.COLOR_META_WHITE:
				return MapColor.snowColor;
			case Stat.COLOR_META_YELLOW:
				return MapColor.yellowColor;
				
			default: return MapColor.airColor;
		}
	}
}
