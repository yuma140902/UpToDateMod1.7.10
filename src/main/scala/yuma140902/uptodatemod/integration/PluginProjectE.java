package yuma140902.uptodatemod.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.Loader;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;

class PluginProjectE implements ITweakingPlugin {

	public static final PluginProjectE INSTANCE = new PluginProjectE();
	
	public static final String MOD_ID = "ProjectE";
	public static final Logger logger = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-Plugin PorjectE");
	
	@Override
	public String getModId() {
		return MOD_ID;
	}
	
	@Override
	public String getModName() {
		return MOD_ID;
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
		return isModLoaded();
	}
	
	@Override
	public void tweakMod() {
		try {
			IEMCProxy emcProxy = ProjectEAPI.getEMCProxy();
			if(EnumDisableableFeatures.stones.featureEnabled()) {
				emcProxy.registerCustomEMC(new ItemStack(MyBlocks.stone, 1, BlockStone.META_GRANITE), 16);
				emcProxy.registerCustomEMC(new ItemStack(MyBlocks.stone, 1, BlockStone.META_DIORITE), 16);
				emcProxy.registerCustomEMC(new ItemStack(MyBlocks.stone, 1, BlockStone.META_ANDESITE), 16);
			}
			if(EnumDisableableFeatures.concreteAndConcretePowder.featureEnabled()) {
				for(int meta = 0; meta <= 15; ++meta) {
					emcProxy.registerCustomEMC(new ItemStack(MyBlocks.concretePowder, 1, meta), 4);
					emcProxy.registerCustomEMC(new ItemStack(MyBlocks.concreteBlock, 1, meta), 4);
				}
			}
			
			logger.info("Registered EMC");
		}
		catch(Exception ex) {
			logger.error("Failed to register EMC");
			ex.printStackTrace();
		}
		
	}
	
}
