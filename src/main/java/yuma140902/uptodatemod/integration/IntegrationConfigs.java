package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.config.ModConfigCore.*;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.config.model.CategoryBuilder;
import yuma140902.uptodatemod.config.model.MultiLingualString;

public class IntegrationConfigs {
	private IntegrationConfigs() {}
	
	public static final String CATEGORY_INTEGRATION = getSubCategory("Integration");
	
	public static void initConfig(Configuration cfg) {
		new CategoryBuilder(CATEGORY_INTEGRATION)
			.comment(MultiLingualString.single("Settings to cooperate with other mods"))
			.langKey(ModConfigCore.getCategoryLangkey("integration"))
			.requireMcRestart()
			.registerToForge(cfg);
		
		PluginEtFuturum.INSTANCE.initConfig(cfg);
	}

	public static void syncConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.syncConfig(cfg);
	}

	public static void wrapConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.wrapConfig(cfg);
	}
	
	
	
}
