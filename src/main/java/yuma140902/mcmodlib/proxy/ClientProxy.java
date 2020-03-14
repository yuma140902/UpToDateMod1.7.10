package yuma140902.mcmodlib.proxy;

import net.minecraftforge.common.MinecraftForge;
import yuma140902.mcmodlib.event_handlers.ClientEventHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(ClientEventHandler.INSTANCE);
	}
}
