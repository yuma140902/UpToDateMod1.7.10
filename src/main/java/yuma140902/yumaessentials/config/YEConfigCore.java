package yuma140902.yumaessentials.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import yuma140902.yumaessentials.ModYumaEssentials;

public class YEConfigCore {
	
	public static Configuration cfg;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
	}
	
	public static void syncConfig() {
		ModYumaEssentials.LOGGER.info("Loading config");
		cfg.save();
	}
	
}
