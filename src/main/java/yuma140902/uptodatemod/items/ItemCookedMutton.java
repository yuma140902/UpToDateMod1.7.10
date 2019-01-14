package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyItems;

public class ItemCookedMutton extends ItemFood implements IRegisterable, IHasRecipes {
	public ItemCookedMutton() {
		super(6, false);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".cooked_mutton");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":cooked_mutton");
		GameRegistry.registerItem(this, "cooked_mutton");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addSmelting(new ItemStack(MyItems.rawMutton), new ItemStack(this), 5);
	}
}
