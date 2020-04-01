package yuma140902.uptodatemod;

import static yuma140902.uptodatemod.registry.EnumDisableableFeatures.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.blocks.BlockBarrel;
import yuma140902.uptodatemod.blocks.BlockBone;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.blocks.BlockConcrete;
import yuma140902.uptodatemod.blocks.BlockConcretePowder;
import yuma140902.uptodatemod.blocks.BlockDarkPrismarine;
import yuma140902.uptodatemod.blocks.BlockEndStoneBricks;
import yuma140902.uptodatemod.blocks.BlockGlazedTerracotta;
import yuma140902.uptodatemod.blocks.BlockGrassPath;
import yuma140902.uptodatemod.blocks.BlockIronTrapDoor;
import yuma140902.uptodatemod.blocks.BlockMagma;
import yuma140902.uptodatemod.blocks.BlockNetherWart;
import yuma140902.uptodatemod.blocks.BlockNewFlower;
import yuma140902.uptodatemod.blocks.BlockPrismarine;
import yuma140902.uptodatemod.blocks.BlockPrismarineBricks;
import yuma140902.uptodatemod.blocks.BlockPurpur;
import yuma140902.uptodatemod.blocks.BlockPurpurPillar;
import yuma140902.uptodatemod.blocks.BlockRedNetherBricks;
import yuma140902.uptodatemod.blocks.BlockRedSandStone;
import yuma140902.uptodatemod.blocks.BlockSeaLantern;
import yuma140902.uptodatemod.blocks.BlockSmoothQuartz;
import yuma140902.uptodatemod.blocks.BlockSmoothSandstone;
import yuma140902.uptodatemod.blocks.BlockSmoothStone;
import yuma140902.uptodatemod.blocks.BlockSponge;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.blocks.BlockStoneSlab;
import yuma140902.uptodatemod.blocks.BlockSweetBerryBush;
import yuma140902.uptodatemod.blocks.BlockUnlimitedPot;
import yuma140902.uptodatemod.blocks.BlockWitherRose;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStrippedLog;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.blocks.BlockGenericButton;
import yuma140902.yumalib.api.blocks.BlockGenericDoor;
import yuma140902.yumalib.api.blocks.BlockGenericFence;
import yuma140902.yumalib.api.blocks.BlockGenericFenceGate;
import yuma140902.yumalib.api.blocks.BlockGenericPressurePlate;
import yuma140902.yumalib.api.blocks.BlockGenericSlab;
import yuma140902.yumalib.api.blocks.BlockGenericStairs;
import yuma140902.yumalib.api.blocks.BlockGenericTrapDoor;
import yuma140902.yumalib.api.blocks.BlockGenericWall;
import yuma140902.yumalib.api.blocks.SlabBuilder;
import yuma140902.yumalib.api.blocks.StairsBuilder;

public final class MyBlocks {
	private MyBlocks() {}
	
	private static boolean isEnabled(EnumDisableableFeatures feature) {
		return DisabledFeaturesRegistry.INSTANCE.isEnabled(feature);
	}
	
	private static void add(Block block) {
		if(block != null) list.add(block);
	}
	
	private static List<Block> list = new ArrayList<Block>();
	
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
	
	public static final BlockGenericDoor doorAcacia;
	public static final BlockGenericDoor doorBirch;
	public static final BlockGenericDoor doorDarkOak;
	public static final BlockGenericDoor doorJungle;
	public static final BlockGenericDoor doorSpruce;
	
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
	public static final BlockSponge sponge;
	
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
	public static final BlockSmoothQuartz smoothQuartz;
	
	public static final BlockEndStoneBricks endStoneBricks;
	public static final BlockPurpur purpurBlock;
	public static final BlockPurpurPillar purpurPillar;
	
	public static final BlockBone boneBlock;
	
	public static final BlockSmoothSandstone smoothSandstone;
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
	public static final BlockGenericStairs stairsSmoothQuartz;
	public static final BlockGenericStairs stairsPrismarine;
	public static final BlockGenericStairs stairsPrismarineBricks;
	public static final BlockGenericStairs stairsDarkPrismarine;
	public static final BlockGenericStairs stairsPurpur;
	public static final BlockGenericStairs stairsSmoothSandstone;
	public static final BlockGenericStairs stairsRedSandstone;
	public static final BlockGenericStairs stairsSmoothRedSandstone;
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
	public static final BlockGenericSlab slabSmoothQuartz;
	public static final BlockGenericSlab slabEndStoneBricks;
	public static final BlockGenericSlab slabMossyStoneBricks;
	public static final BlockGenericSlab slabMossyCobbleStone;
	public static final BlockGenericSlab slabCutSandstone;
	public static final BlockGenericSlab slabSmoothSandstone;
	public static final BlockGenericSlab slabRedSandstone;
	public static final BlockGenericSlab slabCutRedSandstone;
	public static final BlockGenericSlab slabSmoothRedSandstone;
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
	
	public static final BlockWitherRose witherRose;
	public static final BlockNewFlower flower;
	public static final BlockUnlimitedPot unlimitedPot;
	
	
	static {
		ModUpToDateMod.LOGGER.info("Blocks init");
		
		add(stone = isEnabled(stones) ? new yuma140902.uptodatemod.blocks.BlockStone() : null);
		add(smoothStone = isEnabled(EnumDisableableFeatures.smoothStone) ? new BlockSmoothStone() : null);
		
		if(isEnabled(strippedLogs)) {
			add(strippedLogAcacia = 	new BlockGenericStrippedLog("stripped_log_acacia", 		"stripped_acacia_log", 		McConst.Meta.PLANK_ACACIA));
			add(strippedLogBirch = 		new BlockGenericStrippedLog("stripped_log_birch", 		"stripped_birch_log", 		McConst.Meta.PLANK_BIRCH));
			add(strippedLogDarkOak = 	new BlockGenericStrippedLog("stripped_log_dark_oak", 	"stripped_dark_oak_log", 	McConst.Meta.PLANK_DARKOAK));
			add(strippedLogJungle = 	new BlockGenericStrippedLog("stripped_log_jungle", 		"stripped_jungle_log", 		McConst.Meta.PLANK_JUNGLE));
			add(strippedLogOak = 			new BlockGenericStrippedLog("stripped_log_oak", 			"stripped_oak_log", 			McConst.Meta.PLANK_OAK));
			add(strippedLogSpruce = 	new BlockGenericStrippedLog("stripped_log_spruce", 	"stripped_spruce_log", 			McConst.Meta.PLANK_SPRUCE));
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
			add(doorAcacia = 	new BlockGenericDoor("door_acacia", 		"acacia_door", 		MyItems.itemDoorAcacia));
			add(doorBirch = 		new BlockGenericDoor("door_birch", 			"birch_door", 		MyItems.itemDoorBirch));
			add(doorDarkOak = 	new BlockGenericDoor("door_dark_oak", 	"dark_oak_door", 	MyItems.itemDoorDarkOak));
			add(doorJungle = 	new BlockGenericDoor("door_jungle", 		"jungle_door", 		MyItems.itemDoorJungle));
			add(doorSpruce = 	new BlockGenericDoor("door_spruce", 		"spruce_door", 		MyItems.itemDoorSpruce));
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
			add(fenceGateAcacia = 	new BlockGenericFenceGate(McConst.Meta.PLANK_ACACIA, 	"fence_gate_acacia"));
			add(fenceGateBirch = 		new BlockGenericFenceGate(McConst.Meta.PLANK_BIRCH, 		"fence_gate_birch"));
			add(fenceGateDarkOak = 	new BlockGenericFenceGate(McConst.Meta.PLANK_DARKOAK, 	"fence_gate_dark_oak"));
			add(fenceGateJungle = 	new BlockGenericFenceGate(McConst.Meta.PLANK_JUNGLE, 	"fence_gate_jungle"));
			add(fenceGateSpruce = 	new BlockGenericFenceGate(McConst.Meta.PLANK_SPRUCE, 	"fence_gate_spruce"));
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
		add(sponge = isEnabled(EnumDisableableFeatures.sponge) ? new BlockSponge() : null);
		
		if(isEnabled(woodenTrapdoors)) {
			add(trapDoorAcacia = 		new BlockGenericTrapDoor("trap_door_acacia", 		McConst.Meta.PLANK_ACACIA));
			add(trapDoorBirch = 		new BlockGenericTrapDoor("trap_door_birch", 		McConst.Meta.PLANK_BIRCH));
			add(trapDoorDarkOak = 	new BlockGenericTrapDoor("trap_door_dark_oak", 	McConst.Meta.PLANK_DARKOAK));
			add(trapDoorJungle = 		new BlockGenericTrapDoor("trap_door_jungle", 		McConst.Meta.PLANK_JUNGLE));
			add(trapDoorSpruce = 		new BlockGenericTrapDoor("trap_door_spruce", 		McConst.Meta.PLANK_SPRUCE));
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
			add(buttonAcacia = 		new BlockGenericButton(McConst.Meta.PLANK_ACACIA, 		"button_acacia"));
			add(buttonBirch = 		new BlockGenericButton(McConst.Meta.PLANK_BIRCH, 		"button_birch"));
			add(buttonDarkOak = 	new BlockGenericButton(McConst.Meta.PLANK_DARKOAK, 	"button_dark_oak"));
			add(buttonJungle = 		new BlockGenericButton(McConst.Meta.PLANK_JUNGLE, 		"button_jungle"));
			add(buttonSpruce = 		new BlockGenericButton(McConst.Meta.PLANK_SPRUCE, 		"button_spruce"));
		}
		else {
			buttonAcacia = null;
			buttonBirch = null;
			buttonDarkOak = null;
			buttonJungle = null;
			buttonSpruce = null;
		}
		
		if(isEnabled(pressurePlates)) {
			add(pressurePlateAcacia = 	new BlockGenericPressurePlate(McConst.Meta.PLANK_ACACIA, 	"pressure_plate_acacia"));
			add(pressurePlateBirch = 		new BlockGenericPressurePlate(McConst.Meta.PLANK_BIRCH, 		"pressure_plate_birch"));
			add(pressurePlateDarkOak = 	new BlockGenericPressurePlate(McConst.Meta.PLANK_DARKOAK, 	"pressure_plate_dark_oak"));
			add(pressurePlateJungle = 	new BlockGenericPressurePlate(McConst.Meta.PLANK_JUNGLE, 	"pressure_plate_jungle"));
			add(pressurePlateSpruce = 	new BlockGenericPressurePlate(McConst.Meta.PLANK_SPRUCE, 	"pressure_plate_spruce"));
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
		add(smoothQuartz = new BlockSmoothQuartz());
		
		add(endStoneBricks = isEnabled(EnumDisableableFeatures.endstoneBricks) ? new BlockEndStoneBricks() : null);
		add(purpurBlock = isEnabled(purpurStuffs) ? new BlockPurpur() : null);
		add(purpurPillar = isEnabled(purpurStuffs) ? new BlockPurpurPillar() : null);
		
		add(boneBlock = isEnabled(boneBlockAndFossile) ? new BlockBone() : null);
		
		add(smoothSandstone = new BlockSmoothSandstone());
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
			add(stairsStone = new StairsBuilder(Blocks.stone, "stairs_stone").build());
			add(stairsGranite = new StairsBuilder(MyBlocks.stone, "stairs_granite").meta(BlockStone.META_GRANITE).build());
			add(stairsDiorite = new StairsBuilder(MyBlocks.stone, "stairs_diorite").meta(BlockStone.META_DIORITE).build());
			add(stairsAndesite = new StairsBuilder(MyBlocks.stone, "stairs_andesite").meta(BlockStone.META_ANDESITE).build());
			add(stairsPolishedGranite = new StairsBuilder(MyBlocks.stone, "stairs_polished_granite").meta(BlockStone.META_POLISHED_GRANITE).build());
			add(stairsPolishedDiorite = new StairsBuilder(MyBlocks.stone, "stairs_polished_diorite").meta(BlockStone.META_POLISHED_DIORITE).build());
			add(stairsPolishedAndesite = new StairsBuilder(MyBlocks.stone, "stairs_polished_andesite").meta(BlockStone.META_POLISHED_ANDESITE).build());
			add(stairsRedNetherBricks = new StairsBuilder(MyBlocks.redNetherBricks, "stairs_red_nether_bricks").build());
			add(stairsSmoothQuartz = new StairsBuilder(MyBlocks.smoothQuartz, "stairs_smooth_quartz").build());
			add(stairsPrismarine = new StairsBuilder(MyBlocks.prismarineBlock, "stairs_prismarine").build());
			add(stairsPrismarineBricks = new StairsBuilder(MyBlocks.prismarineBricks, "stairs_prismarine_bricks").build());
			add(stairsDarkPrismarine = new StairsBuilder(MyBlocks.prismarineDark, "stairs_dark_prismarine").build());
			add(stairsPurpur = new StairsBuilder(MyBlocks.purpurBlock, "stairs_purpur").build());
			add(stairsSmoothSandstone = new StairsBuilder(MyBlocks.smoothSandstone, "stairs_smooth_sandstone").build());
			add(stairsRedSandstone = new StairsBuilder(MyBlocks.redSandStone, "stairs_red_sandstone").meta(BlockRedSandStone.META_NORMAL).build());
			add(stairsSmoothRedSandstone = new StairsBuilder(MyBlocks.redSandStone, "stairs_smooth_red_sandstone").meta(BlockRedSandStone.META_SMOOTH).build());
			add(stairsEndStoneBricks = new StairsBuilder(MyBlocks.endStoneBricks, "stairs_end_stone_bricks").build());
			add(stairsMossyStoneBricks = new StairsBuilder(Blocks.stonebrick, "stairs_mossy_stone_bricks").meta(1).build());
			add(stairsMossyCobbleStone = new StairsBuilder(Blocks.mossy_cobblestone, "stairs_mossy_cobblestone").build());
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
			stairsSmoothQuartz = null;
			stairsPrismarine = null;
			stairsPrismarineBricks = null;
			stairsDarkPrismarine = null;
			stairsPurpur = null;
			stairsSmoothSandstone = null;
			stairsRedSandstone = null;
			stairsSmoothRedSandstone = null;
			stairsEndStoneBricks = null;
			stairsMossyStoneBricks = null;
			stairsMossyCobbleStone = null;
		}
		
		if(isEnabled(allKindsOfSlabs)) {
			add(slabStone = new BlockStoneSlab());
			add(slabGranite = new SlabBuilder(MyBlocks.stone, "slab_granite").meta(BlockStone.META_GRANITE).build());
			add(slabDiorite = new SlabBuilder(MyBlocks.stone, "slab_diorite").meta(BlockStone.META_DIORITE).build());
			add(slabAndesite = new SlabBuilder(MyBlocks.stone, "slab_andesite").meta(BlockStone.META_ANDESITE).build());
			add(slabPolishedGranite
					= new SlabBuilder(MyBlocks.stone, "slab_polished_granite")
					.meta(BlockStone.META_POLISHED_GRANITE)
					.specialSideTexture(StringUtil.name.domainedTexture("polished_granite_slab_side")).build());
			add(slabPolishedDiorite
					= new SlabBuilder(MyBlocks.stone, "slab_polished_diorite")
					.meta(BlockStone.META_POLISHED_DIORITE)
					.specialSideTexture(StringUtil.name.domainedTexture("polished_diorite_slab_side")).build());
			add(slabPolishedAndesite
					= new SlabBuilder(MyBlocks.stone, "slab_polished_andesite")
					.meta(BlockStone.META_POLISHED_ANDESITE)
					.specialSideTexture(StringUtil.name.domainedTexture("polished_andesite_slab_side")).build());
			add(slabRedNetherBricks = new SlabBuilder(MyBlocks.redNetherBricks, "slab_red_nether_bricks").build());
			add(slabSmoothQuartz = new SlabBuilder(MyBlocks.smoothQuartz, "slab_smooth_quartz").build());
			add(slabEndStoneBricks = new SlabBuilder(MyBlocks.endStoneBricks, "slab_end_stone_bricks").build());
			add(slabMossyStoneBricks = new SlabBuilder(Blocks.stonebrick, "slab_mossy_stone_bricks").meta(1).build());
			add(slabMossyCobbleStone = new SlabBuilder(Blocks.mossy_cobblestone, "slab_mossy_cobblestone").build());
			add(slabCutSandstone = new SlabBuilder(Blocks.sandstone, "slab_cut_sandstone").meta(McConst.Meta.SANDSTONE_CUT).build());
			add(slabSmoothSandstone = new SlabBuilder(MyBlocks.smoothSandstone, "slab_smooth_sandstone").build());
			add(slabRedSandstone = new SlabBuilder(MyBlocks.redSandStone, "slab_red_sandstone").meta(BlockRedSandStone.META_NORMAL).build());
			add(slabCutRedSandstone = new SlabBuilder(MyBlocks.redSandStone, "slab_cut_red_sandstone").meta(BlockRedSandStone.META_CUT).build());
			add(slabSmoothRedSandstone = new SlabBuilder(MyBlocks.redSandStone, "slab_smooth_red_sandstone").meta(BlockRedSandStone.META_SMOOTH).build());
			add(slabPurpur = new SlabBuilder(MyBlocks.purpurBlock, "slab_purpur").build());
			add(slabPrismarine = new SlabBuilder(MyBlocks.prismarineBlock, "slab_prismarine").build());
			add(slabPrismarineBricks = new SlabBuilder(MyBlocks.prismarineBricks, "slab_prismarine_bricks").build());
			add(slabDarkPrismarine = new SlabBuilder(MyBlocks.prismarineDark, "slab_dark_prismairne").build());
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
			slabSmoothQuartz = null;
			slabEndStoneBricks = null;
			slabMossyStoneBricks = null;
			slabMossyCobbleStone = null;
			slabCutSandstone = null;
			slabSmoothSandstone = null;
			slabRedSandstone = null;
			slabCutRedSandstone = null;
			slabSmoothRedSandstone = null;
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
		
		add(witherRose = isEnabled(EnumDisableableFeatures.witherRose) ? new BlockWitherRose() : null);
		add(flower = isEnabled(EnumDisableableFeatures.flower) ? new BlockNewFlower() : null);
		add(unlimitedPot = new BlockUnlimitedPot());
	}
	
}
