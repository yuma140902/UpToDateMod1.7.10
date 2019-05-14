package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.util.StringUtil;

public class ItemCookedMutton extends ItemFood implements IRegisterable, IHasRecipes {
	public ItemCookedMutton() {
		super(6, false);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName("cooked_mutton"));
		this.setTextureName(StringUtil.getDomainedTextureName("cooked_mutton"));
		GameRegistry.registerItem(this, "cooked_mutton");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addSmelting(new ItemStack(MyItems.rawMutton), new ItemStack(this), 5);
	}
}
