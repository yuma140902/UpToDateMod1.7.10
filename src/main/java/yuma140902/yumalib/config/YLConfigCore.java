package yuma140902.yumalib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import yuma140902.yumalib.ModYumaLib;
import yuma140902.yumalib.api.config.CategoryBuilder;
import yuma140902.yumalib.api.config.PropertyBuilder;

public class YLConfigCore {
	
	public static class Tooltip {
		
		public static final String CAT_NAME = "Tooltip";
		
		public static EnumTooltip showOreDic = EnumTooltip.Never;
		public static EnumTooltip showRegistryName = EnumTooltip.Never;
		public static EnumTooltip showBlockMaterialInfo = EnumTooltip.Never;
	}
	
	public static boolean showCreativeTab = false;
	
	public static Configuration cfg;
	
	private static CategoryBuilder generalCategory;
	private static CategoryBuilder tooltipCategory;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
		generalCategory = new CategoryBuilder("General")
				.comment("Settings of YumaLib")
				.add(PropertyBuilder.bool("showCreativeTab")
						.defaultBool(showCreativeTab)
						.requireMcRestart());
		generalCategory.registerToForge(cfg);
		
		
		tooltipCategory = new CategoryBuilder(Tooltip.CAT_NAME)
				.comment("Settings of Tooltip")
				.add(PropertyBuilder.string("showOreDic")
						.defaultString(Tooltip.showOreDic.toString())
						.validStrings(EnumTooltip.stringValues()))
				.add(PropertyBuilder.string("showRegistryName")
						.defaultString(Tooltip.showRegistryName.toString())
						.validStrings(EnumTooltip.stringValues()))
				.add(PropertyBuilder.string("showBlockMaterialInfo")
						.defaultString(Tooltip.showBlockMaterialInfo.toString())
						.validStrings(EnumTooltip.stringValues()));
		tooltipCategory.registerToForge(cfg);
	}
	
	public static void syncConfig() {
		ModYumaLib.LOGGER.info("Loading config");
		
		generalCategory.registerPropertiesToForge(cfg);
		showCreativeTab = generalCategory.get("showCreativeTab", cfg).getBoolean();
		
		tooltipCategory.registerPropertiesToForge(cfg);
		Tooltip.showOreDic = EnumTooltip.valueOf(tooltipCategory.get("showOreDic", cfg).getString());
		Tooltip.showRegistryName = EnumTooltip.valueOf(tooltipCategory.get("showRegistryName", cfg).getString());
		Tooltip.showBlockMaterialInfo = EnumTooltip.valueOf(tooltipCategory.get("showBlockMaterialInfo", cfg).getString());
		
		cfg.save();
	}
	
}
