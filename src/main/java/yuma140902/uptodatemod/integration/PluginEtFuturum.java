package yuma140902.uptodatemod.integration;

import java.util.List;
import javax.annotation.Nullable;
import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.config.ModConfigCore;

public class PluginEtFuturum implements IPlugin, IConfigurablePlugin {
	private PluginEtFuturum() {}
	
	public static final PluginEtFuturum INSTANCE = new PluginEtFuturum();
	
	public static final String MOD_ID = "etfuturum";
	
	@Nullable
	private Boolean _isModLoadedCache = null;
	public boolean isModLoaded() {
		if(_isModLoadedCache == null) {
			return _isModLoadedCache = Loader.isModLoaded(MOD_ID);
		}
		return _isModLoadedCache.booleanValue();
	}
	
	public boolean isIntegrationEnabled() {
		return isModLoaded() && config_integrateWithEtFuturum;
	}
	
	
	// ================= IConfigurablePlugin ここから =================
	
	public static final String CATEGORY = ModConfigCore.getSubCategory("Integration.EtFuturum");
	
	public boolean config_integrateWithEtFuturum = true;
	
	@Override
	public void initConfig(Configuration cfg) {
		if(!isModLoaded()) {
			return;
		}
		
		cfg.addCustomCategoryComment(CATEGORY, "Settings to cooperate with EtFuturum");
		cfg.setCategoryLanguageKey(CATEGORY, ModConfigCore.getCategoryLangkey("integration.etfuturum"));
		cfg.setCategoryRequiresMcRestart(CATEGORY, true);
	}

	@Override
	public void syncConfig(Configuration cfg) {
		if(!isModLoaded()) {
			return;
		}
		
		config_integrateWithEtFuturum = cfg.getBoolean("integrate", CATEGORY, true, 
				"Cooperate with EtFuturum or not | EtFuturumと連携するかどうか",
				ModConfigCore.getPropertyLangkey("integrate"));
		
	}
	
	public void wrapConfig(Configuration cfg) {
		// TODO
		if(!isModLoaded()) {
			return;
		}
		
		
	}
	
	// ================= IConfigurablePlugin ここまで =================
	
	public void getNamesToRemoveFromRecipe(List<String> list) {
		if(!isIntegrationEnabled()) {
			return;
		}
		list.add("etfuturum:stone");
		list.add("etfuturum:stone");
		list.add("etfuturum:stone");
		list.add("etfuturum:stone");
		list.add("etfuturum:stone");
		list.add("etfuturum:stone");
		list.add("etfuturum:iron_trapdoor");
		list.add("etfuturum:red_sandstone");
		list.add("etfuturum:coarse_dirt");
		list.add("etfuturum:end_bricks");
		list.add("etfuturum:door_spruce");
		list.add("etfuturum:door_birch");
		list.add("etfuturum:door_jungle");
		list.add("etfuturum:door_acacia");
		list.add("etfuturum:door_dark_oak");
		list.add("etfuturum:fence_oak");
		list.add("etfuturum:fence_oak");
		list.add("etfuturum:fence_spruce");
		list.add("etfuturum:fence_birch");
		list.add("etfuturum:fence_jungle");
		list.add("etfuturum:fence_acacia");
		list.add("etfuturum:fence_dark_oak");
		list.add("etfuturum:fence_gate_spruce");
		list.add("etfuturum:fence_gate_birch");
		list.add("etfuturum:fence_gate_jungle");
		list.add("etfuturum:fence_gate_acacia");
		list.add("etfuturum:fence_gate_dark_oak");
		list.add("etfuturum:wooden_armorstand");
	}
	
}
