package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.config.ModConfigCore.*;
import net.minecraftforge.common.config.Configuration;

public class IntegrationConfigs {
	private IntegrationConfigs() {}
	
	public static final String CATEGORY_INTEGRATION = getSubCategory("Integration");
	
	public static void initConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_INTEGRATION, "Settings to cooperate with other mods");
		cfg.setCategoryLanguageKey(CATEGORY_INTEGRATION, getCategoryLangkey("integration"));
		cfg.setCategoryRequiresMcRestart(CATEGORY_INTEGRATION, true);
		
		PluginEtFuturum.INSTANCE.initConfig(cfg);
	}

	public static void syncConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.syncConfig(cfg);
	}

	public static void wrapConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.wrapConfig(cfg);
	}
	
	
	
}
