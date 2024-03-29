package yuma140902.uptodatemod.integration;

import java.util.List;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.registry.HoeEfficientBlockRegistry;

class PluginEtFuturum implements IConfiguratingPlugin, ITweakingPlugin {
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
	
	private Boolean _isModLoadedCache = null;
	@Override
	public boolean isModLoaded() {
		if(_isModLoadedCache == null) {
			return _isModLoadedCache = Loader.isModLoaded(MOD_ID);
		}
		return _isModLoadedCache.booleanValue();
	}
	
	@Override
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
	
	@Override
	public void wrapConfig(Configuration cfg) {
		// TODO
		if(!isModLoaded()) {
			return;
		}
		
		
	}
	
	// ================= IConfiguratingPlugin ここまで =================
	
	public void getNamesToRemoveFromRecipe(List<String> list) {
		if(EnumDisableableFeatures.stones.featureEnabled()) {
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
			list.add("etfuturum:stone");
		}
		if(EnumDisableableFeatures.ironTrapdoor.featureEnabled()) {
			list.add("etfuturum:iron_trapdoor");
		}
		if(EnumDisableableFeatures.redSandstone.featureEnabled()) {
			list.add("etfuturum:red_sandstone");
		}
		if(EnumDisableableFeatures.coarseDirt.featureEnabled()) {
			list.add("etfuturum:coarse_dirt");
		}
		if(EnumDisableableFeatures.endstoneBricks.featureEnabled()) {
			list.add("etfuturum:end_bricks");
		}
		if(EnumDisableableFeatures.doors.featureEnabled()) {
			list.add("etfuturum:door_spruce");
			list.add("etfuturum:door_birch");
			list.add("etfuturum:door_jungle");
			list.add("etfuturum:door_acacia");
			list.add("etfuturum:door_dark_oak");
		}
		if(EnumDisableableFeatures.fences.featureEnabled()) {
			list.add("etfuturum:fence_oak");
			list.add("etfuturum:fence_oak");
			list.add("etfuturum:fence_spruce");
			list.add("etfuturum:fence_birch");
			list.add("etfuturum:fence_jungle");
			list.add("etfuturum:fence_acacia");
			list.add("etfuturum:fence_dark_oak");
		}
		if(EnumDisableableFeatures.fenceGates.featureEnabled()) {
			list.add("etfuturum:fence_gate_spruce");
			list.add("etfuturum:fence_gate_birch");
			list.add("etfuturum:fence_gate_jungle");
			list.add("etfuturum:fence_gate_acacia");
			list.add("etfuturum:fence_gate_dark_oak");
		}
		if(EnumDisableableFeatures.armorStand.featureEnabled()) {
			list.add("etfuturum:wooden_armorstand");
		}
	}
	
	@Override public void tweakMod() {
		FMLControlledNamespacedRegistry<Block> blockRegistry = GameData.getBlockRegistry();
		HoeEfficientBlockRegistry.INSTANCE.addBlock(blockRegistry.getObject("etfuturum:nether_wart"));
		HoeEfficientBlockRegistry.INSTANCE.addBlock(blockRegistry.getObject("etfuturum:sponge"));
	}
}
