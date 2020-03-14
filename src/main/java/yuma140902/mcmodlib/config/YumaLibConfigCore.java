package yuma140902.mcmodlib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import yuma140902.mcmodlib.ModYumaLib;

public class YumaLibConfigCore {
	
	public static class Tooltip {
		
		public static final String CAT_NAME = "Tooltip";
		
		public static boolean showOreDic = false;
		public static boolean showModName = false;
		public static boolean showRegistryName = false;
	}
	
	public static Configuration cfg;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
		ConfigCategory generalCategory = cfg.getCategory("General");
		generalCategory.setComment("Settings of YumaLib");
		
		ConfigCategory tooltipCategory = cfg.getCategory(Tooltip.CAT_NAME);
		tooltipCategory.setComment("");
	}
	
	public static void syncConfig() {
		ModYumaLib.LOGGER.info("Loading config");
		
		Property showOreDic = cfg.get(Tooltip.CAT_NAME, "showOreDic", Tooltip.showOreDic);
		showOreDic.setDefaultValue(Tooltip.showOreDic);
		Tooltip.showOreDic = showOreDic.getBoolean();
		
		Property showModName = cfg.get(Tooltip.CAT_NAME, "showModName", Tooltip.showModName);
		showModName.setDefaultValue(Tooltip.showModName);
		Tooltip.showModName = showModName.getBoolean();
		
		Property showRegistryName = cfg.get(Tooltip.CAT_NAME, "showRegitrsyName", Tooltip.showRegistryName);
		showRegistryName.setDefaultValue(Tooltip.showRegistryName);
		Tooltip.showRegistryName = showRegistryName.getBoolean();
		
		cfg.save();
	}
	
}
