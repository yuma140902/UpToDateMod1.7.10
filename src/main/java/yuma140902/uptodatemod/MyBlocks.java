package yuma140902.uptodatemod;

import static yuma140902.uptodatemod.registry.EnumDisableableFeatures.*;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.blocks.BlockBarrel;
import yuma140902.uptodatemod.blocks.BlockBone;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.blocks.BlockConcrete;
import yuma140902.uptodatemod.blocks.BlockConcretePowder;
import yuma140902.uptodatemod.blocks.BlockDarkPrismarine;
import yuma140902.uptodatemod.blocks.BlockDoorAcacia;
import yuma140902.uptodatemod.blocks.BlockDoorBirch;
import yuma140902.uptodatemod.blocks.BlockDoorDarkOak;
import yuma140902.uptodatemod.blocks.BlockDoorJungle;
import yuma140902.uptodatemod.blocks.BlockDoorSpruce;
import yuma140902.uptodatemod.blocks.BlockEndStoneBricks;
import yuma140902.uptodatemod.blocks.BlockGlazedTerracotta;
import yuma140902.uptodatemod.blocks.BlockGrassPath;
import yuma140902.uptodatemod.blocks.BlockIronTrapDoor;
import yuma140902.uptodatemod.blocks.BlockMagma;
import yuma140902.uptodatemod.blocks.BlockNetherWart;
import yuma140902.uptodatemod.blocks.BlockObserver;
import yuma140902.uptodatemod.blocks.BlockPrismarine;
import yuma140902.uptodatemod.blocks.BlockPrismarineBricks;
import yuma140902.uptodatemod.blocks.BlockPurpur;
import yuma140902.uptodatemod.blocks.BlockPurpurPillar;
import yuma140902.uptodatemod.blocks.BlockRedNetherBricks;
import yuma140902.uptodatemod.blocks.BlockRedSandStone;
import yuma140902.uptodatemod.blocks.BlockSeaLantern;
import yuma140902.uptodatemod.blocks.BlockSmoothStone;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.blocks.BlockStoneSlab;
import yuma140902.uptodatemod.blocks.BlockSweetBerryBush;
import yuma140902.uptodatemod.blocks.generics.BlockGenericButton;
import yuma140902.uptodatemod.blocks.generics.BlockGenericFence;
import yuma140902.uptodatemod.blocks.generics.BlockGenericFenceGate;
import yuma140902.uptodatemod.blocks.generics.BlockGenericPressurePlate;
import yuma140902.uptodatemod.blocks.generics.BlockGenericSlab;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStairs;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStrippedLog;
import yuma140902.uptodatemod.blocks.generics.BlockGenericTrapDoor;
import yuma140902.uptodatemod.blocks.generics.BlockGenericWall;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public final class MyBlocks {
	private MyBlocks() {}
	
	private static boolean isEnabled(EnumDisableableFeatures feature) {
		return DisabledFeaturesRegistry.INSTANCE.isEnabled(feature);
	}
	
	private static void add(Block block) {
		if(block != null) list.add(block);
	}
	
	private static HashSet<Block> list = new HashSet<Block>();
	
	public static Iterator<Block> iterator(){
		return list.iterator();
	}
	
	public static void register() {
		Iterator<Block> iterator = iterator();
		while (iterator.hasNext()) {
			Block block = iterator.next();
			if(block instanceof IRegisterable) {
				((IRegisterable) block).register();
			}
		}
	}
	
	public static final yuma140902.uptodatemod.blocks.BlockStone stone;
	public static final BlockSmoothStone smoothStone;
	
	public static final BlockGenericStrippedLog strippedLogAcacia;
	public static final BlockGenericStrippedLog strippedLogBirch;
	public static final BlockGenericStrippedLog strippedLogDarkOak;
	public static final BlockGenericStrippedLog strippedLogJungle;
	public static final BlockGenericStrippedLog strippedLogOak;
	public static final BlockGenericStrippedLog strippedLogSpruce;
	
	public static final BlockDoorAcacia doorAcacia;
	public static final BlockDoorBirch doorBirch;
	public static final BlockDoorDarkOak doorDarkOak;
	public static final BlockDoorJungle doorJungle;
	public static final BlockDoorSpruce doorSpruce;
	
	public static final BlockGenericFence fenceAcacia;
	public static final BlockGenericFence fenceBirch;
	public static final BlockGenericFence fenceDarkOak;
	public static final BlockGenericFence fenceJungle;
	public static final BlockGenericFence fenceSpruce;
	
	public static final BlockGenericFenceGate fenceGateAcacia;
	public static final BlockGenericFenceGate fenceGateBirch;
	public static final BlockGenericFenceGate fenceGateDarkOak;
	public static final BlockGenericFenceGate fenceGateJungle;
	public static final BlockGenericFenceGate fenceGateSpruce;
	
	public static final BlockCoarseDirt coarseDirt;
	
	public static final BlockPrismarine prismarineBlock;
	public static final BlockPrismarineBricks prismarineBricks;
	public static final BlockDarkPrismarine prismarineDark;
	public static final BlockSeaLantern seaLantern;
	
	public static final BlockObserver observer;
	
	public static final BlockGenericTrapDoor trapDoorAcacia;
	public static final BlockGenericTrapDoor trapDoorBirch;
	public static final BlockGenericTrapDoor trapDoorDarkOak;
	public static final BlockGenericTrapDoor trapDoorJungle;
	public static final BlockGenericTrapDoor trapDoorSpruce;
	public static final BlockIronTrapDoor trapDoorIron;
	
	public static final BlockGenericButton buttonAcacia;
	public static final BlockGenericButton buttonBirch;
	public static final BlockGenericButton buttonDarkOak;
	public static final BlockGenericButton buttonJungle;
	public static final BlockGenericButton buttonSpruce;
	
	public static final BlockGenericPressurePlate pressurePlateAcacia;
	public static final BlockGenericPressurePlate pressurePlateBirch;
	public static final BlockGenericPressurePlate pressurePlateDarkOak;
	public static final BlockGenericPressurePlate pressurePlateJungle;
	public static final BlockGenericPressurePlate pressurePlateSpruce;
	
	public static final BlockRedNetherBricks redNetherBricks;
	public static final BlockNetherWart netherWartBlock;
	public static final BlockMagma magmaBlock;
	
	public static final BlockEndStoneBricks endStoneBricks;
	public static final BlockPurpur purpurBlock;
	public static final BlockPurpurPillar purpurPillar;
	
	public static final BlockBone boneBlock;
	
	public static final BlockRedSandStone redSandStone;
	
	public static final BlockConcrete concreteBlock;
	public static final BlockConcretePowder concretePowder;
	
	public static final BlockGlazedTerracotta glazedTerracottaBlack;
	public static final BlockGlazedTerracotta glazedTerracottaBlue;
	public static final BlockGlazedTerracotta glazedTerracottaBrown;
	public static final BlockGlazedTerracotta glazedTerracottaCyan;
	public static final BlockGlazedTerracotta glazedTerracottaGray;
	public static final BlockGlazedTerracotta glazedTerracottaGreen;
	public static final BlockGlazedTerracotta glazedTerracottaLightBlue;
	public static final BlockGlazedTerracotta glazedTerracottaLightGray;
	public static final BlockGlazedTerracotta glazedTerracottaLime;
	public static final BlockGlazedTerracotta glazedTerracottaMagenta;
	public static final BlockGlazedTerracotta glazedTerracottaOrange;
	public static final BlockGlazedTerracotta glazedTerracottaPink;
	public static final BlockGlazedTerracotta glazedTerracottaPurple;
	public static final BlockGlazedTerracotta glazedTerracottaRed;
	public static final BlockGlazedTerracotta glazedTerracottaWhite;
	public static final BlockGlazedTerracotta glazedTerracottaYellow;
	
	public static final BlockGrassPath grassPath;
	
	public static final BlockGenericStairs stairsStone;
	public static final BlockGenericStairs stairsGranite;
	public static final BlockGenericStairs stairsDiorite;
	public static final BlockGenericStairs stairsAndesite;
	public static final BlockGenericStairs stairsPolishedGranite;
	public static final BlockGenericStairs stairsPolishedDiorite;
	public static final BlockGenericStairs stairsPolishedAndesite;
	public static final BlockGenericStairs stairsRedNetherBricks;
	public static final BlockGenericStairs stairsPrismarine;
	public static final BlockGenericStairs stairsPrismarineBricks;
	public static final BlockGenericStairs stairsDarkPrismarine;
	public static final BlockGenericStairs stairsPurpur;
	public static final BlockGenericStairs stairsRedSandstone;
	public static final BlockGenericStairs stairsEndStoneBricks;
	public static final BlockGenericStairs stairsMossyStoneBricks;
	public static final BlockGenericStairs stairsMossyCobbleStone;
	
	public static final BlockGenericSlab slabStone;
	public static final BlockGenericSlab slabGranite;
	public static final BlockGenericSlab slabDiorite;
	public static final BlockGenericSlab slabAndesite;
	public static final BlockGenericSlab slabPolishedGranite;
	public static final BlockGenericSlab slabPolishedDiorite;
	public static final BlockGenericSlab slabPolishedAndesite;
	public static final BlockGenericSlab slabRedNetherBricks;
	public static final BlockGenericSlab slabEndStoneBricks;
	public static final BlockGenericSlab slabMossyStoneBricks;
	public static final BlockGenericSlab slabMossyCobbleStone;
	public static final BlockGenericSlab slabRedSandstone;
	public static final BlockGenericSlab slabPurpur;
	public static final BlockGenericSlab slabPrismarine;
	public static final BlockGenericSlab slabPrismarineBricks;
	public static final BlockGenericSlab slabDarkPrismarine;
	
	public static final BlockGenericWall wallBricks;
	public static final BlockGenericWall wallStoneBricks;
	public static final BlockGenericWall wallMossyStoneBricks;
	public static final BlockGenericWall wallSandstone;
	public static final BlockGenericWall wallRedSandstone;
	public static final BlockGenericWall wallNetherBricks;
	public static final BlockGenericWall wallRedNetherBricks;
	public static final BlockGenericWall wallEndStoneBricks;
	public static final BlockGenericWall wallAndesite;
	public static final BlockGenericWall wallDiorite;
	public static final BlockGenericWall wallGranite;
	public static final BlockGenericWall wallPrismarine;
	public static final BlockGenericWall wallPrismarineBrick;
	public static final BlockGenericWall wallDarkPrismarine;
	
	public static final BlockBarrel barrel;

	public static final BlockSweetBerryBush sweetBerryBush;
	
	
	static {
		ModUpToDateMod.LOGGER.info("Blocks init");
		
		add(stone = isEnabled(stones) ? new yuma140902.uptodatemod.blocks.BlockStone() : null);
		add(smoothStone = isEnabled(EnumDisableableFeatures.smoothStone) ? new BlockSmoothStone() : null);
		
		if(isEnabled(strippedLogs)) {
			add(strippedLogAcacia =  new BlockGenericStrippedLog("stripped_log_acacia",    "stripped_acacia_log",   Stat.PLANK_META_ACACIA));
			add(strippedLogBirch =   new BlockGenericStrippedLog("stripped_log_birch",      "stripped_birch_log",    Stat.PLANK_META_BIRCH));
			add(strippedLogDarkOak = new BlockGenericStrippedLog("stripped_log_dark_oak", "stripped_dark_oak_log", Stat.PLANK_META_DARKOAK));
			add(strippedLogJungle =  new BlockGenericStrippedLog("stripped_log_jungle",    "stripped_jungle_log",   Stat.PLANK_META_JUNGLE));
			add(strippedLogOak =     new BlockGenericStrippedLog("stripped_log_oak",          "stripped_oak_log",      Stat.PLANK_META_OAK));
			add(strippedLogSpruce =  new BlockGenericStrippedLog("stripped_log_spruce",    "stripped_spruce_log",   Stat.PLANK_META_SPRUCE));
		}
		else {
			strippedLogAcacia = null;
			strippedLogBirch = null;
			strippedLogDarkOak = null;
			strippedLogJungle = null;
			strippedLogOak = null;
			strippedLogSpruce = null;
		}
		
		if(isEnabled(doors)) {
			add(doorAcacia = new BlockDoorAcacia());
			add(doorBirch = new BlockDoorBirch());
			add(doorDarkOak = new BlockDoorDarkOak());
			add(doorJungle = new BlockDoorJungle());
			add(doorSpruce = new BlockDoorSpruce());
		}
		else {
			doorAcacia = null;
			doorBirch = null;
			doorDarkOak = null;
			doorJungle = null;
			doorSpruce = null;
		}
		
		if(isEnabled(fences)) {
			add(fenceAcacia = new BlockGenericFence("planks_acacia", "fence_acacia"));
			add(fenceBirch = new BlockGenericFence("planks_birch", "fence_birch"));
			add(fenceDarkOak = new BlockGenericFence("planks_big_oak", "fence_dark_oak"));
			add(fenceJungle = new BlockGenericFence("planks_jungle", "fence_jungle"));
			add(fenceSpruce = new BlockGenericFence("planks_spruce", "fence_spruce"));
		}
		else {
			fenceAcacia = null;
			fenceBirch = null;
			fenceDarkOak = null;
			fenceJungle = null;
			fenceSpruce = null;
		}
		
		if(isEnabled(fenceGates)) {
			add(fenceGateAcacia = new BlockGenericFenceGate(Stat.PLANK_META_ACACIA, "fence_gate_acacia"));
			add(fenceGateBirch = new BlockGenericFenceGate(Stat.PLANK_META_BIRCH, "fence_gate_birch"));
			add(fenceGateDarkOak = new BlockGenericFenceGate(Stat.PLANK_META_DARKOAK, "fence_gate_dark_oak"));
			add(fenceGateJungle = new BlockGenericFenceGate(Stat.PLANK_META_JUNGLE, "fence_gate_jungle"));
			add(fenceGateSpruce = new BlockGenericFenceGate(Stat.PLANK_META_SPRUCE, "fence_gate_spruce"));
		}
		else {
			fenceGateAcacia = null;
			fenceGateBirch = null;
			fenceGateDarkOak = null;
			fenceGateJungle = null;
			fenceGateSpruce = null;
		}
		
		add(coarseDirt = isEnabled(EnumDisableableFeatures.coarseDirt) ? new BlockCoarseDirt() : null);
		
		if(isEnabled(prismarineStuffs)) {
			add(prismarineBlock = new BlockPrismarine());
			add(prismarineBricks = new BlockPrismarineBricks());
			add(prismarineDark = new BlockDarkPrismarine());
			add(seaLantern = new BlockSeaLantern());
		}
		else {
			prismarineBlock = null;
			prismarineBricks = null;
			prismarineDark = null;
			seaLantern = null;
		}
		
		add(observer = ModConfigCore.enable_observer ? new BlockObserver() : null);
		
		if(isEnabled(woodenTrapdoors)) {
			add(trapDoorAcacia = new BlockGenericTrapDoor("trap_door_acacia", Stat.PLANK_META_ACACIA));
			add(trapDoorBirch = new BlockGenericTrapDoor("trap_door_birch", Stat.PLANK_META_BIRCH));
			add(trapDoorDarkOak = new BlockGenericTrapDoor("trap_door_dark_oak", Stat.PLANK_META_DARKOAK));
			add(trapDoorJungle = new BlockGenericTrapDoor("trap_door_jungle", Stat.PLANK_META_JUNGLE));
			add(trapDoorSpruce = new BlockGenericTrapDoor("trap_door_spruce", Stat.PLANK_META_SPRUCE));
		}
		else {
			trapDoorAcacia = null;
			trapDoorBirch = null;
			trapDoorDarkOak = null;
			trapDoorJungle = null;
			trapDoorSpruce = null;
		}
		add(trapDoorIron = isEnabled(ironTrapdoor) ? new BlockIronTrapDoor() : null);
		
		if(isEnabled(buttons)) {
			add(buttonAcacia = new BlockGenericButton(Stat.PLANK_META_ACACIA, "button_acacia"));
			add(buttonBirch = new BlockGenericButton(Stat.PLANK_META_BIRCH, "button_birch"));
			add(buttonDarkOak = new BlockGenericButton(Stat.PLANK_META_DARKOAK, "button_dark_oak"));
			add(buttonJungle = new BlockGenericButton(Stat.PLANK_META_JUNGLE, "button_jungle"));
			add(buttonSpruce = new BlockGenericButton(Stat.PLANK_META_SPRUCE, "button_spruce"));
		}
		else {
			buttonAcacia = null;
			buttonBirch = null;
			buttonDarkOak = null;
			buttonJungle = null;
			buttonSpruce = null;
		}
		
		if(isEnabled(pressurePlates)) {
			add(pressurePlateAcacia = new BlockGenericPressurePlate(Stat.PLANK_META_ACACIA, "pressure_plate_acacia"));
			add(pressurePlateBirch = new BlockGenericPressurePlate(Stat.PLANK_META_BIRCH, "pressure_plate_birch"));
			add(pressurePlateDarkOak = new BlockGenericPressurePlate(Stat.PLANK_META_DARKOAK, "pressure_plate_dark_oak"));
			add(pressurePlateJungle = new BlockGenericPressurePlate(Stat.PLANK_META_JUNGLE, "pressure_plate_jungle"));
			add(pressurePlateSpruce = new BlockGenericPressurePlate(Stat.PLANK_META_SPRUCE, "pressure_plate_spruce"));
		}
		else {
			pressurePlateAcacia = null;
			pressurePlateBirch = null;
			pressurePlateDarkOak = null;
			pressurePlateJungle = null;
			pressurePlateSpruce = null;
		}
		
		add(redNetherBricks = isEnabled(EnumDisableableFeatures.redNetherBricks) ? new BlockRedNetherBricks() : null);
		add(netherWartBlock = isEnabled(EnumDisableableFeatures.netherWartBlock) ? new BlockNetherWart() : null);
		add(magmaBlock = isEnabled(EnumDisableableFeatures.magmaBlock) ? new BlockMagma() : null);
		
		add(endStoneBricks = isEnabled(EnumDisableableFeatures.endstoneBricks) ? new BlockEndStoneBricks() : null);
		add(purpurBlock = isEnabled(purpurStuffs) ? new BlockPurpur() : null);
		add(purpurPillar = isEnabled(purpurStuffs) ? new BlockPurpurPillar() : null);
		
		add(boneBlock = isEnabled(boneBlockAndFossile) ? new BlockBone() : null);
		
		add(redSandStone = isEnabled(EnumDisableableFeatures.redSandstone) ? new BlockRedSandStone() : null);
		
		if(isEnabled(concreteAndConcretePowder)) {
			add(concreteBlock = new BlockConcrete());
			add(concretePowder = new BlockConcretePowder());
		}
		else {
			concreteBlock = null;
			concretePowder = null;
		}
		
		if(isEnabled(glazedTerracotta)) {
			add(glazedTerracottaBlack = new BlockGlazedTerracotta(ColorUtil.META_BLACK));
			add(glazedTerracottaBlue = new BlockGlazedTerracotta(ColorUtil.META_BLUE));
			add(glazedTerracottaBrown = new BlockGlazedTerracotta(ColorUtil.META_BROWN));
			add(glazedTerracottaCyan = new BlockGlazedTerracotta(ColorUtil.META_CYAN));
			add(glazedTerracottaGray = new BlockGlazedTerracotta(ColorUtil.META_GRAY));
			add(glazedTerracottaGreen = new BlockGlazedTerracotta(ColorUtil.META_GREEN));
			add(glazedTerracottaLightBlue = new BlockGlazedTerracotta(ColorUtil.META_LIGHT_BLUE));
			add(glazedTerracottaLightGray = new BlockGlazedTerracotta(ColorUtil.META_LIGHT_GRAY));
			add(glazedTerracottaLime = new BlockGlazedTerracotta(ColorUtil.META_LIME));
			add(glazedTerracottaMagenta = new BlockGlazedTerracotta(ColorUtil.META_MAGENTA));
			add(glazedTerracottaOrange = new BlockGlazedTerracotta(ColorUtil.META_ORANGE));
			add(glazedTerracottaPink = new BlockGlazedTerracotta(ColorUtil.META_PINK));
			add(glazedTerracottaPurple = new BlockGlazedTerracotta(ColorUtil.META_PURPLE));
			add(glazedTerracottaRed = new BlockGlazedTerracotta(ColorUtil.META_RED));
			add(glazedTerracottaWhite = new BlockGlazedTerracotta(ColorUtil.META_WHITE));
			add(glazedTerracottaYellow = new BlockGlazedTerracotta(ColorUtil.META_YELLOW));
		}
		else {
			glazedTerracottaBlack = null;
			glazedTerracottaBlue = null;
			glazedTerracottaBrown = null;
			glazedTerracottaCyan = null;
			glazedTerracottaGray = null;
			glazedTerracottaGreen = null;
			glazedTerracottaLightBlue = null;
			glazedTerracottaLightGray = null;
			glazedTerracottaLime = null;
			glazedTerracottaMagenta = null;
			glazedTerracottaOrange = null;
			glazedTerracottaPink = null;
			glazedTerracottaPurple = null;
			glazedTerracottaRed = null;
			glazedTerracottaWhite = null;
			glazedTerracottaYellow = null;
		}
		
		add(grassPath = isEnabled(EnumDisableableFeatures.grassPath) ? new BlockGrassPath() : null);
		
		if(isEnabled(allKindsOfStairs)) {
			add(stairsStone = BlockGenericStairs.constructIfNotNull(Blocks.stone, 0, "stairs_stone"));
			add(stairsGranite = BlockGenericStairs.constructIfNotNull(MyBlocks.stone, BlockStone.META_GRANITE, "stairs_granite"));
			add(stairsDiorite = BlockGenericStairs.constructIfNotNull(MyBlocks.stone, BlockStone.META_DIORITE, "stairs_diorite"));
			add(stairsAndesite = BlockGenericStairs.constructIfNotNull(MyBlocks.stone, BlockStone.META_ANDESITE, "stairs_andesite"));
			add(stairsPolishedGranite = BlockGenericStairs.constructIfNotNull(MyBlocks.stone, BlockStone.META_POLISHED_GRANITE, "stairs_polished_granite"));
			add(stairsPolishedDiorite = BlockGenericStairs.constructIfNotNull(MyBlocks.stone, BlockStone.META_POLISHED_DIORITE, "stairs_polished_diorite"));
			add(stairsPolishedAndesite = BlockGenericStairs.constructIfNotNull(MyBlocks.stone, BlockStone.META_POLISHED_ANDESITE, "stairs_polished_andesite"));
			add(stairsRedNetherBricks = BlockGenericStairs.constructIfNotNull(MyBlocks.redNetherBricks, 0, "stairs_red_nether_bricks"));
			add(stairsPrismarine = BlockGenericStairs.constructIfNotNull(MyBlocks.prismarineBlock, 0, "stairs_prismarine"));
			add(stairsPrismarineBricks = BlockGenericStairs.constructIfNotNull(MyBlocks.prismarineBricks, 0, "stairs_prismarine_bricks"));
			add(stairsDarkPrismarine = BlockGenericStairs.constructIfNotNull(MyBlocks.prismarineDark, 0, "stairs_dark_prismarine"));
			add(stairsPurpur = BlockGenericStairs.constructIfNotNull(MyBlocks.purpurBlock, 0, "stairs_purpur"));
			add(stairsRedSandstone = BlockGenericStairs.constructIfNotNull(MyBlocks.redSandStone, 0, "stairs_red_sandstone"));
			add(stairsEndStoneBricks = BlockGenericStairs.constructIfNotNull(MyBlocks.endStoneBricks, 0, "stairs_end_stone_bricks"));
			add(stairsMossyStoneBricks = BlockGenericStairs.constructIfNotNull(Blocks.stonebrick, 1, "stairs_mossy_stone_bricks"));
			add(stairsMossyCobbleStone = BlockGenericStairs.constructIfNotNull(Blocks.mossy_cobblestone, 0, "stairs_mossy_cobblestone"));
		}
		else {
			stairsStone = null;
			stairsGranite = null;
			stairsDiorite = null;
			stairsAndesite = null;
			stairsPolishedGranite = null;
			stairsPolishedDiorite = null;
			stairsPolishedAndesite = null;
			stairsRedNetherBricks = null;
			stairsPrismarine = null;
			stairsPrismarineBricks = null;
			stairsDarkPrismarine = null;
			stairsPurpur = null;
			stairsRedSandstone = null;
			stairsEndStoneBricks = null;
			stairsMossyStoneBricks = null;
			stairsMossyCobbleStone = null;
		}
		
		if(isEnabled(allKindsOfSlabs)) {
			add(slabStone = new BlockStoneSlab());
			add(slabGranite = BlockGenericSlab.constructIfNotNull(MyBlocks.stone, BlockStone.META_GRANITE, "slab_granite"));
			add(slabDiorite = BlockGenericSlab.constructIfNotNull(MyBlocks.stone, BlockStone.META_DIORITE, "slab_diorite"));
			add(slabAndesite = BlockGenericSlab.constructIfNotNull(MyBlocks.stone, BlockStone.META_ANDESITE, "slab_andesite"));
			add(slabPolishedGranite = BlockGenericSlab.constructIfNotNull(MyBlocks.stone, BlockStone.META_POLISHED_GRANITE, "slab_polished_granite", StringUtil.getDomainedModTextureName("polished_granite_slab_side")));
			add(slabPolishedDiorite = BlockGenericSlab.constructIfNotNull(MyBlocks.stone, BlockStone.META_POLISHED_DIORITE, "slab_polished_diorite", StringUtil.getDomainedModTextureName("polished_diorite_slab_side")));
			add(slabPolishedAndesite = BlockGenericSlab.constructIfNotNull(MyBlocks.stone, BlockStone.META_POLISHED_ANDESITE, "slab_polished_andesite", StringUtil.getDomainedModTextureName("polished_andesite_slab_side")));
			add(slabRedNetherBricks = BlockGenericSlab.constructIfNotNull(MyBlocks.redNetherBricks, 0, "slab_red_nether_bricks"));
			add(slabEndStoneBricks = BlockGenericSlab.constructIfNotNull(MyBlocks.endStoneBricks, 0, "slab_end_stone_bricks"));
			add(slabMossyStoneBricks = BlockGenericSlab.constructIfNotNull(Blocks.stonebrick, 1, "slab_mossy_stone_bricks"));
			add(slabMossyCobbleStone = BlockGenericSlab.constructIfNotNull(Blocks.mossy_cobblestone, 0, "slab_mossy_cobblestone"));
			add(slabRedSandstone = BlockGenericSlab.constructIfNotNull(MyBlocks.redSandStone, 0, "slab_red_sandstone"));
			add(slabPurpur = BlockGenericSlab.constructIfNotNull(MyBlocks.purpurBlock, 0, "slab_purpur"));
			add(slabPrismarine = BlockGenericSlab.constructIfNotNull(MyBlocks.prismarineBlock, 0, "slab_prismarine"));
			add(slabPrismarineBricks = BlockGenericSlab.constructIfNotNull(MyBlocks.prismarineBricks, 0, "slab_prismarine_bricks"));
			add(slabDarkPrismarine = BlockGenericSlab.constructIfNotNull(MyBlocks.prismarineDark, 0, "slab_dark_prismairne"));
		}
		else {
			slabStone = null;
			slabGranite = null;
			slabDiorite = null;
			slabAndesite = null;
			slabPolishedGranite = null;
			slabPolishedDiorite = null;
			slabPolishedAndesite = null;
			slabRedNetherBricks = null;
			slabEndStoneBricks = null;
			slabMossyStoneBricks = null;
			slabMossyCobbleStone = null;
			slabRedSandstone = null;
			slabPurpur = null;
			slabPrismarine = null;
			slabPrismarineBricks = null;
			slabDarkPrismarine = null;
		}
		
		if(isEnabled(allKindsOfWalls)) {
			add(wallBricks = BlockGenericWall.constructIfNotNull(Blocks.brick_block, 0, "wall_bricks"));
			add(wallStoneBricks = BlockGenericWall.constructIfNotNull(Blocks.stonebrick, 0, "wall_stone_bricks"));
			add(wallMossyStoneBricks = BlockGenericWall.constructIfNotNull(Blocks.stonebrick, 1, "wall_mossy_stone_bricks"));
			add(wallSandstone = BlockGenericWall.constructIfNotNull(Blocks.sandstone, 0, "wall_sandstone"));
			add(wallRedSandstone = BlockGenericWall.constructIfNotNull(MyBlocks.redSandStone, 0, "wall_red_sandstone"));
			add(wallNetherBricks = BlockGenericWall.constructIfNotNull(Blocks.nether_brick, 0, "wall_nether_bricks"));
			add(wallRedNetherBricks = BlockGenericWall.constructIfNotNull(MyBlocks.redNetherBricks, 0, "wall_red_nether_bricks"));
			add(wallEndStoneBricks = BlockGenericWall.constructIfNotNull(MyBlocks.endStoneBricks, 0, "wall_end_stone_bricks"));
			add(wallAndesite = BlockGenericWall.constructIfNotNull(MyBlocks.stone, BlockStone.META_ANDESITE, "wall_andesite"));
			add(wallDiorite = BlockGenericWall.constructIfNotNull(MyBlocks.stone, BlockStone.META_DIORITE, "wall_diorite"));
			add(wallGranite = BlockGenericWall.constructIfNotNull(MyBlocks.stone, BlockStone.META_GRANITE, "wall_granite"));
			add(wallPrismarine = BlockGenericWall.constructIfNotNull(prismarineBlock, 0, "wall_prismarine"));
			add(wallPrismarineBrick = BlockGenericWall.constructIfNotNull(prismarineBricks, 0, "wall_prismarine_brick"));
			add(wallDarkPrismarine = BlockGenericWall.constructIfNotNull(prismarineDark, 0, "wall_dark_prismarine"));
		}
		else {
			wallBricks = null;
			wallStoneBricks = null;
			wallMossyStoneBricks = null;
			wallSandstone = null;
			wallRedSandstone = null;
			wallNetherBricks = null;
			wallRedNetherBricks = null;
			wallEndStoneBricks = null;
			wallAndesite = null;
			wallDiorite = null;
			wallGranite = null;
			wallPrismarine = null;
			wallPrismarineBrick = null;
			wallDarkPrismarine = null;
		}
		
		add(barrel = isEnabled(EnumDisableableFeatures.barrel) ? new BlockBarrel() : null);
		
		add(sweetBerryBush = isEnabled(sweetBerry) ? new BlockSweetBerryBush() : null);
	}
	
}
