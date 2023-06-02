package yuma140902.yumaessentials.proxy;

import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumaessentials.iea.InitialEnvironmentArranger;
import yuma140902.yumaessentials.event_handlers.YEClientEventHandler;

public class YEClientProxy extends YECommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(YEClientEventHandler.INSTANCE);
	}
	
	public void arrangeEnvironments(){
		super.arrangeEnvironments();
		InitialEnvironmentArranger.INSTANCE.arrangeClient();
	}
	
}
