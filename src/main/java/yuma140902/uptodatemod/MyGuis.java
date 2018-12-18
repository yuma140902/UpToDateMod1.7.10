package yuma140902.uptodatemod;

import cpw.mods.fml.common.network.NetworkRegistry;
import yuma140902.uptodatemod.gui.BarrelGuiHandler;

public final class MyGuis {
	private MyGuis() {}
	
	public static final MyGuis INSTANCE = new MyGuis();
	
	private int id = 0;
	public int getNextGuiId() {
		return id++;
	}
	
	public void register() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ModUpToDateMod.INSTANCE, new BarrelGuiHandler());
	}
}
