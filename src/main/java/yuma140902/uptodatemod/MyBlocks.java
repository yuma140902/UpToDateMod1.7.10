package yuma140902.uptodatemod;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.BlockObserver;
import yuma140902.uptodatemod.blocks.CoarseDirt;
import yuma140902.uptodatemod.blocks.DoorAcacia;
import yuma140902.uptodatemod.blocks.DoorBirch;
import yuma140902.uptodatemod.blocks.DoorDarkOak;
import yuma140902.uptodatemod.blocks.DoorJungle;
import yuma140902.uptodatemod.blocks.DoorSpruce;
import yuma140902.uptodatemod.blocks.generics.GeneticsFence;
import yuma140902.uptodatemod.blocks.generics.GeneticsFenceGate;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockDarkPrismarine;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarine;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarineBrick;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockSeaLantern;
import yuma140902.uptodatemod.util.Stat;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		((IRegisterable) stone).register();
		((IRegisterable) doorAcacia).register();
		((IRegisterable) doorBirch).register();
		((IRegisterable) doorDarkOak).register();
		((IRegisterable) doorJungle).register();
		((IRegisterable) doorSpruce).register();
		((IRegisterable) fenceAcacia).register();
		((IRegisterable) fenceBirch).register();
		((IRegisterable) fenceDarkOak).register();
		((IRegisterable) fenceJungle).register();
		((IRegisterable) fenceSpruce).register();
		((IRegisterable) fenceGateAcacia).register();
		((IRegisterable) fenceGateBirch).register();
		((IRegisterable) fenceGateDarkOak).register();
		((IRegisterable) fenceGateJungle).register();
		((IRegisterable) fenceGateSpruce).register();
		
		((IRegisterable) coarseDirt).register();
		
		prismarineBlock.register();
		prismarineBrick.register();
		prismarineDark.register();
		seaLantern.register();
		
		observer.register();
	}
	
	public static final Block stone = new yuma140902.uptodatemod.blocks.Stone();
	public static final Block doorAcacia = new DoorAcacia();
	public static final Block doorBirch = new DoorBirch();
	public static final Block doorDarkOak = new DoorDarkOak();
	public static final Block doorJungle = new DoorJungle();
	public static final Block doorSpruce = new DoorSpruce();
	
	public static final Block fenceAcacia = new GeneticsFence("planks_acacia", "fence_acacia");
	public static final Block fenceBirch = new GeneticsFence("planks_birch", "fence_birch");
	public static final Block fenceDarkOak = new GeneticsFence("planks_big_oak", "fence_dark_oak");
	public static final Block fenceJungle = new GeneticsFence("planks_jungle", "fence_jungle");
	public static final Block fenceSpruce = new GeneticsFence("planks_spruce", "fence_spruce");
	
	public static final Block fenceGateAcacia = new GeneticsFenceGate(Stat.PLANK_META_ACACIA, "fence_gate_acacia");
	public static final Block fenceGateBirch = new GeneticsFenceGate(Stat.PLANK_META_BIRCH, "fence_gate_birch");
	public static final Block fenceGateDarkOak = new GeneticsFenceGate(Stat.PLANK_META_DARKOAK, "fence_gate_dark_oak");
	public static final Block fenceGateJungle = new GeneticsFenceGate(Stat.PLANK_META_JUNGLE, "fence_gate_jungle");
	public static final Block fenceGateSpruce = new GeneticsFenceGate(Stat.PLANK_META_SPRUCE, "fence_gate_spruce");
	
	public static final Block coarseDirt = new CoarseDirt();
	
	public static final BlockPrismarine prismarineBlock = new BlockPrismarine();
	public static final BlockPrismarineBrick prismarineBrick = new BlockPrismarineBrick();
	public static final BlockDarkPrismarine prismarineDark = new BlockDarkPrismarine();
	public static final BlockSeaLantern seaLantern = new BlockSeaLantern();
	
	public static final BlockObserver observer = new BlockObserver();
}
