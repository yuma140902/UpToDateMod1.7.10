package yuma140902.uptodatemod.util;

public class DirectionUtil {
	private DirectionUtil() {}
	
	private static int getBack(int side) {
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
	
	private static int getRightSide(int side) {
		switch (side) {
			case Stat.SIDE_NORTH: return Stat.SIDE_EAST;
			case Stat.SIDE_EAST: return Stat.SIDE_SOUTH;
			case Stat.SIDE_SOUTH: return Stat.SIDE_WEST;
			case Stat.SIDE_WEST: return Stat.SIDE_NORTH;
			default: return 0;
		}
	}
	
	private static int getLeftSide(int side) {
		switch (side) {
			case Stat.SIDE_NORTH: return Stat.SIDE_WEST;
			case Stat.SIDE_WEST: return Stat.SIDE_SOUTH;
			case Stat.SIDE_SOUTH: return Stat.SIDE_EAST;
			case Stat.SIDE_EAST: return Stat.SIDE_NORTH;
			default: return 0;
		}
	}
}
