package yuma140902.uptodatemod;

import yuma140902.uptodatemod.blocks.BlockObserver;
import yuma140902.uptodatemod.blocks.CoarseDirt;
import yuma140902.uptodatemod.blocks.DoorAcacia;
import yuma140902.uptodatemod.blocks.DoorBirch;
import yuma140902.uptodatemod.blocks.DoorDarkOak;
import yuma140902.uptodatemod.blocks.DoorJungle;
import yuma140902.uptodatemod.blocks.DoorSpruce;
import yuma140902.uptodatemod.blocks.generics.GenericFence;
import yuma140902.uptodatemod.blocks.generics.GenericFenceGate;
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
	}
	
	public static final yuma140902.uptodatemod.blocks.Stone stone = new yuma140902.uptodatemod.blocks.Stone();
	public static final DoorAcacia doorAcacia = new DoorAcacia();
	public static final DoorBirch doorBirch = new DoorBirch();
	public static final DoorDarkOak doorDarkOak = new DoorDarkOak();
	public static final DoorJungle doorJungle = new DoorJungle();
	public static final DoorSpruce doorSpruce = new DoorSpruce();
	
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
	
	public static final CoarseDirt coarseDirt = new CoarseDirt();
	
	public static final BlockPrismarine prismarineBlock = new BlockPrismarine();
	public static final BlockPrismarineBrick prismarineBrick = new BlockPrismarineBrick();
	public static final BlockDarkPrismarine prismarineDark = new BlockDarkPrismarine();
	public static final BlockSeaLantern seaLantern = new BlockSeaLantern();
	
	public static final BlockObserver observer = ModUpToDateMod.INSTANCE.config_enable_observer ? new BlockObserver() : null;
	
	public static final GenericTrapDoor trapDoorAcacia = new GenericTrapDoor("trap_door_acacia");
	public static final GenericTrapDoor trapDoorBirch = new GenericTrapDoor("trap_door_birch");
	public static final GenericTrapDoor trapDoorDarkOak = new GenericTrapDoor("trap_door_dark_oak");
	public static final GenericTrapDoor trapDoorJungle = new GenericTrapDoor("trap_door_jungle");
	public static final GenericTrapDoor trapDoorSpruce = new GenericTrapDoor("trap_door_spruce");
	
}
