package yuma140902.uptodatemod.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class StringUtil {
	private StringUtil() {}
	
	public static String surfix(String sep, String value) {
		return value.isEmpty() ? "" : sep + value;
	}
	
	public static String prefix(String value, String sep) {
		return value.isEmpty() ? "" : value + sep;
	}
	
	public static String getDomainedModTextureName(String name) {
		return ModUpToDateMod.MOD_TEXTURE_DOMAIN + ":" + name;
	}
	
	public static String getDomainedMCTextureName(String name) {
		return "minecraft:" + name;
	}
	
	public static String getDomainedUnlocalizedName(String name) {
		return ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN + "." + name;
	}
	
	@Nonnull
	public static String[] domainedModTextureNames(String... names) {
		return domainedTextureNames(ModUpToDateMod.MOD_TEXTURE_DOMAIN, names);
	}
	
	@Nonnull
	public static String[] domainedMCTextureNames(String... names) {
		return domainedTextureNames("minecraft", names);
	}
	
	@Nonnull
	private static String[] domainedTextureNames(String textureDomain, @Nullable String... names) {
		if(names == null) return new String[0];
		
		String[] ret = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			ret[i] = textureDomain + ":" + names[i];
		}
		return ret;
	}
	
}
