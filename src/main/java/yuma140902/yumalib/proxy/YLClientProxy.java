package yuma140902.yumalib.proxy;

import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumalib.event_handlers.YLClientEventHandler;

public class YLClientProxy extends YLCommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(YLClientEventHandler.INSTANCE);
	}
}
