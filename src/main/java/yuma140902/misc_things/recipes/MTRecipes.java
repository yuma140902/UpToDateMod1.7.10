package yuma140902.misc_things.recipes;

import net.minecraft.block.Block;
import yuma140902.misc_things.MTVariantBlocks;
import yuma140902.yumalib.api.IHasRecipes;

public class MTRecipes {
	public static void registerAllRecipes() {
		for(final Block block : MTVariantBlocks.getBlocks()) {
			if(block instanceof IHasRecipes) {
				((IHasRecipes) block).registerRecipes();
			}
		}
		
	}
}
