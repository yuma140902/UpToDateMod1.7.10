package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;

public class BlockSmoothQuartz extends Block implements IRegisterable, IHasRecipes {
	public BlockSmoothQuartz() {
		super(Material.rock);
		setStepSound(soundTypePiston);
		setHardness(0.8F);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("smooth_quartz"));
		GameRegistry.registerBlock(this, "smooth_quartz");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		return;
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addSmelting(new ItemStack(Blocks.quartz_block, 1, 0), new ItemStack(this), McConst.EXP_BRICK);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.quartz_block.getIcon(McConst.SIDE_BOTTOM, 0);
	}
}
