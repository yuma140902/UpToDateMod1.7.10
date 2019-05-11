package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.config.ModConfigCore.*;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.config.Configuration;

public class IntegrationConfigPlugin implements IConfigurablePlugin {
	private IntegrationConfigPlugin() {}
	
	public static final IntegrationConfigPlugin INSTANCE = new IntegrationConfigPlugin();
	
	public static final String CATEGORY_INTEGRATION = getSubCategory("Integration");
	
	@Override
	public void initConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_INTEGRATION, "Settings to cooperate with other mods");
		cfg.setCategoryLanguageKey(CATEGORY_INTEGRATION, getCategoryLangkey("integration"));
		cfg.setCategoryRequiresMcRestart(CATEGORY_INTEGRATION, true);
		
		PluginEtFuturum.INSTANCE.initConfig(cfg);
	}

	@Override
	public void syncConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.syncConfig(cfg);
	}

	@Override
	public void wrapConfig(Configuration cfg) {
		PluginEtFuturum.INSTANCE.wrapConfig(cfg);
	}
	
	
	
	// ==================================
	
	
	public List<String> getNamesToRemoveFromRecipe() {
		List<String> list = new ArrayList<>();
		PluginEtFuturum.INSTANCE.getNamesToRemoveFromRecipe(list);
		return list;
	}
}
