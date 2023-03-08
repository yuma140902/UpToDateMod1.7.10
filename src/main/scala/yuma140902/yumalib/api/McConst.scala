package yuma140902.yumalib.api

object McConst {

  /**
   * バニラMinecraftのメタデータに関する定数
   */
  object Meta {
    val PLANK_OAK = 0
    val PLANK_ACACIA = 4
    val PLANK_BIRCH = 2
    val PLANK_DARKOAK = 5
    val PLANK_JUNGLE = 3
    val PLANK_SPRUCE = 1

    val LOG_OAK = 0
    val LOG_SPRUCE = 1
    val LOG_BIRCH = 2
    val LOG_JUNGLE = 3
    val LOG2_ACACIA = 0
    val LOG2_DARK_OAK = 1

    val STAIRS_EAST = 0
    val STAIRS_WEST = 1
    val STAIRS_SOUTH = 2
    val STAIRS_NORTH = 3

    val STAIRS_LOWER: Int = 0x0 //0b0000
    val STAIRS_UPPER: Int = 0x4 //0b0100

    val PILLAR_UP_DOWN: Int = 0x0 //0b0000
    val PILLAR_WEST_EAST: Int = 0x4 //0b0100
    val PILLAR_NORTH_SOUTH: Int = 0x8 //0b1000
    val PILLAR_AXIS_NONE: Int = 0xc //0b1100

    val PUMPKIN_NORTH = 0
    val PUMPKIN_EAST = 1
    val PUMPKIN_SOUTH = 2
    val PUMPKIN_WEST = 3

    val LADDER_SOUTH = 2
    val LADDER_NORTH = 3
    val LADDER_EAST = 4
    val LADDER_WEST = 5

    val CHEST_SOUTH = 2
    val CHEST_NORTH = 3
    val CHEST_EAST = 4
    val CHEST_WEST = 5

    val SLAB_STONE = 0
    val SLAB_SANDSTONE = 1
    val SLAB_ROCKPLANK = 2
    val SLAB_COBBLESTONE = 3
    val SLAB_BRICKS = 4
    val SLAB_STONEBRICKS = 5
    val SLAB_NETHERBRICKS = 6
    val SLAB_QUARTZ = 7

    val IS_SLAB_UPPER: Int = 0x8 //0b1000
    val IS_STAIRS_UPSIDE_DOWN: Int = 0x4 //0b0100


    /** ポピー */
    val REDFLOWER_POPPY = 0
    /** ヒスイラン */
    val REDFLOWER_BLUE_ORCHID = 1
    /** レンゲソウ */
    val REDFLOWER_ALLIUM = 2
    /** ヒナソウ */
    val REDFLOWER_HOUSTONIA = 3
    val REDFLOWER_RED_TULIP = 4
    val REDFLOWER_ORANGE_TULIP = 5
    val REDFLOWER_WHITE_TULIP = 6
    val REDFLOWER_PINK_TULIP = 7
    /** フランスギク */
    val REDFLOWER_OXEYE_DAISY = 8

    val SANDSTONE_NORMAL = 0
    val SANDSTONE_CHISELED = 1
    val SANDSTONE_CUT = 2

    val STONEBRICK_NORMAL = 0
    val STONEBRICK_MOSSY = 1
    val STONEBRICK_CRACKED = 2
    val STONEBRICK_CHISELED = 3
  }

  object Particle {
    val HUGE_EXPLOSION = "hugeexplosion"
    val LARGE_EXPLODE = "largeexplode"
    val FIREWORK_SPARK = "fireworksSpark"
    val BUBBLE = "bubble"
    val SUSPENDED = "suspended"
    val DEPTH_SUSPEND = "depthsuspend"
    val TOWN_AURA = "townaura"
    val CRITICAL = "crit"
    val MAGIC_CRITICAL = "magicCrit"
    val SMOKE = "smoke"
    val MOB_SPELL = "mobSpell"
    val MOB_SPELL_AMBIENT = "mobSpellAmbient"
    val SPELL = "spell"
    val INSTANT_SPELL = "instantSpell"
    val WITCH_MAGIC = "witchMagic"
    val NOTE = "note"
    val PORTAL = "portal"
    val ENCHANTMENT_TABLE = "enchantmenttable"
    val EXPLODE = "explode"
    val FLAME = "flame"
    val LAVA = "lava"
    val FOOTSTEP = "footstep"
    val SPLASH = "splash"
    val WAKE = "wake"
    val LARGE_SMOKE = "largesmoke"
    val CLOUD = "cloud"
    val REDDUST = "reddust"
    val SNOWBALLPOOF = "snowballpoof"
    val DRIP_WATER = "dripWater"
    val DRIP_LAVA = "dripLava"
    val SNOW_SHOVEL = "snowshovel"
    val SLIME = "slime"
    val HEART = "heart"
    val ANGRY_VILLAGER = "angryVillager"
    val HAPPY_VILLAGER = "happyVillager";
  }

  val SIDE_BOTTOM = 0
  val SIDE_TOP = 1
  val SIDE_NORTH = 2
  val SIDE_SOUTH = 3
  val SIDE_WEST = 4
  val SIDE_EAST = 5;

  val EXP_STONE = 0.1F
  val EXP_COAL = 0.15F
  val EXP_CACTUS = 0.2F
  val EXP_BRICK = 0.3F
  val EXP_MEAT = 0.35F
  val EXP_IRON = 0.7F
  val EXP_GEM = 1.0F;
}
