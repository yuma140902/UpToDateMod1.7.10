package yuma140902.uptodatemod.world.generation.woodland_mansion_B;

public final class Facing2D {
	private Facing2D() {}
	
	public static final int
			UP = 0,
			LEFT = 1,
			DOWN = 2,
			RIGHT = 3;
	
	public static final int
			NORTH = UP,
			WEST = LEFT,
			SOUTH = DOWN,
			EAST = RIGHT;
	
	public static final int
			DEG_0 = NORTH,
			DEG_90 = WEST,
			DEG_180 = SOUTH,
			DEG_270 = EAST;
	
	/*
	 * z座標の減る方向がマイクラ世界での北である
	 * 
	 *       UP NORTH
	 *        |
	 *        |
	 * LEFT   |         RIGHT
	 * -------+-----------> X
	 * WEST   |         EAST
	 *        |
	 *        v
	 *        Z
	 *       DOWN SOUTH
	 */
}
