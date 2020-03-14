package yuma140902.mcmodlib.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.mcmodlib.event_handlers.CommonEventHandler;

public class CommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(CommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(CommonEventHandler.INSTANCE);
	}
}
