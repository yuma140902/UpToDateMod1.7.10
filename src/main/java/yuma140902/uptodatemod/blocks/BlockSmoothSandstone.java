package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockSandStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;

public class BlockSmoothSandstone extends BlockSandStone implements IRegisterable, IHasRecipes {
	public BlockSmoothSandstone() {
		setStepSound(soundTypePiston);
		setHardness(0.8F);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("smooth_sandstone"));
		GameRegistry.registerBlock(this, "smooth_sandstone");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		return;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(this, 1, 0));
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.sandstone.getIcon(McConst.SIDE_TOP, meta);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addSmelting(new ItemStack(Blocks.sandstone, 1, McConst.Meta.SANDSTONE_NORMAL), new ItemStack(this), McConst.EXP_STONE);
	}
}
