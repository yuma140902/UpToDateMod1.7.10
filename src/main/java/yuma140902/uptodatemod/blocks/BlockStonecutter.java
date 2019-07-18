package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockStonecutter extends Block/*Container*/ implements IRegisterable, IHasRecipes {
	
	public static final int META_NORTH = 2, META_EAST = 3, META_SOUTH = 0, META_WEST = 1;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop, iconTop90, iconBottom, iconSide, iconSaw;
	
	public BlockStonecutter() {
		super(Material.rock);
		setStepSound(soundTypePiston);
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5625f, 1.0f);
		setLightOpacity(0);
		setHardness(3.5f);
		setResistance(10.0F);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("stonecutter"));
		setBlockTextureName(StringUtil.getDomainedModTextureName("stonecutter"));
		GameRegistry.registerBlock(this, "stonecutter");
	}
	
	@Override
	public void registerRecipes() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		iconTop = register.registerIcon(getTextureName() + "_top");
		iconTop90 = register.registerIcon(getTextureName() + "_top90");
		iconBottom = register.registerIcon(getTextureName() + "_bottom");
		iconSide = register.registerIcon(getTextureName() + "_side");
		iconSaw = register.registerIcon(getTextureName() + "_saw");
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0d, y + 0.5d, z + 1.0d);
	}
	
	// ================= 登録処理 ここまで =================
	
	
	
	// ================= 描画処理 ここから =================
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return
				side == Stat.SIDE_TOP && (meta == META_NORTH || meta == META_SOUTH) ? iconTop :
				 side == Stat.SIDE_TOP && (meta == META_EAST || meta == META_WEST) ? iconTop90 :
				 side == Stat.SIDE_BOTTOM ? iconBottom :
				 iconSide;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	// ================= 描画処理 ここまで =================
	
	
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		super.onBlockPlacedBy(world, x, y, z, player, itemstack);
		
		int rotation = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
    world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
	}
}
