package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockCoarseDirt extends Block implements IRegisterable {

	public BlockCoarseDirt() {
		super(Material.ground);
		setHardness(0.5F);
		setStepSound(soundTypeGravel);
		setHarvestLevel("shovel", 0);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".coarse_dirt");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":coarse_dirt");
		GameRegistry.registerBlock(this, "coarse_dirt");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int p_149673_5_) {
		Material material = world.getBlock(x, y + 1, z).getMaterial();

    if (material == Material.snow || material == Material.craftedSnow)
    {
        return Blocks.grass.getIcon(world, x, y, z, p_149673_5_);
    }
		
		return super.getIcon(world, x, y, z, p_149673_5_);
	}
	
}
