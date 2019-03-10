package yuma140902.uptodatemod.world.generation.woodland_mansion;

import static yuma140902.uptodatemod.util.Stat.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockStairs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


/**
 * 構造物生成用の相対座標系
 * @author yuma1
 *
 */
public class StructureRelativeCoordinateSystem {
	
	private static enum Operation {
		PLACE_BLOCK,
		SET_TILEENTITY
	}
	
	/**
	 * 相対位置の基準となるx,y,z座標
	 */
	private int originX, originY, originZ;
	
	/**
	 * 相対座標軸の絶対座標系に対する向き。これによってxとz座標の方向が変わる
	 */
	private Rotation2D rotationYaw = Rotation2D.DEG0;
	
	/**
	 * ブロックの設置先のワールド
	 */
	private World world;

	
	public int getOriginX() {
		return originX;
	}
	
	public int getOriginY() {
		return originY;
	}
	
	public int getOriginZ() {
		return originZ;
	}
	
	public Rotation2D getRotationYaw() {
		return rotationYaw;
	}
	
	public World getWorld() {
		return world;
	}
	
	public StructureRelativeCoordinateSystem(int originX, int originY, int originZ, Rotation2D rotationYaw, World world) {
		this.originX = originX;
		this.originY = originY;
		this.originZ = originZ;
		this.rotationYaw = rotationYaw;
		this.world = world;
	}
	
	
	public void setBlock(int relX, int relY, int relZ, Block block) {
		setBlockAndMeta(relX, relY, relZ, block, 0);
	}
	
	public void setBlockAndMeta(int relX, int relY, int relZ, Block block, int meta) {
		operateToAbsoluteCoord(Operation.PLACE_BLOCK, relX, relY, relZ, block, null, meta, 2);
	}
	
	private void operateToAbsoluteCoord(Operation operation, int relX, int relY, int relZ, Block block, TileEntity tileEntity, int meta, int flag) {
		int absX = originX;
		final int absY = originY + relY;
		int absZ = originZ;
		
		switch(rotationYaw.getValue()) {
			case Rotation2D.DEG0_VALUE:
				absX += relX;
				absZ += relZ;
				break;
			case Rotation2D.DEG90_VALUE:
				absX += relZ;
				absZ -= relX;
				break;
			case Rotation2D.DEG180_VALUE:
				absX -= relX;
				absZ -= relZ;
				break;
			case Rotation2D.DEG270_VALUE:
				absX -= relZ;
				absZ += relX;
				break;
		}
		
		if(operation == Operation.SET_TILEENTITY) {
			world.setTileEntity(absX, absY, absZ, tileEntity);
		}
		else {
			world.setBlock(absX, absY, absZ, block, meta, flag);
		}
	}
	
	/**
	 * 
	 * @param relX
	 * @param relY
	 * @param relZ
	 * @param block
	 * @param originMeta
	 * @param world
	 */
	public void setBlockAndRotatedMeta(int relX, int relY, int relZ, Block block, int originMeta) {
		int rotatedMeta = originMeta;
		if(block instanceof BlockStairs) {
			rotatedMeta = getRotatedStairsMeta(originMeta);
		}
		else if(block instanceof BlockRotatedPillar) {
			rotatedMeta = getRotatedPillarMeta(originMeta);
		}
		else if(block instanceof BlockLadder) {
			rotatedMeta = getRotatedLadderMeta(originMeta);
		}
		else if(block instanceof BlockPumpkin) {
			rotatedMeta = getRotatedPumpkinMeta(originMeta);
		}
		
		setBlockAndMeta(relX, relY, relZ, block, rotatedMeta);
	}
	
	/**
	 * 汎用のメタデータ回転メソッド。東西南北の四方向のメタデータをそれぞれ指定する
	 * @param originMeta
	 * @param north
	 * @param west
	 * @param south
	 * @param east
	 * @return
	 */
	private int getRotatedMetaWithCustomDirections(int originMeta, int north, int west, int south, int east) {
		switch(rotationYaw.getValue()) {
			case Rotation2D.DEG90_VALUE:
				return (originMeta == north) ? west :
					      (originMeta == west) ? south :
					      (originMeta == south) ? east :
					      	north;
			
			case Rotation2D.DEG180_VALUE:
				return (originMeta == north) ? south :
					      (originMeta == south) ? north :
					      (originMeta == west) ? east :
					      	west;
				
			case Rotation2D.DEG270_VALUE:
				return (originMeta == north) ? east :
					      (originMeta == east) ? south :
					      (originMeta == south) ? west :
					      	north;
			
			default:
				return originMeta;
		}
	}
	
	/**
	 * 階段のメタデータを回転する。
	 * @param originMeta 北を基準としたときの階段ブロックのメタデータ
	 * @return
	 */
	public int getRotatedStairsMeta(final int originMeta) {
		/*
		 * 階段ブロックのメタデータの仕様
		 * 
		 * 0abb (2進数)
		 * 
		 * a: 下付きなら0, 上付きなら1
		 * bb: 東:0, 西:1, 南:2, 北:3
		 */
		
		int direction = originMeta & 0b0011;
		final int lowOrUp = originMeta & 0b0100;
		
		direction = getRotatedMetaWithCustomDirections(direction, META_STAIRS_NORTH, META_STAIRS_WEST, META_STAIRS_SOUTH, META_STAIRS_EAST);
		
		return lowOrUp | direction;
	}
	
	
	/**
	 * BlockRotatedPillarブロックのメタデータを回転する。
	 * @param originMeta 北を基準としたときのBlockRotatedPillarブロックのメタデータ
	 * @return
	 */
	public int getRotatedPillarMeta(int originMeta) {
		/*
		 * BlockRotatedPillarのメタデータの仕様
		 * 
		 * aabb (2進数)
		 * 
		 * aa: 軸。上下:0, 東西:1, 南北:2, なし:3
		 * bb: 種類
		 * 
		 * つまり、
		 *   上下: 0b0000,
		 *   東西: 0b0100,
		 *   南北: 0b1000,
		 *   なし: 0b1100
		 * となって、90度、270度回転させる場合は8の位の数字と4の位の数字を入れ替えればよいとわかる。
		 * 0度、180度の場合はなにもしなくてよい。
		 */
		
		
		int rotaion = rotationYaw.getValue();
		if(rotaion == Rotation2D.DEG90_VALUE || rotaion == Rotation2D.DEG270_VALUE) {
				//8の位と4の位の数字を入れ替える
				int _8 = (originMeta & 0b1000) >>> 3;
				int _4 = (originMeta & 0b0100) >>> 2;
				int type = originMeta & 0b0011;
				
				return (_4 << 3) | (_8 << 2) | type;
		}
		
		return originMeta;
	}
	
	
	/**
	 * はしごのメタデータを回転する。
	 * @param originMeta 北を基準としたときのはしごブロックのメタデータ
	 * @return
	 */
	public int getRotatedLadderMeta(int originMeta) {
		return getRotatedMetaWithCustomDirections(originMeta, META_LADDER_NORTH, META_LADDER_WEST, META_LADDER_SOUTH, META_LADDER_EAST);
	}
	
	/**
	 * カボチャのメタデータを回転する。
	 * @param originMeta 北を基準としたときのカボチャのメタデータ
	 * @return
	 */
	public int getRotatedPumpkinMeta(int originMeta) {
		return getRotatedMetaWithCustomDirections(originMeta, META_PUMPKIN_NORTH, META_PUMPKIN_WEST, META_PUMPKIN_SOUTH, META_PUMPKIN_EAST);
	}
	
	public void setBlockWithNotify(int relX, int relY, int relZ, Block block, int meta) {
		operateToAbsoluteCoord(Operation.PLACE_BLOCK, relX, relY, relZ, block, null, meta, 3);
	}
	
	public void setTileEntity(int relX, int relY, int relZ, TileEntity tileentity) {
		operateToAbsoluteCoord(Operation.SET_TILEENTITY, relX, relY, relZ, null, tileentity, 0, 0);
	}
}
