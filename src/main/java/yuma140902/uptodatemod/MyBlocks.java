package yuma140902.uptodatemod;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.DoorAcacia;
import yuma140902.uptodatemod.blocks.DoorBirch;
import yuma140902.uptodatemod.blocks.DoorDarkOak;
import yuma140902.uptodatemod.blocks.DoorJungle;
import yuma140902.uptodatemod.blocks.DoorSpruce;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		((IRegisterable) stone).register();
		((IRegisterable) doorAcacia).register();
		((IRegisterable) doorBirch).register();
		((IRegisterable) doorDarkOak).register();
		((IRegisterable) doorJungle).register();
		((IRegisterable) doorSpruce).register();
	}
	
	public static final Block stone = new yuma140902.uptodatemod.blocks.Stone();
	public static final Block doorAcacia = new DoorAcacia();
	public static final Block doorBirch = new DoorBirch();
	public static final Block doorDarkOak = new DoorDarkOak();
	public static final Block doorJungle = new DoorJungle();
	public static final Block doorSpruce = new DoorSpruce();
}
