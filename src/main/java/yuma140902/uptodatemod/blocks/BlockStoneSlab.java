package yuma140902.uptodatemod.blocks;

import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.blocks.generics.BlockGenericSlab;

public class BlockStoneSlab extends BlockGenericSlab {
	
	public BlockStoneSlab() {
		super(Blocks.stone, 0, "slab_stone");
	}
	
	
	@Override
	public void registerRecipes() {
		if(!ModUpToDateMod.config.recipe.addOldSmoothStoneSlab.get()) super.registerRecipes();
	}
}
