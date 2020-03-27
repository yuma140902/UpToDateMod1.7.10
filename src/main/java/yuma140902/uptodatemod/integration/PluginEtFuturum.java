package yuma140902.uptodatemod.integration;

import java.util.List;
import javax.annotation.Nullable;
import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;

class PluginEtFuturum implements IConfiguratingPlugin {
	private PluginEtFuturum() {}
	
	public static final PluginEtFuturum INSTANCE = new PluginEtFuturum();
	
	public static final String MOD_ID = "etfuturum";
	
	@Override
	public String getModId() {
		return MOD_ID;
	}
	
	@Override
	public String getModName() {
		return "Et Futurum";
	}
	
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
	
	
	// ================= IConfiguratingPlugin ここから =================
	
	public static final String CATEGORY = ModConfigCore.getSubCategory("Integration.EtFuturum");
	
	public boolean config_integrateWithEtFuturum = true;
	
	@Override
	public void initConfig(Configuration cfg) {
		if(!isModLoaded()) {
			return;
		}
		
		cfg.addCustomCategoryComment(CATEGORY, "Settings to cooperate with EtFuturum");
		cfg.setCategoryLanguageKey(CATEGORY, ModConfigCore.getCategoryLangkey("integration.etfuturum").toString());
		cfg.setCategoryRequiresMcRestart(CATEGORY, true);
	}

	@Override
	public void syncConfig(Configuration cfg) {
		if(!isModLoaded()) {
			return;
		}
		
		config_integrateWithEtFuturum = cfg.getBoolean("integrate", CATEGORY, true, 
				"Cooperate with EtFuturum or not | EtFuturumと連携するかどうか",
				ModConfigCore.getPropertyLangkey("integrate_etfuturum").toString());
		
	}
	
	public void wrapConfig(Configuration cfg) {
		// TODO
		if(!isModLoaded()) {
			return;
		}
		
		
	}
	
	// ================= IConfiguratingPlugin ここまで =================
	
	public void getNamesToRemoveFromRecipe(List<String> list) {
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.stones)) {
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.ironTrapdoor)) {
			list.add("etfuturum:iron_trapdoor");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.redSandstone)) {
			list.add("etfuturum:red_sandstone");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.coarseDirt)) {
			list.add("etfuturum:coarse_dirt");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.endstoneBricks)) {
			list.add("etfuturum:end_bricks");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.doors)) {
			list.add("etfuturum:door_spruce");
			list.add("etfuturum:door_birch");
			list.add("etfuturum:door_jungle");
			list.add("etfuturum:door_acacia");
			list.add("etfuturum:door_dark_oak");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.fences)) {
			list.add("etfuturum:fence_oak");
			list.add("etfuturum:fence_oak");
			list.add("etfuturum:fence_spruce");
			list.add("etfuturum:fence_birch");
			list.add("etfuturum:fence_jungle");
			list.add("etfuturum:fence_acacia");
			list.add("etfuturum:fence_dark_oak");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.fenceGates)) {
			list.add("etfuturum:fence_gate_spruce");
			list.add("etfuturum:fence_gate_birch");
			list.add("etfuturum:fence_gate_jungle");
			list.add("etfuturum:fence_gate_acacia");
			list.add("etfuturum:fence_gate_dark_oak");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.armorStand)) {
			list.add("etfuturum:wooden_armorstand");
		}
	}
	
}
