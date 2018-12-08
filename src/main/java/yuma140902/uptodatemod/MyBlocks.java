package yuma140902.uptodatemod;

import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.blocks.BlockBone;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.blocks.BlockDarkPrismarine;
import yuma140902.uptodatemod.blocks.BlockDoorAcacia;
import yuma140902.uptodatemod.blocks.BlockDoorBirch;
import yuma140902.uptodatemod.blocks.BlockDoorDarkOak;
import yuma140902.uptodatemod.blocks.BlockDoorJungle;
import yuma140902.uptodatemod.blocks.BlockDoorSpruce;
import yuma140902.uptodatemod.blocks.BlockEndStoneBricks;
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
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.blocks.generics.BlockGenericButton;
import yuma140902.uptodatemod.blocks.generics.BlockGenericFence;
import yuma140902.uptodatemod.blocks.generics.BlockGenericFenceGate;
import yuma140902.uptodatemod.blocks.generics.BlockGenericSlab;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStairs;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStrippedLog;
import yuma140902.uptodatemod.blocks.generics.BlockGenericTrapDoor;
import yuma140902.uptodatemod.util.Stat;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		stone.register();
		
		strippedLogAcacia.register();
		strippedLogBirch.register();
		strippedLogDarkOak.register();
		strippedLogJungle.register();
		strippedLogOak.register();
		strippedLogSpruce.register();
		
		doorAcacia.register();
		doorBirch.register();
		doorDarkOak.register();
		doorJungle.register();
		doorSpruce.register();
		fenceAcacia.register();
		fenceBirch.register();
		fenceDarkOak.register();
		fenceJungle.register();
		fenceSpruce.register();
		fenceGateAcacia.register();
		fenceGateBirch.register();
		fenceGateDarkOak.register();
		fenceGateJungle.register();
		fenceGateSpruce.register();
		
		coarseDirt.register();
		
		prismarineBlock.register();
		prismarineBricks.register();
		prismarineDark.register();
		seaLantern.register();
		
		if(ModUpToDateMod.INSTANCE.config_enable_observer) observer.register();
		
		trapDoorAcacia.register();
		trapDoorBirch.register();
		trapDoorDarkOak.register();
		trapDoorJungle.register();
		trapDoorSpruce.register();
		trapDoorIron.register();
		
		buttonAcacia.register();
		buttonBirch.register();
		buttonDarkOak.register();
		buttonJungle.register();
		buttonSpruce.register();
		
		redNetherBricks.register();
		netherWartBlock.register();
		magmaBlock.register();
		
		endStoneBricks.register();
		purpurBlock.register();
		purpurPillar.register();
		
		boneBlock.register();
		
		redSandStone.register();
		
		stairsStone.register();
		stairsGranite.register();
		stairsDiorite.register();
		stairsAndesite.register();
		stairsPolishedGranite.register();
		stairsPolishedDiorite.register();
		stairsPolishedAndesite.register();
		stairsRedNetherBricks.register();
		stairsPrismarine.register();
		stairsPrismarineBricks.register();
		stairsDarkPrismarine.register();
		stairsPurpur.register();
		stairsRedSandstone.register();
		stairsEndStoneBricks.register();
		stairsMossyStoneBricks.register();
		stairsMossyCobbleStone.register();
		
		slabRedSandstone.setSlab(slabRedSandstone, doubleSlabRedSandstone);
		slabRedSandstone.register();
		doubleSlabRedSandstone.setSlab(slabRedSandstone, doubleSlabRedSandstone);
		doubleSlabRedSandstone.register();
	}
	
	public static final yuma140902.uptodatemod.blocks.BlockStone stone = new yuma140902.uptodatemod.blocks.BlockStone();
	
	public static final BlockGenericStrippedLog strippedLogAcacia = new BlockGenericStrippedLog("stripped_log_acacia");
	public static final BlockGenericStrippedLog strippedLogBirch = new BlockGenericStrippedLog("stripped_log_birch");
	public static final BlockGenericStrippedLog strippedLogDarkOak = new BlockGenericStrippedLog("stripped_log_dark_oak");
	public static final BlockGenericStrippedLog strippedLogJungle = new BlockGenericStrippedLog("stripped_log_jungle");
	public static final BlockGenericStrippedLog strippedLogOak = new BlockGenericStrippedLog("stripped_log_oak");
	public static final BlockGenericStrippedLog strippedLogSpruce = new BlockGenericStrippedLog("stripped_log_spruce");
	
	public static final BlockDoorAcacia doorAcacia = new BlockDoorAcacia();
	public static final BlockDoorBirch doorBirch = new BlockDoorBirch();
	public static final BlockDoorDarkOak doorDarkOak = new BlockDoorDarkOak();
	public static final BlockDoorJungle doorJungle = new BlockDoorJungle();
	public static final BlockDoorSpruce doorSpruce = new BlockDoorSpruce();
	
	public static final BlockGenericFence fenceAcacia = new BlockGenericFence("planks_acacia", "fence_acacia");
	public static final BlockGenericFence fenceBirch = new BlockGenericFence("planks_birch", "fence_birch");
	public static final BlockGenericFence fenceDarkOak = new BlockGenericFence("planks_big_oak", "fence_dark_oak");
	public static final BlockGenericFence fenceJungle = new BlockGenericFence("planks_jungle", "fence_jungle");
	public static final BlockGenericFence fenceSpruce = new BlockGenericFence("planks_spruce", "fence_spruce");
	
	public static final BlockGenericFenceGate fenceGateAcacia = new BlockGenericFenceGate(Stat.PLANK_META_ACACIA, "fence_gate_acacia");
	public static final BlockGenericFenceGate fenceGateBirch = new BlockGenericFenceGate(Stat.PLANK_META_BIRCH, "fence_gate_birch");
	public static final BlockGenericFenceGate fenceGateDarkOak = new BlockGenericFenceGate(Stat.PLANK_META_DARKOAK, "fence_gate_dark_oak");
	public static final BlockGenericFenceGate fenceGateJungle = new BlockGenericFenceGate(Stat.PLANK_META_JUNGLE, "fence_gate_jungle");
	public static final BlockGenericFenceGate fenceGateSpruce = new BlockGenericFenceGate(Stat.PLANK_META_SPRUCE, "fence_gate_spruce");
	
	public static final BlockCoarseDirt coarseDirt = new BlockCoarseDirt();
	
	public static final BlockPrismarine prismarineBlock = new BlockPrismarine();
	public static final BlockPrismarineBricks prismarineBricks = new BlockPrismarineBricks();
	public static final BlockDarkPrismarine prismarineDark = new BlockDarkPrismarine();
	public static final BlockSeaLantern seaLantern = new BlockSeaLantern();
	
	public static final BlockObserver observer = ModUpToDateMod.INSTANCE.config_enable_observer ? new BlockObserver() : null;
	
	public static final BlockGenericTrapDoor trapDoorAcacia = new BlockGenericTrapDoor("trap_door_acacia", Stat.PLANK_META_ACACIA);
	public static final BlockGenericTrapDoor trapDoorBirch = new BlockGenericTrapDoor("trap_door_birch", Stat.PLANK_META_BIRCH);
	public static final BlockGenericTrapDoor trapDoorDarkOak = new BlockGenericTrapDoor("trap_door_dark_oak", Stat.PLANK_META_DARKOAK);
	public static final BlockGenericTrapDoor trapDoorJungle = new BlockGenericTrapDoor("trap_door_jungle", Stat.PLANK_META_JUNGLE);
	public static final BlockGenericTrapDoor trapDoorSpruce = new BlockGenericTrapDoor("trap_door_spruce", Stat.PLANK_META_SPRUCE);
	public static final BlockIronTrapDoor trapDoorIron = new BlockIronTrapDoor();
	
	public static final BlockGenericButton buttonAcacia = new BlockGenericButton(Stat.PLANK_META_ACACIA, "button_acacia");
	public static final BlockGenericButton buttonBirch = new BlockGenericButton(Stat.PLANK_META_BIRCH, "button_birch");
	public static final BlockGenericButton buttonDarkOak = new BlockGenericButton(Stat.PLANK_META_DARKOAK, "button_dark_oak");
	public static final BlockGenericButton buttonJungle = new BlockGenericButton(Stat.PLANK_META_JUNGLE, "button_jungle");
	public static final BlockGenericButton buttonSpruce = new BlockGenericButton(Stat.PLANK_META_SPRUCE, "button_spruce");
	
	public static final BlockRedNetherBricks redNetherBricks = new BlockRedNetherBricks();
	public static final BlockNetherWart netherWartBlock = new BlockNetherWart();
	public static final BlockMagma magmaBlock = new BlockMagma();
	
	public static final BlockEndStoneBricks endStoneBricks = new BlockEndStoneBricks();
	public static final BlockPurpur purpurBlock = new BlockPurpur();
	public static final BlockPurpurPillar purpurPillar = new BlockPurpurPillar();
	
	public static final BlockBone boneBlock = new BlockBone();
	
	public static final BlockRedSandStone redSandStone = new BlockRedSandStone();
	
	public static final BlockGenericStairs stairsStone = new BlockGenericStairs(Blocks.stone, 0, "stairs_stone");
	public static final BlockGenericStairs stairsGranite = new BlockGenericStairs(MyBlocks.stone, BlockStone.META_GRANITE, "stairs_granite");
	public static final BlockGenericStairs stairsDiorite = new BlockGenericStairs(MyBlocks.stone, BlockStone.META_DIORITE, "stairs_diorite");
	public static final BlockGenericStairs stairsAndesite = new BlockGenericStairs(MyBlocks.stone, BlockStone.META_ANDESITE, "stairs_andesite");
	public static final BlockGenericStairs stairsPolishedGranite = new BlockGenericStairs(MyBlocks.stone, BlockStone.META_POLISHED_GRANITE, "stairs_polished_granite");
	public static final BlockGenericStairs stairsPolishedDiorite = new BlockGenericStairs(MyBlocks.stone, BlockStone.META_POLISHED_DIORITE, "stairs_polished_diorite");
	public static final BlockGenericStairs stairsPolishedAndesite = new BlockGenericStairs(MyBlocks.stone, BlockStone.META_POLISHED_ANDESITE, "stairs_polished_andesite");
	public static final BlockGenericStairs stairsRedNetherBricks = new BlockGenericStairs(MyBlocks.redNetherBricks, 0, "stairs_red_nether_bricks");
	public static final BlockGenericStairs stairsPrismarine = new BlockGenericStairs(MyBlocks.prismarineBlock, 0, "stairs_prismarine");
	public static final BlockGenericStairs stairsPrismarineBricks = new BlockGenericStairs(MyBlocks.prismarineBricks, 0, "stairs_prismarine_bricks");
	public static final BlockGenericStairs stairsDarkPrismarine = new BlockGenericStairs(MyBlocks.prismarineDark, 0, "stairs_dark_prismarine");
	public static final BlockGenericStairs stairsPurpur = new BlockGenericStairs(MyBlocks.purpurBlock, 0, "stairs_purpur");
	public static final BlockGenericStairs stairsRedSandstone = new BlockGenericStairs(MyBlocks.redSandStone, 0, "stairs_red_sandstone");
	public static final BlockGenericStairs stairsEndStoneBricks = new BlockGenericStairs(MyBlocks.endStoneBricks, 0, "stairs_end_stone_bricks");
	public static final BlockGenericStairs stairsMossyStoneBricks = new BlockGenericStairs(Blocks.stonebrick, 1, "stairs_mossy_stone_bricks");
	public static final BlockGenericStairs stairsMossyCobbleStone = new BlockGenericStairs(Blocks.mossy_cobblestone, 0, "stairs_mossy_cobblestone");
	
	public static final BlockGenericSlab slabRedSandstone = new BlockGenericSlab(false, MyBlocks.redSandStone, 0, "slab_red_sandstone");
	public static final BlockGenericSlab doubleSlabRedSandstone = new BlockGenericSlab(true, MyBlocks.redSandStone, 0, "slab_red_sandstone");
}
