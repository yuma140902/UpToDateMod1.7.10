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
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.items.ItemBlockRedSandStone;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockRedSandStone extends BlockSandStone implements IRegisterable, IHasRecipes {
	
	public static final String[] names = new String[] {"", "chiseled", "cut", "smooth"};
	public static final int META_MAX = names.length - 1;
	
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
		setBlockName(StringUtil.getDomainedUnlocalizedName("red_sandstone"));
		setBlockTextureName(StringUtil.getDomainedTextureName("red_sandstone"));
		GameRegistry.registerBlock(this, ItemBlockRedSandStone.class, "red_sandstone");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(meta == 3) {
			return sideIcons[3];
		}
		
		if (side != 1 && (side != 0 || meta != 1 && meta != 2)) {
			if (side == 0) {
				return this.bottomIcon;
			}
			else {
				if (meta < 0 || meta >= this.sideIcons.length) meta = 0;
				return this.sideIcons[meta];
			}
		}
		else {
			return this.topIcon;
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
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
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.redSandStone, 1, 0),
				"##",
				"##",
				'#', new ItemStack(Blocks.sand, 1, 1)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.redSandStone, 4, 2),
				"##",
				"##",
				'#', new ItemStack(MyBlocks.redSandStone, 1, 0)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.redSandStone, 1, 1),
				"H",
				"H",
				'H', MyBlocks.slabRedSandstone
				);
	}
}
