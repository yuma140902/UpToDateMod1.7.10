package yuma140902.uptodatemod.util;

import net.minecraft.block.material.MapColor;

public class ColorUtil {
	private ColorUtil() {}
	
	public static final int
		META_WHITE = 0,
		META_ORANGE = 1,
		META_MAGENTA = 2,
		META_LIGHT_BLUE = 3,
		META_YELLOW = 4,
		META_LIME = 5,
		META_PINK = 6,
		META_GRAY = 7,
		META_LIGHT_GRAY = 8,
		META_CYAN = 9,
		META_PURPLE = 10,
		META_BLUE = 11,
		META_BROWN = 12,
		META_GREEN = 13,
		META_RED = 14,
		META_BLACK = 15;
	
	public static String metaToString(int meta) {
		switch(meta) {
			case META_BLACK:
				return "black";
			case META_BLUE:
				return "blue";
			case META_BROWN:
				return "brown";
			case META_CYAN:
				return "cyan";
			case META_GRAY:
				return "gray";
			case META_GREEN:
				return "green";
			case META_LIGHT_BLUE:
				return "light_blue";
			case META_LIME:
				return "lime";
			case META_MAGENTA:
				return "magenta";
			case META_ORANGE:
				return "orange";
			case META_PINK:
				return "pink";
			case META_PURPLE:
				return "purple";
			case META_RED:
				return "red";
			case META_LIGHT_GRAY:
				return "light_gray";
			case META_WHITE:
				return "white";
			case META_YELLOW:
				return "yellow";
				
			default: return "unknown_color";
		}
	}
	
	public static String metaToDyeName(int meta) {
		switch(meta) {
			case META_BLACK:
				return "Black";
			case META_BLUE:
				return "Blue";
			case META_BROWN:
				return "Brown";
			case META_CYAN:
				return "Cyan";
			case META_GRAY:
				return "Gray";
			case META_GREEN:
				return "Green";
			case META_LIGHT_BLUE:
				return "LightBlue";
			case META_LIME:
				return "Lime";
			case META_MAGENTA:
				return "Magenta";
			case META_ORANGE:
				return "Orange";
			case META_PINK:
				return "Pink";
			case META_PURPLE:
				return "Purple";
			case META_RED:
				return "Red";
			case META_LIGHT_GRAY:
				return "LightGray";
			case META_WHITE:
				return "White";
			case META_YELLOW:
				return "Yellow";
				
			default: return "UnknownColor";
		}
	}
	
	public static MapColor metaToMapColor(int meta) {
		switch(meta) {
			case META_BLACK:
				return MapColor.blackColor;
			case META_BLUE:
				return MapColor.blueColor;
			case META_BROWN:
				return MapColor.brownColor;
			case META_CYAN:
				return MapColor.cyanColor;
			case META_GRAY:
				return MapColor.grayColor;
			case META_GREEN:
				return MapColor.greenColor;
			case META_LIGHT_BLUE:
				return MapColor.lightBlueColor;
			case META_LIME:
				return MapColor.limeColor;
			case META_MAGENTA:
				return MapColor.magentaColor;
			case META_ORANGE:
				return MapColor.adobeColor;
			case META_PINK:
				return MapColor.pinkColor;
			case META_PURPLE:
				return MapColor.purpleColor;
			case META_RED:
				return MapColor.redColor;
			case META_LIGHT_GRAY:
				return MapColor.ironColor;
			case META_WHITE:
				return MapColor.snowColor;
			case META_YELLOW:
				return MapColor.yellowColor;
				
			default: return MapColor.airColor;
		}
	}
}
