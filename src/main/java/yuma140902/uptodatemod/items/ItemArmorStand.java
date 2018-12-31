package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;

public class ItemArmorStand extends Item implements IRegisterable, IHasRecipes {
	
	public ItemArmorStand() {
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".armor_stand");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":armor_stand");
		GameRegistry.registerItem(this, "armor_stand");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(this),
				"III",
				" I ",
				"IOI",
				'I', Items.stick,
				'O', new ItemStack(Blocks.stone_slab, 1, 0)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(this),
				"III",
				" I ",
				"IOI",
				'I', Items.stick,
				'O', MyBlocks.slabStone
				);
	}
}
