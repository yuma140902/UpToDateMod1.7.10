package yuma140902.misc_things.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import yuma140902.misc_things.ModMiscThings;

public class MTConfigCore {
	public static Configuration cfg;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
		ConfigCategory generalCategory = cfg.getCategory("General");
		generalCategory.setComment("Settings of MiscThings");
	}
	
	public static void syncConfig() {
		ModMiscThings.LOGGER.info("Loading config");
		
		cfg.save();
	}
}
