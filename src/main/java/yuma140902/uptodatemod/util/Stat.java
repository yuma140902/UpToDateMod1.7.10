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
