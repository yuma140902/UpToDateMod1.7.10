package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockRedNetherBrick extends Block implements IRegisterable {

	public BlockRedNetherBrick() {
		super(Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".red_nether_brick");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":red_nether_brick");
		GameRegistry.registerBlock(this, "red_nether_brick");
	}
	
}
