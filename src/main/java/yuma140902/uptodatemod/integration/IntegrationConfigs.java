package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.config.ModConfigCore.*;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.yumalib.api.config.CategoryBuilder;

public class IntegrationConfigs {
	private IntegrationConfigs() {}
	
	public static final String CATEGORY_INTEGRATION = getSubCategory("Integration");
	
	private static CategoryBuilder integrationCategory;
	
	public static void initConfig(Configuration cfg) {
		integrationCategory =  new CategoryBuilder(CATEGORY_INTEGRATION)
			.langKey(ModConfigCore.getCategoryLangkey("integration"))
			.requireMcRestart();
		integrationCategory.registerToForge(cfg);
		
		Plugins.initConfig(cfg);
	}

	public static void syncConfig(Configuration cfg) {
		integrationCategory.registerPropertiesToForge(cfg);

		Plugins.syncConfig(cfg);
	}

	public static void wrapConfig(Configuration cfg) {
		Plugins.wrapConfig(cfg);
	}
	
	
	
}
