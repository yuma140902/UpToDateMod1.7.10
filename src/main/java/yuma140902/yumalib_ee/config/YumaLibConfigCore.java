package yuma140902.yumalib_ee.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import yuma140902.yumalib_ee.ModYumaLib;

public class YumaLibConfigCore {
	
	public static class Tooltip {
		
		public static final String CAT_NAME = "Tooltip";
		
		public static EnumTooltip showOreDic = EnumTooltip.Never;
		public static EnumTooltip showRegistryName = EnumTooltip.Never;
		public static EnumTooltip showBlockMaterialInfo = EnumTooltip.Never;
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
		
		Property showOreDic = cfg.get(Tooltip.CAT_NAME, "showOreDic", Tooltip.showOreDic.toString());
		showOreDic.setValidValues(EnumTooltip.stringValues());
		Tooltip.showOreDic = EnumTooltip.valueOf(showOreDic.getString());
		
		Property showRegistryName = cfg.get(Tooltip.CAT_NAME, "showRegitrsyName", Tooltip.showRegistryName.toString());
		showRegistryName.setValidValues(EnumTooltip.stringValues());
		Tooltip.showRegistryName = EnumTooltip.valueOf(showRegistryName.getString());
		
		Property showBlockMat = cfg.get(Tooltip.CAT_NAME, "showBlockMaterialInfo", Tooltip.showBlockMaterialInfo.toString());
		showBlockMat.setValidValues(EnumTooltip.stringValues());
		Tooltip.showBlockMaterialInfo = EnumTooltip.valueOf(showBlockMat.getString());
		
		cfg.save();
	}
	
}
