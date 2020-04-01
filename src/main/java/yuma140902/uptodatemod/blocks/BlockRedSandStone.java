package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSandStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.items.ItemBlockRedSandStone;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;

public class BlockRedSandStone extends BlockSandStone implements IRegisterable, IHasRecipes {
	
	public static final String[] names = new String[] {"", "chiseled", "cut", "smooth"};
	public static final int META_MAX = names.length - 1;
	
	public static final int META_NORMAL = 0, META_CHISELED = 1, META_CUT = 2, META_SMOOTH = 3;
	
  @SideOnly(Side.CLIENT)
  private IIcon[] sideIcons;
  @SideOnly(Side.CLIENT)
  private IIcon topIcon;
  @SideOnly(Side.CLIENT)
  private IIcon bottomIcon;
	
	public BlockRedSandStone() {
		setStepSound(soundTypePiston);
		setHardness(0.8F);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("red_sandstone"));
		setBlockTextureName(StringUtil.name.domainedTexture("red_sandstone"));
		GameRegistry.registerBlock(this, ItemBlockRedSandStone.class, "red_sandstone");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(meta == 3) {
			return topIcon;
		}
		else if(side == McConst.SIDE_TOP) {
			return topIcon;
		}
		else if(side == McConst.SIDE_BOTTOM) {
			return bottomIcon;
		}
		else {
			if(meta < 0 || this.sideIcons.length <= meta) meta = 0;
			return this.sideIcons[meta];
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for(int meta = 0; meta <= META_MAX; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		this.sideIcons = new IIcon[names.length];
		
		for (int i = 0; i < this.sideIcons.length; ++i) {
			this.sideIcons[i] = register.registerIcon(this.getTextureName() + StringUtil.surfix("_", names[i]));
		}
		
		this.topIcon = register.registerIcon(this.getTextureName() + "_top");
		this.bottomIcon = register.registerIcon(this.getTextureName() + "_bottom");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.redSandStone, 1, META_NORMAL),
				"##",
				"##",
				'#', new ItemStack(Blocks.sand, 1, 1)
				);
		
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.redSandStone, 4, META_CUT),
				"##",
				"##",
				'#', new ItemStack(MyBlocks.redSandStone, 1, META_NORMAL)
				);
		
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.redSandStone, 1, META_CHISELED),
				"H",
				"H",
				'H', MyBlocks.slabRedSandstone
				);
		
		RecipeRegister.addSmelting(new ItemStack(this, 1, META_NORMAL), new ItemStack(this, 1, META_SMOOTH), McConst.EXP_STONE);
	}
}
