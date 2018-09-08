package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceBirch extends FenceBase implements IRegisterable {
	public FenceBirch() {
		super("planks_birch");
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_birch");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_birch");
		GameRegistry.registerBlock(this, "fence_birch");
	}
}
