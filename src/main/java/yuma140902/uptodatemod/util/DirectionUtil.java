package yuma140902.uptodatemod.util;

import static yuma140902.yumalib.api.McConst.*;
import yuma140902.yumalib.api.util.BlockPos;

public class DirectionUtil {
	private DirectionUtil() {}
	
	public static int getBack(int side) {
		switch (side) {
			case SIDE_NORTH: return SIDE_SOUTH;
			case SIDE_SOUTH: return SIDE_NORTH;
			case SIDE_WEST: return SIDE_EAST;
			case SIDE_EAST: return SIDE_WEST;
			case SIDE_TOP: return SIDE_BOTTOM;
			case SIDE_BOTTOM: return SIDE_TOP;
			default: return 0;
		}
	}
	
	public static int getRightSide(int side) {
		switch (side) {
			case SIDE_NORTH: return SIDE_EAST;
			case SIDE_EAST: return SIDE_SOUTH;
			case SIDE_SOUTH: return SIDE_WEST;
			case SIDE_WEST: return SIDE_NORTH;
			default: return 0;
		}
	}
	
	public static int getLeftSide(int side) {
		switch (side) {
			case SIDE_NORTH: return SIDE_WEST;
			case SIDE_WEST: return SIDE_SOUTH;
			case SIDE_SOUTH: return SIDE_EAST;
			case SIDE_EAST: return SIDE_NORTH;
			default: return 0;
		}
	}
	
	public static final int[] rightSides = new int[] {SIDE_NORTH, SIDE_SOUTH, SIDE_EAST, SIDE_WEST, SIDE_NORTH, SIDE_SOUTH };
	
	public static final int[] leftSides = new int[] {SIDE_SOUTH, SIDE_NORTH, SIDE_WEST, SIDE_EAST, SIDE_SOUTH, SIDE_NORTH };
	
	//座標(x, y, z)のブロックのside側の隣の座標を返します
	public static BlockPos getCoordBySide(int side, int x, int y, int z) {
		switch (side) {
			case SIDE_BOTTOM: 	return new BlockPos(x  , y-1, z  );
			case SIDE_TOP: 			return new BlockPos(x  , y+1, z  );
			case SIDE_NORTH: 	return new BlockPos(x  , y  , z-1);
			case SIDE_SOUTH: 	return new BlockPos(x  , y  , z+1);
			case SIDE_WEST: 		return new BlockPos(x-1, y  , z  );
			case SIDE_EAST: 		return new BlockPos(x+1, y  , z  );
			
			default: return new BlockPos(0, 0, 0);
		}
	}
}
