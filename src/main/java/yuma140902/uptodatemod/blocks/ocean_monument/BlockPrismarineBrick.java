package yuma140902.uptodatemod.blocks.ocean_monument;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockPrismarineBrick extends Block implements IRegisterable {
	public BlockPrismarineBrick() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
	}
	
	@Override
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".prismarine_brick");
		setBlockTextureName(ModUpToDateMod.MOD_ID + ":prismarine_brick");
		GameRegistry.registerBlock(this, "prismarine_brick");
	}
}
