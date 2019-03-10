package yuma140902.uptodatemod.world.generation.structure;

/**
 * 上下左右の方向を表す
 * @author yuma1
 * @see Rotation2D
 * @see Facing2D
 */
public final class Direction2D {
	
	public static final int
			UP_VALUE = Facing2D.NORTH_VALUE,
			LEFT_VALUE = Facing2D.WEST_VALUE,
			DOWN_VALUE = Facing2D.SOUTH_VALUE,
			RIGHT_VALUE = Facing2D.EAST_VALUE;
	
	public static final Direction2D
			UP = new Direction2D(UP_VALUE),
			LEFT = new Direction2D(LEFT_VALUE),
			DOWN = new Direction2D(DOWN_VALUE),
			RIGHT = new Direction2D(RIGHT_VALUE);
	
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
	
	
	private Direction2D(int value) {
		this.value = value;
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}
}
