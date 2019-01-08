package yuma140902.uptodatemod.util;

public class DirectionUtil {
	private DirectionUtil() {}
	
	public static int getBack(int side) {
		switch (side) {
			case Stat.SIDE_NORTH: return Stat.SIDE_SOUTH;
			case Stat.SIDE_SOUTH: return Stat.SIDE_NORTH;
			case Stat.SIDE_WEST: return Stat.SIDE_EAST;
			case Stat.SIDE_EAST: return Stat.SIDE_WEST;
			case Stat.SIDE_TOP: return Stat.SIDE_BOTTOM;
			case Stat.SIDE_BOTTOM: return Stat.SIDE_TOP;
			default: return 0;
		}
	}
	
	public static int getRightSide(int side) {
		switch (side) {
			case Stat.SIDE_NORTH: return Stat.SIDE_EAST;
			case Stat.SIDE_EAST: return Stat.SIDE_SOUTH;
			case Stat.SIDE_SOUTH: return Stat.SIDE_WEST;
			case Stat.SIDE_WEST: return Stat.SIDE_NORTH;
			default: return 0;
		}
	}
	
	public static int getLeftSide(int side) {
		switch (side) {
			case Stat.SIDE_NORTH: return Stat.SIDE_WEST;
			case Stat.SIDE_WEST: return Stat.SIDE_SOUTH;
			case Stat.SIDE_SOUTH: return Stat.SIDE_EAST;
			case Stat.SIDE_EAST: return Stat.SIDE_NORTH;
			default: return 0;
		}
	}
	
	public static final int[] rightSides = new int[] {Stat.SIDE_NORTH, Stat.SIDE_SOUTH, Stat.SIDE_EAST, Stat.SIDE_WEST, Stat.SIDE_NORTH, Stat.SIDE_SOUTH };
	
	public static final int[] leftSides = new int[] {Stat.SIDE_SOUTH, Stat.SIDE_NORTH, Stat.SIDE_WEST, Stat.SIDE_EAST, Stat.SIDE_SOUTH, Stat.SIDE_NORTH };
	
	//座標(x, y, z)のブロックのside側の隣の座標を返します
	public static BlockCoordinate3 getCoordBySide(int side, int x, int y, int z) {
		switch (side) {
			case Stat.SIDE_BOTTOM: 	return new BlockCoordinate3(x  , y-1, z  );
			case Stat.SIDE_TOP: 			return new BlockCoordinate3(x  , y+1, z  );
			case Stat.SIDE_NORTH: 	return new BlockCoordinate3(x  , y  , z-1);
			case Stat.SIDE_SOUTH: 	return new BlockCoordinate3(x  , y  , z+1);
			case Stat.SIDE_WEST: 		return new BlockCoordinate3(x-1, y  , z  );
			case Stat.SIDE_EAST: 		return new BlockCoordinate3(x+1, y  , z  );
			
			default: return new BlockCoordinate3(0, 0, 0);
		}
	}
}
