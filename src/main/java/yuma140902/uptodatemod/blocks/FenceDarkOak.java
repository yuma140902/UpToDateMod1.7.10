package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceDarkOak extends FenceBase implements IRegisterable {
	public FenceDarkOak() {
		super("planks_big_oak");
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_dark_oak");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_dark_oak");
		GameRegistry.registerBlock(this, "fence_dark_oak");
	}
}
