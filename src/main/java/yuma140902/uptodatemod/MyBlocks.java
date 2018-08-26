package yuma140902.uptodatemod;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		stone.register();
	}
	
	public static final IRegisterable stone = new yuma140902.uptodatemod.blocks.Stone();
}
