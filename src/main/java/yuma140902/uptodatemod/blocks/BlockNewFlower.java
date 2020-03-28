package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.items.ItemPlainDye;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.items.ItemBlockMultiName;

public class BlockNewFlower extends BlockBush implements IRegisterable, IHasRecipes {
	
	public static String[] names = new String[] {"cornflower", "lily_of_the_valley"};
	public static int MAX_META = names.length - 1;
	public static int CORNFLOWER = 0;
	public static int LILY = 1;
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockNewFlower() {
		super(Material.plants);
		setStepSound(soundTypeGrass);
		float f = 0.2F;
    this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
    this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("flower"));
		setBlockTextureName(StringUtil.getDomainedTextureName("flower"));
		GameRegistry.registerBlock(this, Companion.class, "flower");
	}
	
	
	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return super.canPlaceBlockOn(block) || block == Blocks.flower_pot || block == MyBlocks.unlimitedPot;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta % 2;
	}
	
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapeless(new ItemStack(MyItems.dye, 1, ItemPlainDye.BLUE), new ItemStack(this, 1, CORNFLOWER));
		RecipeRegister.addShapeless(new ItemStack(MyItems.dye, 1, ItemPlainDye.WHITE), new ItemStack(this, 1, LILY));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		this.icons = new IIcon[names.length];
		
		for(int meta=0; meta < icons.length; ++meta) {
			this.icons[meta] = register.registerIcon(this.getTextureName() + "_" + names[meta]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[meta];
	}
	
	@SuppressWarnings({
		"unchecked", "rawtypes"
	})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int meta=0; meta <= MAX_META; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
	
	public static class Companion extends ItemBlockMultiName {
		
		public Companion(Block block) {
			super(block, names);
		}
		
		@Override
		public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
			@SuppressWarnings("null")
			boolean success = MyBlocks.unlimitedPot.putItemIn(itemstack, player, world, x, y, z);
			if(success) {
				return true;
			}else {
				return super.onItemUse(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
			}
		}
	}
}
