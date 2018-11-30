package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockNetherWart extends Block implements IRegisterable {

	public BlockNetherWart() {
		super(Material.grass);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".nether_wart_block");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":nether_wart_block");
		GameRegistry.registerBlock(this, "nether_wart_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.redColor;
	}
	
}
