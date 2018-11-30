package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class ItemPrismarineCrystals extends Item implements IRegisterable {
	public ItemPrismarineCrystals() {
		setCreativeTab(CreativeTabs.tabMaterials);
  }
  
  public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".prismarine_crystals");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":prismarine_crystals");
		GameRegistry.registerItem(this, "prismarine_crystals");
  }
}
