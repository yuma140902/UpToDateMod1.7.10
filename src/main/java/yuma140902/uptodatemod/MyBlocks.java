package yuma140902.uptodatemod;

import net.minecraft.block.Block;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		((IRegisterable) stone).register();
	}
	
	public static final Block stone = new yuma140902.uptodatemod.blocks.Stone();
}
