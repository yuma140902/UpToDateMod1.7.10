package yuma140902.uptodatemod.blocks;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.util.BlockPos;
import yuma140902.yumalib.api.util.WorldUtils;

public class BlockGrassPath extends Block implements IRegisterable {
	
	private IIcon iconTop;
	
	public BlockGrassPath() {
		super(Material.ground);
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.65F);
		useNeighborBrightness = true;
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("grass_path"));
		setBlockTextureName(StringUtil.name.domainedTexture("grass_path"));
		GameRegistry.registerBlock(this, "grass_path");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(this.getTextureName() + "_side");
		this.iconTop = register.registerIcon(this.getTextureName() + "_top");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == McConst.SIDE_TOP ? this.iconTop : side == McConst.SIDE_BOTTOM ? Blocks.dirt.getIcon(side, 0) : blockIcon;
	}
	
	@Override
	public boolean shouldSideBeRendered(	IBlockAccess world, int x, int y, int z, int side) {
		if(side == McConst.SIDE_TOP) return true;
		return super.shouldSideBeRendered(world, x, y, z, side);
	}
	
	
	private static boolean needEdgeCollisionBox(World world, BlockPos pos, ForgeDirection direction) {
		pos = pos.offset(direction);
		if(WorldUtils.getBlock(world, pos) != MyBlocks.grassPath && !WorldUtils.isAir(world, pos)) {
			return !WorldUtils.noCollisionBox(world, pos.offset(ForgeDirection.UP, 3)) ||
							!WorldUtils.noCollisionBox(world, pos.offset(ForgeDirection.UP));
		}
		return false;
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
		/*
		 * (横から見た図)
		 * 
		 * 　■            □ …… 空気ブロックやドアなど通り抜けられるブロック
		 * ＊□            ■ …… 普通のブロック
		 * ＊□            ＊ …… プレーヤー
		 * ◎■            ◎ …… 草の道ブロック
		 * 
		 * 上のようになったときにプレイヤーが右に通り抜けられるはずなのに通り抜けられない。
		 * マイクラの当たり判定のバグと思われる。
		 * EtFuturumではこれを回避するために、草の道ブロックの当たり判定を1x1x1にしているが、
		 * 私は草の道ブロックの上に乗ったときに少し体が低くなるのが好きなので、全てを1x1x1の当たり判定にしたくない。
		 * そのため、草の道ブロックがその他のブロックに隣接していて、かつ隣接ブロックの3マス上が空気でないときは
		 * でっぱりの当たり判定を追加するようにした。
		 * 
		 * 2020/03/31 yuma140902
		 */
		
		/*
		 * (横から見た図)
		 *
		 * ＊					■ …… 普通のブロック
		 * ＊⊿				⊿ …… 階段ブロックなど
		 * ◎■					＊ …… プレーヤー
		 * 						◎ …… 草の道ブロック
		 *
		 * 上のようになったとき、プレーヤーがジャンプなしで右に行けるとよいが、高さの差が0.5ブロックよりわずかに大きいので行けない。
		 * これを可能にするために、でっぱりの当たり判定を追加するようにした
		 *
		 * 2020/11/01 yuma140902
		 */
		
		BlockPos pos = new BlockPos(x, y, z);
		
		float edge = 31f/32f;
		
		// ベースとなる当たり判定
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 15.0f/16.0f, 1.0f);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		
		if(needEdgeCollisionBox(world, pos, ForgeDirection.EAST)) {  // EAST: +x
			this.setBlockBounds(edge, 0.0f, 0.0f, 	1.0f, 1.0f, 1.0f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		if(needEdgeCollisionBox(world, pos, ForgeDirection.WEST)) {  // WEST: -x
			this.setBlockBounds(0.0f, 0.0f, 0.0f, 	1.0f-edge, 1.0f, 1.0f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		if(needEdgeCollisionBox(world, pos, ForgeDirection.SOUTH)) {  // SOUTH: +z
			this.setBlockBounds(0.0f, 0.0f, edge, 	1.0f, 1.0f, 1.0f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		if(needEdgeCollisionBox(world, pos, ForgeDirection.NORTH)) {
			this.setBlockBounds(0.0f, 0.0f, 0.0f, 	1.0f, 1.0f, 1.0f-edge);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		
		// もとに戻す。戻さないと描画時の表示がおかしくなる
		setBlockBoundsForItemRender();
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsForItemRender();
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBoundsForItemRender();
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 15.0f/16.0f, 1.0f);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		Block blockAbove = world.getBlock(x, y+1, z);
		if(blockAbove != null && blockAbove.getMaterial().isSolid()) {
			world.setBlock(x, y, z, Blocks.dirt);
		}
	}
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return false;
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
	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(Blocks.dirt);
	}
}
