package yuma140902.yumaessentials.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumaessentials.iea.InitialEnvironmentArranger;
import yuma140902.yumaessentials.event_handlers.YECommonEventHandler;

public class YECommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(YECommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(YECommonEventHandler.INSTANCE);
	}
	
	public void arrangeEnvironments(){
		InitialEnvironmentArranger.INSTANCE.arrangeServer();
	}
	
}
