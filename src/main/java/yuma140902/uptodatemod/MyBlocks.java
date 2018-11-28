package yuma140902.uptodatemod;

import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.blocks.BlockConcrete;
import yuma140902.uptodatemod.blocks.BlockConcretePowder;
import yuma140902.uptodatemod.blocks.BlockDoorAcacia;
import yuma140902.uptodatemod.blocks.BlockDoorBirch;
import yuma140902.uptodatemod.blocks.BlockDoorDarkOak;
import yuma140902.uptodatemod.blocks.BlockDoorJungle;
import yuma140902.uptodatemod.blocks.BlockDoorSpruce;
import yuma140902.uptodatemod.blocks.BlockIronTrapDoor;
import yuma140902.uptodatemod.blocks.BlockObserver;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.blocks.generics.GenericButton;
import yuma140902.uptodatemod.blocks.generics.GenericFence;
import yuma140902.uptodatemod.blocks.generics.GenericFenceGate;
import yuma140902.uptodatemod.blocks.generics.GenericStairs;
import yuma140902.uptodatemod.blocks.generics.GenericTrapDoor;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockDarkPrismarine;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarine;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarineBrick;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockSeaLantern;
import yuma140902.uptodatemod.util.Stat;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		stone.register();
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
		prismarineBrick.register();
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
		
		blockConcrete.register();
		blockConcretePowder.register();
		
		stairsGranite.register();
		stairsDiorite.register();
		stairsAndesite.register();
		stairsPolishedGranite.register();
		stairsPolishedDiorite.register();
		stairsPolishedAndesite.register();
	}
	
	public static final yuma140902.uptodatemod.blocks.BlockStone stone = new yuma140902.uptodatemod.blocks.BlockStone();
	public static final BlockDoorAcacia doorAcacia = new BlockDoorAcacia();
	public static final BlockDoorBirch doorBirch = new BlockDoorBirch();
	public static final BlockDoorDarkOak doorDarkOak = new BlockDoorDarkOak();
	public static final BlockDoorJungle doorJungle = new BlockDoorJungle();
	public static final BlockDoorSpruce doorSpruce = new BlockDoorSpruce();
	
	public static final GenericFence fenceAcacia = new GenericFence("planks_acacia", "fence_acacia");
	public static final GenericFence fenceBirch = new GenericFence("planks_birch", "fence_birch");
	public static final GenericFence fenceDarkOak = new GenericFence("planks_big_oak", "fence_dark_oak");
	public static final GenericFence fenceJungle = new GenericFence("planks_jungle", "fence_jungle");
	public static final GenericFence fenceSpruce = new GenericFence("planks_spruce", "fence_spruce");
	
	public static final GenericFenceGate fenceGateAcacia = new GenericFenceGate(Stat.PLANK_META_ACACIA, "fence_gate_acacia");
	public static final GenericFenceGate fenceGateBirch = new GenericFenceGate(Stat.PLANK_META_BIRCH, "fence_gate_birch");
	public static final GenericFenceGate fenceGateDarkOak = new GenericFenceGate(Stat.PLANK_META_DARKOAK, "fence_gate_dark_oak");
	public static final GenericFenceGate fenceGateJungle = new GenericFenceGate(Stat.PLANK_META_JUNGLE, "fence_gate_jungle");
	public static final GenericFenceGate fenceGateSpruce = new GenericFenceGate(Stat.PLANK_META_SPRUCE, "fence_gate_spruce");
	
	public static final BlockCoarseDirt coarseDirt = new BlockCoarseDirt();
	
	public static final BlockPrismarine prismarineBlock = new BlockPrismarine();
	public static final BlockPrismarineBrick prismarineBrick = new BlockPrismarineBrick();
	public static final BlockDarkPrismarine prismarineDark = new BlockDarkPrismarine();
	public static final BlockSeaLantern seaLantern = new BlockSeaLantern();
	
	public static final BlockObserver observer = ModUpToDateMod.INSTANCE.config_enable_observer ? new BlockObserver() : null;
	
	public static final GenericTrapDoor trapDoorAcacia = new GenericTrapDoor("trap_door_acacia", Stat.PLANK_META_ACACIA);
	public static final GenericTrapDoor trapDoorBirch = new GenericTrapDoor("trap_door_birch", Stat.PLANK_META_BIRCH);
	public static final GenericTrapDoor trapDoorDarkOak = new GenericTrapDoor("trap_door_dark_oak", Stat.PLANK_META_DARKOAK);
	public static final GenericTrapDoor trapDoorJungle = new GenericTrapDoor("trap_door_jungle", Stat.PLANK_META_JUNGLE);
	public static final GenericTrapDoor trapDoorSpruce = new GenericTrapDoor("trap_door_spruce", Stat.PLANK_META_SPRUCE);
	public static final BlockIronTrapDoor trapDoorIron = new BlockIronTrapDoor();
	
	public static final GenericButton buttonAcacia = new GenericButton(Stat.PLANK_META_ACACIA, "button_acacia");
	public static final GenericButton buttonBirch = new GenericButton(Stat.PLANK_META_BIRCH, "button_birch");
	public static final GenericButton buttonDarkOak = new GenericButton(Stat.PLANK_META_DARKOAK, "button_dark_oak");
	public static final GenericButton buttonJungle = new GenericButton(Stat.PLANK_META_JUNGLE, "button_jungle");
	public static final GenericButton buttonSpruce = new GenericButton(Stat.PLANK_META_SPRUCE, "button_spruce");
	
	public static final BlockConcrete blockConcrete = new BlockConcrete();
	public static final BlockConcretePowder blockConcretePowder = new BlockConcretePowder();
	
	public static final GenericStairs stairsGranite = new GenericStairs(MyBlocks.stone, BlockStone.META_GRANITE, "stairs_granite");
	public static final GenericStairs stairsDiorite = new GenericStairs(MyBlocks.stone, BlockStone.META_DIORITE, "stairs_diorite");
	public static final GenericStairs stairsAndesite = new GenericStairs(MyBlocks.stone, BlockStone.META_ANDESITE, "stairs_andesite");
	public static final GenericStairs stairsPolishedGranite = new GenericStairs(MyBlocks.stone, BlockStone.META_POLISHED_GRANITE, "stairs_polished_granite");
	public static final GenericStairs stairsPolishedDiorite = new GenericStairs(MyBlocks.stone, BlockStone.META_POLISHED_DIORITE, "stairs_polished_diorite");
	public static final GenericStairs stairsPolishedAndesite = new GenericStairs(MyBlocks.stone, BlockStone.META_POLISHED_ANDESITE, "stairs_polished_andesite");
}
