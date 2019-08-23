package yuma140902.uptodatemod.api.register;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.api.recipes.IStonecutterRecipe;


public interface IStonecutterRecipeRegistry {
	
	void addRecipe(IStonecutterRecipe recipe);
	
	void addRecipe(ItemStack material, int materialNum, ItemStack product, int productNum);
	
	void addRecipe(ItemStack material, int materialNum, ItemStack product, int productNum, boolean addReverseRecipe);
	
	IStonecutterRecipe createRecipeObj(ItemStack material, int materialNum, ItemStack product, int productNum);
	
	void removeRecipe(IStonecutterRecipe recipe);
	
	void removeAll(ItemStack product);
	
	Iterator<IStonecutterRecipe> getRecipes(ItemStack material);
	
	boolean areEqual(IStonecutterRecipe a, IStonecutterRecipe b);
	
}
