package yuma140902.uptodatemod.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.uptodatemod.event_handlers.ServerEventHandler;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(ServerEventHandler.INSTANCE);
	}
}
