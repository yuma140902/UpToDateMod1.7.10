package yuma140902.uptodatemod.items.ocean_monument;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class PrismarineCrystals extends Item implements IRegisterable {
	public PrismarineCrystals() {
  }
  
  public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".prismarine_crystals");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":prismarine_crystals");
		GameRegistry.registerItem(this, "prismarine_crystals");
  }
}
