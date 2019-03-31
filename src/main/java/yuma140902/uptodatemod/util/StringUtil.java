package yuma140902.uptodatemod.util;

import yuma140902.uptodatemod.ModUpToDateMod;

public class StringUtil {
	private StringUtil() {}
	
	public static String surfix(String sep, String value) {
		return value.isEmpty() ? "" : sep + value;
	}
	
	public static String prefix(String value, String sep) {
		return value.isEmpty() ? "" : value + sep;
	}
	
	public static String getDomainedTextureName(String name) {
		return ModUpToDateMod.MOD_TEXTURE_DOMAIN + ":" + name;
	}
	
	public static String getDomainedMCTextureName(String name) {
		return "minecraft:" + name;
	}
	
	public static String getDomainedUnlocalizedName(String name) {
		return ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN + "." + name;
	}
}
