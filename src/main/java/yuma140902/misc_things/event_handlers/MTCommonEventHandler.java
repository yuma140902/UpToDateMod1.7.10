package yuma140902.misc_things.event_handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import yuma140902.misc_things.MTConstants;
import yuma140902.misc_things.config.MTConfigCore;

public class MTCommonEventHandler {
private MTCommonEventHandler() {}
	
	public static final MTCommonEventHandler INSTANCE = new MTCommonEventHandler();
	
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(MTConstants.MOD_ID.equals(event.modID))
			MTConfigCore.syncConfig();
	}
}
