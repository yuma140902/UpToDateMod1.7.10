package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceJungle extends FenceBase implements IRegisterable {
	public FenceJungle() {
		super("planks_jungle");
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_jungle");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_jungle");
		GameRegistry.registerBlock(this, "fence_jungle");
	}
}
