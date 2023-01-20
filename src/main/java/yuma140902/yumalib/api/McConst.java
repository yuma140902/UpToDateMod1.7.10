package yuma140902.yumalib.api;

/**
 * バニラMinecraftに関する定数
 */
public final class McConst {

	/**
	 * バニラMinecraftのメタデータに関する定数
	 */
	public static final class Meta {
		public static final int
						PLANK_OAK     = 0,
						PLANK_ACACIA  = 4,
						PLANK_BIRCH   = 2,
						PLANK_DARKOAK = 5,
						PLANK_JUNGLE  = 3,
						PLANK_SPRUCE  = 1;
		
		public static final int
						LOG_OAK       = 0,
						LOG_SPRUCE    = 1,
						LOG_BIRCH     = 2,
						LOG_JUNGLE    = 3,
						LOG2_ACACIA   = 0,
						LOG2_DARK_OAK = 1;
		
		public static final int
						STAIRS_EAST  = 0,
						STAIRS_WEST  = 1,
						STAIRS_SOUTH = 2,
						STAIRS_NORTH = 3;
		
		public static final int
						STAIRS_LOWER = 0b0000,
						STAIRS_UPPER = 0b0100;
		
		public static final int
						PILLAR_UP_DOWN     = 0b0000,
						PILLAR_WEST_EAST   = 0b0100,
						PILLAR_NORTH_SOUTH = 0b1000,
						PILLAR_AXIS_NONE   = 0b1100;
		
		public static final int
						PUMPKIN_NORTH = 0,
						PUMPKIN_EAST  = 1,
						PUMPKIN_SOUTH = 2,
						PUMPKIN_WEST  = 3;
		
		public static final int
						LADDER_SOUTH = 2,
						LADDER_NORTH = 3,
						LADDER_EAST  = 4,
						LADDER_WEST  = 5;
		
		public static final int
						CHEST_SOUTH = 2,
						CHEST_NORTH = 3,
						CHEST_EAST  = 4,
						CHEST_WEST  = 5;
		
		public static final int
						SLAB_STONE        = 0,
						SLAB_SANDSTONE    = 1,
						SLAB_ROCKPLANK    = 2,
						SLAB_COBBLESTONE  = 3,
						SLAB_BRICKS       = 4,
						SLAB_STONEBRICKS  = 5,
						SLAB_NETHERBRICKS = 6,
						SLAB_QUARTZ       = 7;
		
		public static final int
						IS_SLAB_UPPER = 0b1000;
		
		public static final int
						IS_STAIRS_UPSIDE_DOWN = 0b0100;
		
		
		/** ポピー */
		public static final int REDFLOWER_POPPY = 0;
		
		/** ヒスイラン */
		public static final int REDFLOWER_BLUE_ORCHID = 1;
		
		/** レンゲソウ */
		public static final int REDFLOWER_ALLIUM = 2;
		
		/** ヒナソウ */
		public static final int REDFLOWER_HOUSTONIA = 3;
		
		public static final int REDFLOWER_RED_TULIP    = 4;
		public static final int REDFLOWER_ORANGE_TULIP = 5;
		public static final int REDFLOWER_WHITE_TULIP  = 6;
		public static final int REDFLOWER_PINK_TULIP   = 7;
		
		/** フランスギク */
		public static final int REDFLOWER_OXEYE_DAISY = 8;
		
		public static final int
						SANDSTONE_NORMAL   = 0,
						SANDSTONE_CHISELED = 1,
						SANDSTONE_CUT      = 2;
		
		public static final int
						STONEBRICK_NORMAL   = 0,
						STONEBRICK_MOSSY    = 1,
						STONEBRICK_CRACKED  = 2,
						STONEBRICK_CHISELED = 3;
	}


	/**
	 * バニラMinecraftのパーティクルに関する定数
	 */
	public static final class Particle {
		public static final String
						HUGE_EXPLOSION    = "hugeexplosion",
						LARGE_EXPLODE     = "largeexplode",
						FIREWORK_SPARK    = "fireworksSpark",
						BUBBLE            = "bubble",
						SUSPENDED         = "suspended",
						DEPTH_SUSPEND     = "depthsuspend",
						TOWN_AURA         = "townaura",
						CRITICAL          = "crit",
						MAGIC_CRITICAL    = "magicCrit",
						SMOKE             = "smoke",
						MOB_SPELL         = "mobSpell",
						MOB_SPELL_AMBIENT = "mobSpellAmbient",
						SPELL             = "spell",
						INSTANT_SPELL     = "instantSpell",
						WITCH_MAGIC       = "witchMagic",
						NOTE              = "note",
						PORTAL            = "portal",
						ENCHANTMENT_TABLE = "enchantmenttable",
						EXPLODE           = "explode",
						FLAME             = "flame",
						LAVA              = "lava",
						FOOTSTEP          = "footstep",
						SPLASH            = "splash",
						WAKE              = "wake",
						LARGE_SMOKE       = "largesmoke",
						CLOUD             = "cloud",
						REDDUST           = "reddust",
						SNOWBALLPOOF      = "snowballpoof",
						DRIP_WATER        = "dripWater",
						DRIP_LAVA         = "dripLava",
						SNOW_SHOVEL       = "snowshovel",
						SLIME             = "slime",
						HEART             = "heart",
						ANGRY_VILLAGER    = "angryVillager",
						HAPPY_VILLAGER    = "happyVillager";
	}
	
	
	public static final int
					SIDE_BOTTOM = 0,
					SIDE_TOP    = 1,
					SIDE_NORTH  = 2,
					SIDE_SOUTH  = 3,
					SIDE_WEST   = 4,
					SIDE_EAST   = 5;
	
	public static final float
					EXP_STONE  = 0.1F,
					EXP_COAL   = 0.15F,
					EXP_CACTUS = 0.2F,
					EXP_BRICK  = 0.3F,
					EXP_MEAT   = 0.35F,
					EXP_IRON   = 0.7F,
					EXP_GEM    = 1.0F;
	
}
