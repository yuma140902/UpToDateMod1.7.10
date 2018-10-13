package yuma140902.uptodatemod;

import yuma140902.uptodatemod.tileentity.TileEntityObserver;

public class MyTileEntities {
	private MyTileEntities() {}
	
	public static void register() {
		observer.register();
	}
	
	public static final TileEntityObserver observer = new TileEntityObserver();
}
