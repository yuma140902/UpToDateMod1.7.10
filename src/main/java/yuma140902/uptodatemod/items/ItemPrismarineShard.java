package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class ItemPrismarineShard extends Item implements IRegisterable{

	public ItemPrismarineShard() {
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".prismarine_shard");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":prismarine_shard");
		GameRegistry.registerItem(this, "prismarine_shard");
	}
	
}
