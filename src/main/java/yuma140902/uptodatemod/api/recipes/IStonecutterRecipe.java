package yuma140902.uptodatemod.api.recipes;

import net.minecraft.item.ItemStack;

public interface IStonecutterRecipe {
	ItemStack getProduct();
	int getProductNum();
	ItemStack getMaterial();
	int getMaterialNum();
	
	boolean matches(ItemStack materialCand);
	
	ItemStack getCraftingResult(ItemStack material);
	
	boolean hasReverseRecipe();
	IStonecutterRecipe getReverseRecipe();
	
}
