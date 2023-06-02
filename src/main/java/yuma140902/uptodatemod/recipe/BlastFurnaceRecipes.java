package yuma140902.uptodatemod.recipe;

import java.util.HashMap;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class BlastFurnaceRecipes {
	
	private HashMap<ItemStack, BlastFurnaceRecipe> recipes = new HashMap<>();
	
	@Nullable
	public BlastFurnaceRecipe getRecipe(ItemStack material) {
		if(material == null) {
			return null;
		}
		
		BlastFurnaceRecipe recipe;
		recipe = this.recipes.get(material);
		if(recipe != null) {
			return recipe;
		}
		
		ItemStack product = FurnaceRecipes.smelting().getSmeltingResult(material);
		if(product == null) {
			return null;
		}
		float experience = FurnaceRecipes.smelting().func_151398_b(material);
		
		BlastFurnaceRecipe newRecipe = new BlastFurnaceRecipe(material, product, experience);
		this.recipes.put(material, newRecipe);
		return newRecipe;
	}
}
