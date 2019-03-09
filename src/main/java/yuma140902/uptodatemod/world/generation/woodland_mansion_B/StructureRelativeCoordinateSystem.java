package yuma140902.uptodatemod.world.generation.woodland_mansion_B;

import static yuma140902.uptodatemod.util.Stat.*;
import net.minecraft.block.Block;
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
				absX = originX + relX;
				absZ = originZ + relZ;
				break;
			case Rotation2D.DEG90_VALUE:
				absX = originZ + relZ;
				absZ = originX - relX;
				break;
			case Rotation2D.DEG180_VALUE:
				absX = originX - relX;
				absZ = originZ - relZ;
				break;
			case Rotation2D.DEG270_VALUE:
				absX = originZ - relZ;
				absZ = originX + relX;
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
		
		setBlockAndMeta(relX, relY, relZ, block, rotatedMeta);
	}
	
	/**
	 * 階段のメタデータを回転する。
	 * @param originMeta 北を基準としたときの階段ブロックのメタデータ
	 * @return
	 */
	public int getRotatedStairsMeta(final int originMeta) {
		//TODO:
		final int EAST = META_STAIRS_EAST, WEST = META_STAIRS_WEST, SOUTH = META_STAIRS_SOUTH, NORTH = META_STAIRS_NORTH;
		/*
		 * 階段ブロックのメタデータの使用
		 * 
		 * 0abb (2進数)
		 * 
		 * a: 下付きなら0, 上付きなら1
		 * bb: 東:0, 西:1, 南:2, 北:3
		 */
		
		int direction = originMeta & 0b0011;
		final int lowOrUp = originMeta & 0b0100;
		
		switch(rotationYaw.getValue()) {
			case Rotation2D.DEG90_VALUE:
				direction = (direction == NORTH) ? WEST : 
					          (direction == WEST) ? SOUTH : 
					          (direction == SOUTH) ? EAST : 
					          	                     NORTH;
				break;
			case Rotation2D.DEG180_VALUE:
				direction = (direction == NORTH) ? SOUTH :
					          (direction == WEST)  ? EAST :
					          (direction == SOUTH) ? NORTH :
					          	                     WEST;
				break;
			case Rotation2D.DEG270_VALUE:
				direction = (direction == NORTH) ? EAST :
					          (direction == WEST) ? NORTH :
					          (direction == SOUTH) ? WEST :
					          	                    SOUTH;
				break;
		}
		
		return lowOrUp | direction;
	}
	
	
	/**
	 * BlockRotatedPillarブロックのメタデータを回転する。
	 * @param originMeta 北を基準としたときのBlockRotatedPillarブロックのメタデータ
	 * @return
	 */
	public int getRotatedPillarMeta(int originMeta) {
		/*
		 * BlockRotatedPillarのメタデータの使用
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
		if(rotationYaw.getValue() == Rotation2D.DEG90_VALUE) {
			switch(originMeta) {
				case META_LADDER_NORTH:
					return META_LADDER_WEST;
					
				case META_LADDER_WEST:
					return META_LADDER_SOUTH;
					
				case META_LADDER_SOUTH:
					return META_LADDER_EAST;
					
				case META_LADDER_EAST:
					return META_LADDER_NORTH;
			}
		}
		
		else if(rotationYaw.getValue() == Rotation2D.DEG180_VALUE) {
			switch(originMeta) {
				case META_LADDER_NORTH:
					return META_LADDER_SOUTH;

				case META_LADDER_SOUTH:
					return META_LADDER_NORTH;

				case META_LADDER_EAST:
					return META_LADDER_WEST;

				case META_LADDER_WEST:
					return META_LADDER_EAST;
			}
		}
		
		else if(rotationYaw.getValue() == Rotation2D.DEG270_VALUE) {
			switch(originMeta) {
				case META_LADDER_NORTH:
					return META_LADDER_EAST;
					
				case META_LADDER_EAST:
					return META_LADDER_SOUTH;
					
				case META_LADDER_SOUTH:
					return META_LADDER_WEST;
					
				case META_LADDER_WEST:
					return META_LADDER_NORTH;
					
			}
		}
		
		return originMeta;
	}
	
	public void setBlockWithNotify(int relX, int relY, int relZ, Block block, int meta) {
		operateToAbsoluteCoord(Operation.PLACE_BLOCK, relX, relY, relZ, block, null, meta, 3);
	}
	
	public void setTileEntity(int relX, int relY, int relZ, TileEntity tileentity) {
		operateToAbsoluteCoord(Operation.SET_TILEENTITY, relX, relY, relZ, null, tileentity, 0, 0);
	}
}
