package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceSpruce extends FenceBase implements IRegisterable {
	public FenceSpruce() {
		super("planks_spruce");
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_spruce");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_spruce");
		GameRegistry.registerBlock(this, "fence_spruce");
	}
}
