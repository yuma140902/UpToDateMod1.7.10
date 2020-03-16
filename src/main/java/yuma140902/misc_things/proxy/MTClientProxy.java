package yuma140902.misc_things.proxy;

import net.minecraftforge.common.MinecraftForge;
import yuma140902.misc_things.event_handlers.MTClientEventHandler;

public class MTClientProxy extends MTCommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(MTClientEventHandler.INSTANCE);
	}
}
