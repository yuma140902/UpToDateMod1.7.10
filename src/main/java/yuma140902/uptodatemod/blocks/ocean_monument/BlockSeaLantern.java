package yuma140902.uptodatemod.blocks.ocean_monument;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockSeaLantern extends Block implements IRegisterable{
	public BlockSeaLantern() {
		super(Material.glass);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(0.3F);
		setLightLevel(1.0F);
		setStepSound(soundTypeGlass);
	}
	
	@Override
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".sea_lantern");
		setBlockTextureName(ModUpToDateMod.MOD_ID + ":sea_lantern");
		GameRegistry.registerBlock(this, "sea_lantern");
	}
}
