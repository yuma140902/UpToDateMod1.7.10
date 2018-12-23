package yuma140902.uptodatemod;

import cpw.mods.fml.common.network.NetworkRegistry;
import yuma140902.uptodatemod.gui.BarrelGuiHandler;

public final class MyGuis {
	private MyGuis() {}
	
	public static final int ID_BARREL = 0;
	
	public static void register() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ModUpToDateMod.INSTANCE, new BarrelGuiHandler());
	}
}
