package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.mcmodlib.api.IHasRecipes;
import yuma140902.mcmodlib.api.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGlazedTerracotta extends Block implements IRegisterable, IHasRecipes {

	public static final int META_NORTH = 2, META_EAST = 3, META_SOUTH = 0, META_WEST = 1;
	
	private int colorMeta;
	
	@SideOnly(Side.CLIENT)
	private IIcon icon;
	@SideOnly(Side.CLIENT)
	private IIcon iconFliped;
	
	public BlockGlazedTerracotta(int colorMeta) {
		super(Material.rock);
		setHardness(1.4F);
		setStepSound(soundTypeStone);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		this.colorMeta = colorMeta;
	}
	
	@Override
	public void register() {
		String colorName = ColorUtil.metaToString(colorMeta);
		this.setBlockName(StringUtil.getDomainedUnlocalizedName("glazed_terracotta." + colorName));
		this.setBlockTextureName(StringUtil.getDomainedTextureName("glazed_terracotta_" + colorName));
		GameRegistry.registerBlock(this, "glazed_terracotta_" + colorName);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, colorMeta), new ItemStack(this), 0.3F);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		icon = register.registerIcon(getTextureName());
		iconFliped = register.registerIcon(getTextureName() + "_flip");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == Stat.SIDE_BOTTOM) return iconFliped;
		else if(side == Stat.SIDE_NORTH && meta%2 == 0) return iconFliped;
		else if(side == Stat.SIDE_EAST && meta%2 == 1) return iconFliped;
		return icon;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		int rotation = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, rotation, 3);
	}
	
	@Override
	public int getRenderType() {
		return ModUpToDateMod.glazedTerracottaRenderId;
	}
	
}
