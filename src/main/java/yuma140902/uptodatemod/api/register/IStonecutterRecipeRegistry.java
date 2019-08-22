package yuma140902.uptodatemod.api.register;

import java.util.Iterator;
import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.api.recipes.IStonecutterRecipe;


public interface IStonecutterRecipeRegistry {
	
	void addRecipe(IStonecutterRecipe recipe);
	
	void removeRecipe(IStonecutterRecipe recipe);
	
	void removeAll(ItemStack product);
	
	@Nonnull
	Iterator<IStonecutterRecipe> getRecipes(ItemStack material);
	
}
