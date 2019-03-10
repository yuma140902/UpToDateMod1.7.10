package yuma140902.uptodatemod.world.generation.structure;

/**
 * 平面上の回転を表す
 * @author yuma1
 * @see Direction2D
 * @see Facing2D
 */
public final class Rotation2D {
	
	public static final int
			DEG0_VALUE = Facing2D.NORTH_VALUE,
			DEG90_VALUE = Facing2D.WEST_VALUE,
			DEG180_VALUE = Facing2D.SOUTH_VALUE,
			DEG270_VALUE = Facing2D.EAST_VALUE;
	
	public static final Rotation2D
			DEG0 = new Rotation2D(DEG0_VALUE),
			DEG90 = new Rotation2D(DEG90_VALUE),
			DEG180 = new Rotation2D(DEG180_VALUE),
			DEG270 = new Rotation2D(DEG270_VALUE);
	
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
	
	private Rotation2D(int value) {
		this.value = value;
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}
}
