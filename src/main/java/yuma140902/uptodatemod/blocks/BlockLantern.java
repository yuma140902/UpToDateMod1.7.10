package yuma140902.uptodatemod.blocks;

import static yuma140902.yumalib.api.McConst.*;
import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.blocks.CustomSoundType;
import yuma140902.yumalib.api.util.WorldUtils;

public class BlockLantern extends Block implements IRegisterable, IHasRecipes {
	
	public static final int META_ON_GROUND = 0, META_HANGING = 1;
	
	protected final static SoundType soundTypeLantern = new CustomSoundType(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "lantern");

	protected final static Vec3 bottomSutainingPoint = Vec3.createVectorHelper(0.5d, 0.5d/16d, 0.5d);
	protected final static Vec3 topSutainingPoint = Vec3.createVectorHelper(0.5d, 1.0d-0.5d/16d, 0.5d);
	
	public BlockLantern() {
		super(Material.iron);
		setResistance(3.5F);
		setHardness(3.5F);
		setLightLevel(1.0F);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(CreativeTabs.tabDecorations);
		setStepSound(soundTypeLantern);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("lantern"));
		setBlockTextureName(StringUtil.name.domainedTexture("lantern"));
		GameRegistry.registerBlock(this, "lantern");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapedOre(
				new ItemStack(this),
				"...",
				".|.",
				"...",
				'.', "nuggetIron",
				'|', Blocks.torch
				);
	}
	
	
	private static boolean canSustainLanternOnTop(World world, int x, int y, int z)
	{
		if (World.doesBlockHaveSolidTopSurface(world, x, y, z)) {
			return true;
		}
		else if(world.getBlock(x, y, z).canPlaceTorchOnTop(world, x, y, z)) {
			return true;
		}
		else {
			Block block = world.getBlock(x, y, z);
			block.setBlockBoundsBasedOnState(world, x, y, z);
			AxisAlignedBB aabb = block.getCollisionBoundingBoxFromPool(world, x, y, z);
			if(aabb == null) return false;
			return aabb.isVecInside(topSutainingPoint.addVector(x, y, z));
		}
	}
	
	protected static boolean canSustainLanternOnBottom(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		block.setBlockBoundsBasedOnState(world, x, y, z);
		AxisAlignedBB aabb = block.getCollisionBoundingBoxFromPool(world, x, y, z);
		if(aabb == null) return false;
		return aabb.isVecInside(bottomSutainingPoint.addVector(x, y, z));
	}
	
	protected static boolean canLanternStayAt(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == META_HANGING) {
			return 
					WorldUtils.isSideSolid(world, x, y+1, z, ForgeDirection.DOWN, false) ||
					canSustainLanternOnBottom(world, x, y+1, z);
		}
		else {
			return
					WorldUtils.isSideSolid(world, x, y-1, z, ForgeDirection.UP, false) ||
					canSustainLanternOnTop(world, x, y-1, z);
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return
				WorldUtils.isSideSolid(world, x, y+1, z, ForgeDirection.DOWN, false) ||
				canSustainLanternOnBottom(world, x, y+1, z) ||
				WorldUtils.isSideSolid(world, x, y-1, z, ForgeDirection.UP, false) ||
				canSustainLanternOnTop(world, x, y-1, z);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if(WorldUtils.isSideSolid(world, x, y+1, z, ForgeDirection.DOWN, false) || canSustainLanternOnBottom(world, x, y+1, z)) {
			if(side == SIDE_BOTTOM) return META_HANGING;
			else if(!WorldUtils.isSideSolid(world, x, y-1, z, ForgeDirection.UP, false) && !canSustainLanternOnTop(world, x, y-1, z)) return META_HANGING;
		}
		return META_ON_GROUND;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		if(!canLanternStayAt(world, x, y, z)) {
			if(!world.isRemote) {
				ItemStack itemstack = new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
				float f = 0.7F;
				double xd = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
				double yd = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
				double zd = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
				EntityItem entityitem = new EntityItem(world, (double)x + xd, (double)y + yd, (double)z + zd, itemstack);
				entityitem.delayBeforeCanPickup = 10;
				world.spawnEntityInWorld(entityitem);
			}
			world.setBlockToAir(x, y, z);
		}
	}

	
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
		
		if(world.getBlockMetadata(x, y, z) == META_HANGING) {
			setBlockBoundsWithState(this, META_HANGING, true);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
			
			setBlockBoundsWithState(this, META_HANGING, false);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		else {
			setBlockBoundsWithState(this, META_ON_GROUND, true);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);

			setBlockBoundsWithState(this, META_ON_GROUND, false);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		// もとに戻す。戻さないと描画時の表示がおかしくなる
		setBlockBoundsWithState(this, META_ON_GROUND, true);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsWithState(this, world.getBlockMetadata(x, y, z), true);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBoundsWithState(this, world.getBlockMetadata(x, y, z), true);
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBoundsWithState(this, META_ON_GROUND, true);
	}
	
	/**
	 * 
	 * @param meta メタデータ
	 * @param isBody trueなら本体部分、falseなら上の出っ張りの部分
	 */
	public static void setBlockBoundsWithState(Block block, int meta, boolean isBody) {
		if(meta == META_HANGING) {
			if(isBody) block.setBlockBounds(5.0f/16.0f, 1.0f/16.0f, 5.0f/16.0f, 11.0f/16.0f, 8.0f/16.0f, 11.0f/16.0f);
			else block.setBlockBounds(6.0f/16.0f, 8.0f/16.0f, 6.0f/16.0f, 10.0f/16.0f, 10.0f/16.0f, 10.0f/16.0f);
		}
		else {
			if(isBody) block.setBlockBounds(5.0f/16.0f, 0.0f, 5.0f/16.0f, 11.0f/16.0f, 7.0f/16.0f, 11.0f/16.0f);
			else block.setBlockBounds(6.0f/16.0f, 7.0f/16.0f, 6.0f/16.0f, 10.0f/16.0f, 9.0f/16.0f, 10.0f/16.0f);
		}
	}
	
	@Override
	public int getRenderType() {
		return ModUpToDateMod.lanternRenderId;
	}
}
