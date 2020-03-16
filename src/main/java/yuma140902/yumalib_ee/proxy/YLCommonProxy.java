package yuma140902.yumalib_ee.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumalib_ee.event_handlers.YLCommonEventHandler;

public class YLCommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(YLCommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(YLCommonEventHandler.INSTANCE);
	}
}
