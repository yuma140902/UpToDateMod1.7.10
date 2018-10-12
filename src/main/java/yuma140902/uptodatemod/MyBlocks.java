package yuma140902.uptodatemod;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.CoarseDirt;
import yuma140902.uptodatemod.blocks.DoorAcacia;
import yuma140902.uptodatemod.blocks.DoorBirch;
import yuma140902.uptodatemod.blocks.DoorDarkOak;
import yuma140902.uptodatemod.blocks.DoorJungle;
import yuma140902.uptodatemod.blocks.DoorSpruce;
import yuma140902.uptodatemod.blocks.FenceGateAcacia;
import yuma140902.uptodatemod.blocks.FenceGateBirch;
import yuma140902.uptodatemod.blocks.FenceGateDarkOak;
import yuma140902.uptodatemod.blocks.FenceGateJungle;
import yuma140902.uptodatemod.blocks.FenceGateSpruce;
import yuma140902.uptodatemod.blocks.generics.Fence;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockDarkPrismarine;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarine;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarineBrick;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockSeaLantern;

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
	}
	
	public static final Block stone = new yuma140902.uptodatemod.blocks.Stone();
	public static final Block doorAcacia = new DoorAcacia();
	public static final Block doorBirch = new DoorBirch();
	public static final Block doorDarkOak = new DoorDarkOak();
	public static final Block doorJungle = new DoorJungle();
	public static final Block doorSpruce = new DoorSpruce();
	
	public static final Block fenceAcacia = new Fence("planks_acacia", "fence_acacia");
	public static final Block fenceBirch = new Fence("planks_birch", "fence_birch");
	public static final Block fenceDarkOak = new Fence("planks_big_oak", "fence_dark_oak");
	public static final Block fenceJungle = new Fence("planks_jungle", "fence_jungle");
	public static final Block fenceSpruce = new Fence("planks_spruce", "fence_spruce");
	
	public static final Block fenceGateAcacia = new FenceGateAcacia();
	public static final Block fenceGateBirch = new FenceGateBirch();
	public static final Block fenceGateDarkOak = new FenceGateDarkOak();
	public static final Block fenceGateJungle = new FenceGateJungle();
	public static final Block fenceGateSpruce = new FenceGateSpruce();
	
	public static final Block coarseDirt = new CoarseDirt();
	
	public static final BlockPrismarine prismarineBlock = new BlockPrismarine();
	public static final BlockPrismarineBrick prismarineBrick = new BlockPrismarineBrick();
	public static final BlockDarkPrismarine prismarineDark = new BlockDarkPrismarine();
	public static final BlockSeaLantern seaLantern = new BlockSeaLantern();
}
