package yuma140902.uptodatemod.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeRegister {
	private RecipeRegister() {}
	
	public static void addShaped(ItemStack output, Object... params) {
		if(output == null || output.getItem() == null)
			return;
		
		for(int i = 0; i < params.length; ++i) {
			if(params[i] == null)
				return;
		}
		
		GameRegistry.addShapedRecipe(output, params);
	}
	
	public static void addShapedOre(ItemStack output, Object... params) {
		if(output == null || output.getItem() == null)
			return;
		
		for(int i = 0; i < params.length; ++i) {
			if(params[i] == null)
				return;
		}
		
		GameRegistry.addRecipe(new ShapedOreRecipe(output, params));
	}
	
	public static void addShapeless(ItemStack output, Object... params) {
		if(output == null || output.getItem() == null)
			return;
		
		for(int i = 0; i < params.length; ++i) {
			if(params[i] == null)
				return;
		}
		
		GameRegistry.addShapelessRecipe(output, params);
	}
	
	public static void addShapelessOre(ItemStack output, Object... params) {
		if(output == null || output.getItem() == null)
			return;
		
		for(int i = 0; i < params.length; ++i) {
			if(params[i] == null)
				return;
		}
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, params));
	}
	
	public static void addSmelting(Block input, ItemStack output, float exp) {
		if(input == null) return;
		
		addSmelting(new ItemStack(input), output, exp);
	}
	
	public static void addSmelting(Item input, ItemStack output, float exp) {
		if(input == null) return;
		
		addSmelting(new ItemStack(input), output, exp);
	}
	
	public static void addSmelting(ItemStack input, ItemStack output, float exp) {
		if(input == null || input.getItem() == null || output == null || output.getItem() == null) {
			return;
		}
		
		GameRegistry.addSmelting(input, output, exp);
	}
}
