package yuma140902.uptodatemod;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;
import yuma140902.uptodatemod.tileentity.TileEntityObserver;

public class MyTileEntities {
	private MyTileEntities() {}
	
	public static void register() {
		if(ModConfigCore.enable_observer) observer.register();
		GameRegistry.registerTileEntity(TileEntityBarrel.class, "tileentity_barrel");
	}
	
	public static final TileEntityObserver observer = new TileEntityObserver();
}
