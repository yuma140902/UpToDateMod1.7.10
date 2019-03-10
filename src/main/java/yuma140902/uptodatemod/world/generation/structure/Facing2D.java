package yuma140902.uptodatemod.world.generation.structure;

/**
 * 東西南北の方角を表す
 * @author yuma1
 * @see Direction2D
 * @see Rotation2D
 */
public final class Facing2D {

	public static final int
			NORTH_VALUE = 0,
			WEST_VALUE = 1,
			SOUTH_VALUE = 2,
			EAST_VALUE = 3;
	
	public static final Facing2D
			NORTH = new Facing2D(NORTH_VALUE),
			WEST = new Facing2D(WEST_VALUE),
			SOUTH = new Facing2D(SOUTH_VALUE),
			EAST = new Facing2D(EAST_VALUE);

	/*
	 * z座標の減る方向がマイクラ世界での北である
	 * 	
	 *       UP NORTH 0°
	 *        |
	 *        |
	 * LEFT   |         RIGHT
	 * -------+-----------> X
	 * WEST   |         EAST
	 * 90°    |         270°
	 *        v
	 *        Z
	 *       DOWN SOUTH 180°
	 */
	
	
	
	private Facing2D(int value) {
		this.value = value;
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}
	
}
