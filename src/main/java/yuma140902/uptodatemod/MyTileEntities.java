package yuma140902.uptodatemod;

import yuma140902.uptodatemod.tileentity.TileEntityObserver;

public class MyTileEntities {
	private MyTileEntities() {}
	
	public static void register() {
		if(ModUpToDateMod.config.experimental.enableObserver.get()) observer.register();
	}
	
	public static final TileEntityObserver observer = new TileEntityObserver();
}
