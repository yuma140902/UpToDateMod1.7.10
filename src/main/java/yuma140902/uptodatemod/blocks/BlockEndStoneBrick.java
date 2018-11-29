package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockEndStoneBrick extends Block implements IRegisterable{
	
	public BlockEndStoneBrick() {
		super(Material.rock);
		setHardness(3.0F);
		setResistance(15.0F);
		setStepSound(soundTypePiston);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".end_stone_brick");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":end_stone_brick");
		GameRegistry.registerBlock(this, "end_stone_brick");
	}
}
