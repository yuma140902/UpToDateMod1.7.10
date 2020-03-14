package yuma140902.mcmodlib.event_handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import yuma140902.mcmodlib.YumaLibConstants;
import yuma140902.mcmodlib.config.YumaLibConfigCore;

public class CommonEventHandler {
private CommonEventHandler() {}
	
	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(YumaLibConstants.MOD_ID.equals(event.modID))
			YumaLibConfigCore.syncConfig();
	}
}
