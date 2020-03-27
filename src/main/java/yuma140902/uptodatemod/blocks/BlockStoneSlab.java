package yuma140902.uptodatemod.blocks;

import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.yumalib_ee.api.blocks.BlockGenericSlab;

public class BlockStoneSlab extends BlockGenericSlab {
	
	public BlockStoneSlab() {
		super(Blocks.stone, 0, "slab_stone");
	}
	
	
	@Override
	public void registerRecipes() {
		if(!ModConfigCore.Recipe.useOldSmoothStoneSlabRecipe()) super.registerRecipes();
	}
}
