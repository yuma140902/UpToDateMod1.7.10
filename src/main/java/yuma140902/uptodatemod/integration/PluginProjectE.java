package yuma140902.uptodatemod.integration;

import javax.annotation.Nullable;
import cpw.mods.fml.common.Loader;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockStone;

public class PluginProjectE implements ITweakingPlugin {

public static final PluginProjectE INSTANCE = new PluginProjectE();
	
	public static final String MOD_ID = "ProjectE";
	
	@Nullable
	private Boolean _isModLoadedCache = null;
	public boolean isModLoaded() {
		if(_isModLoadedCache == null) {
			return _isModLoadedCache = Loader.isModLoaded(MOD_ID);
		}
		return _isModLoadedCache.booleanValue();
	}
	
	public boolean isIntegrationEnabled() {
		return isModLoaded();
	}
	
	@Override
	public void tweakMod() {
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(MyBlocks.stone, 1, BlockStone.META_GRANITE), 16);
		System.out.println("registered EMC!!");
	}
	
}
