package yuma140902.misc_things.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.misc_things.event_handlers.MTCommonEventHandler;

public class MTCommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(MTCommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(MTCommonEventHandler.INSTANCE);
	}
}
