package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockGenericStrippedLog extends BlockRotatedPillar implements IRegisterable {
	
	private String name;
	
	public BlockGenericStrippedLog(String name) {
		super(Material.wood);
		this.name = name;
		
		setHardness(2.0F);
		setStepSound(soundTypeWood);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		setBlockTextureName(ModUpToDateMod.MOD_ID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_) {
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		field_150164_N = register.registerIcon(getTextureName() + "_top");
	}
	
	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int meta) {
		byte b0 = 4;
		int i1 = b0 + 1;
		
		if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
			for (int j1 = -b0; j1 <= b0; ++j1) {
				for (int k1 = -b0; k1 <= b0; ++k1) {
					for (int l1 = -b0; l1 <= b0; ++l1) {
						Block block = world.getBlock(x + j1, y + k1, z + l1);
						if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
							block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
						}
					}
				}
			}
		}
	}
}
