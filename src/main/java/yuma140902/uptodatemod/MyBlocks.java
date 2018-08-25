package yuma140902.uptodatemod;

import yuma140902.uptodatemod.blocks.IMyBlock;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		stone.register();
	}
	
	public static final IMyBlock stone = new yuma140902.uptodatemod.blocks.Stone();
}
