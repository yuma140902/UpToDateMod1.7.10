package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGlazedTerracotta extends Block implements IRegisterable {

	public static final int META_NORTH = 2, META_EAST = 3, META_SOUTH = 0, META_WEST = 1;
	
	private int colorMeta;
	
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		int rotation = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, rotation, 3);
	}
	
	@Override
	public int getRenderType() {
		return ModUpToDateMod.glazedTerracottaRenderId;
	}
	
}
