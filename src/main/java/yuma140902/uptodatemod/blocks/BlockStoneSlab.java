package yuma140902.uptodatemod.blocks;

import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.yumalib.api.blocks.BlockGenericSlab;

public class BlockStoneSlab extends BlockGenericSlab {
	
	public BlockStoneSlab() {
		super(false, Blocks.stone, 0, "slab_stone", null, false);
	}
	
	
	@Override
	public void registerRecipes() {
		if(!ModConfigCore.Recipe.useOldSmoothStoneSlabRecipe()) super.registerRecipes();
	}
}
