package yuma140902.uptodatemod.util;

public final class Stat {
	private Stat() {}
	
	public static final int
		PLANK_META_OAK = 0,
		PLANK_META_ACACIA = 4,
		PLANK_META_BIRCH = 2,
		PLANK_META_DARKOAK = 5,
		PLANK_META_JUNGLE = 3,
		PLANK_META_SPRUCE = 1;
	
	public static final int
		LOG_META_OAK = 0,
		LOG_META_SPRUCE = 1,
		LOG_META_BIRCH = 2,
		LOG_META_JUNGLE = 3,
		LOG2_META_ACACIA = 0,
		LOG2_META_DARK_OAK = 1;
	
	public static final int
		META_STAIRS_EAST = 0,
		META_STAIRS_WEST = 1,
		META_STAIRS_SOUTH = 2,
		META_STAIRS_NORTH = 3;
	
	public static final int
		META_STAIRS_LOWER = 0b0000,
		META_STAIRS_UPPER = 0b0100;
	
	public static final int
		META_PILLAR_UP_DOWN = 0b0000,
		META_PILLAR_WEST_EAST = 0b0100,
		META_PILLAR_NORTH_SOUTH = 0b1000,
		META_PILLAR_AXIS_NONE = 0b1100;
	
	public static final int
		META_PUMPKIN_NORTH = 0,
		META_PUMPKIN_EAST = 1,
		META_PUMPKIN_SOUTH = 2,
		META_PUMPKIN_WEST = 3;
	
	public static final int
		META_LADDER_SOUTH = 2,
		META_LADDER_NORTH = 3,
		META_LADDER_EAST = 4,
		META_LADDER_WEST = 5;
	
	/**
	 * ポピー
	 */
	public static final int META_REDFLOWER_POPPY = 0;
	
	/**
	 * ヒスイラン
	 */
	public static final int META_REDFLOWER_BLUE_ORCHID = 1;
	
	/**
	 * レンゲソウ
	 */
	public static final int META_REDFLOWER_ALLIUM = 2;
	
	/**
	 * ヒナソウ
	 */
	public static final int META_REDFLOWER_HOUSTONIA = 3;
	
	public static final int META_REDFLOWER_RED_TULIP= 4;
	public static final int META_REDFLOWER_ORANGE_TULIP = 5;
	public static final int META_REDFLOWER_WHITE_TULIP = 6;
	public static final int META_REDFLOWER_PINK_TULIP = 7;
	
	/**
	 * フランスギク
	 */
	public static final int META_REDFLOWER_OXEYE_DAISY= 8;
	
	
	public static final int
		SIDE_BOTTOM = 0,
		SIDE_TOP = 1,
		SIDE_NORTH = 2,
		SIDE_SOUTH = 3,
		SIDE_WEST = 4,
		SIDE_EAST = 5;
	
	public static final String
		PROXY_CLIENT = "yuma140902.uptodatemod.proxy.ClientProxy",
		PROXY_SERVER = "yuma140902.uptodatemod.proxy.ServerProxy",
		MOD_CONFIG_GUI_FACTORY = "yuma140902.uptodatemod.gui.ModConfigGuiFactory";
}
