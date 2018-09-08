package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceAcacia extends FenceBase implements IRegisterable {
	public FenceAcacia() {
		super("planks_acacia");
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_acacia");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_acacia");
		GameRegistry.registerBlock(this, "fence_acacia");
	}
}
