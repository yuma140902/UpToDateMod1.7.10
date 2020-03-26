package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.config.ModConfigCore.*;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.config.model.CategoryBuilder;

public class IntegrationConfigs {
	private IntegrationConfigs() {}
	
	public static final String CATEGORY_INTEGRATION = getSubCategory("Integration");
	
	private static CategoryBuilder integrationCategory;
	
	public static void initConfig(Configuration cfg) {
		integrationCategory =  new CategoryBuilder(CATEGORY_INTEGRATION)
			.langKey(ModConfigCore.getCategoryLangkey("integration"))
			.requireMcRestart();
		integrationCategory.registerToForge(cfg);
		
		PluginEtFuturum.INSTANCE.initConfig(cfg);
	}

	public static void syncConfig(Configuration cfg) {
		integrationCategory.registerPropertiesToForge(cfg);
		PluginEtFuturum.INSTANCE.syncConfig(cfg);
	}

	public static void wrapConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.wrapConfig(cfg);
	}
	
	
	
}
