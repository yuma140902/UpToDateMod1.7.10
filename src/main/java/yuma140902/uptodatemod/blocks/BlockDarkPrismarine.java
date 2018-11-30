package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockDarkPrismarine extends Block implements IRegisterable{
	public BlockDarkPrismarine() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
	}
	
	@Override
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".dark_prismarine_block");
		setBlockTextureName(ModUpToDateMod.MOD_ID + ":dark_prismarine_block");
		GameRegistry.registerBlock(this, "dark_prismarine_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.diamondColor;
	}
}
