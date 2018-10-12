package yuma140902.uptodatemod;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.CoarseDirt;
import yuma140902.uptodatemod.blocks.DoorAcacia;
import yuma140902.uptodatemod.blocks.DoorBirch;
import yuma140902.uptodatemod.blocks.DoorDarkOak;
import yuma140902.uptodatemod.blocks.DoorJungle;
import yuma140902.uptodatemod.blocks.DoorSpruce;
import yuma140902.uptodatemod.blocks.FenceAcacia;
import yuma140902.uptodatemod.blocks.FenceBirch;
import yuma140902.uptodatemod.blocks.FenceDarkOak;
import yuma140902.uptodatemod.blocks.FenceGateAcacia;
import yuma140902.uptodatemod.blocks.FenceGateBirch;
import yuma140902.uptodatemod.blocks.FenceGateDarkOak;
import yuma140902.uptodatemod.blocks.FenceGateJungle;
import yuma140902.uptodatemod.blocks.FenceGateSpruce;
import yuma140902.uptodatemod.blocks.FenceJungle;
import yuma140902.uptodatemod.blocks.FenceSpruce;
import yuma140902.uptodatemod.blocks.ocean_monument.BlockPrismarine;

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
	}
	
	public static final Block stone = new yuma140902.uptodatemod.blocks.Stone();
	public static final Block doorAcacia = new DoorAcacia();
	public static final Block doorBirch = new DoorBirch();
	public static final Block doorDarkOak = new DoorDarkOak();
	public static final Block doorJungle = new DoorJungle();
	public static final Block doorSpruce = new DoorSpruce();
	
	public static final Block fenceAcacia = new FenceAcacia();
	public static final Block fenceBirch = new FenceBirch();
	public static final Block fenceDarkOak = new FenceDarkOak();
	public static final Block fenceJungle = new FenceJungle();
	public static final Block fenceSpruce = new FenceSpruce();
	
	public static final Block fenceGateAcacia = new FenceGateAcacia();
	public static final Block fenceGateBirch = new FenceGateBirch();
	public static final Block fenceGateDarkOak = new FenceGateDarkOak();
	public static final Block fenceGateJungle = new FenceGateJungle();
	public static final Block fenceGateSpruce = new FenceGateSpruce();
	
	public static final Block coarseDirt = new CoarseDirt();
	
	public static final BlockPrismarine prismarineBlock = new BlockPrismarine();
}
