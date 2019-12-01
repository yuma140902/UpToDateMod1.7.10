package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.config.ModConfigCore.*;
import yuma140902.uptodatemod.config.IConfigBridge;

public class IntegrationConfigs {
	private IntegrationConfigs() {}
	
	public static final String CATEGORY_INTEGRATION = getSubCategory("Integration");
	
	public static CategoryIntegration category;
	
	public static void initConfig(IConfigBridge cfg) {
		category = (CategoryIntegration) cfg.root().addSubCategory(CategoryIntegration::new);
		cfg.initCategory(category);
		
		Plugins.initConfig(cfg);
	}

	public static void syncConfig(IConfigBridge cfg) {
		Plugins.syncConfig(cfg);
	}

	public static void wrapConfig(IConfigBridge cfg) {
		Plugins.wrapConfig(cfg);
	}
	
}
