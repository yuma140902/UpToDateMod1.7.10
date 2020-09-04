package yuma140902.yumaessentials.event_handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import yuma140902.yumaessentials.YEConstants;
import yuma140902.yumaessentials.config.YEConfigCore;

public class YECommonEventHandler {
private YECommonEventHandler() {}
	
	public static final YECommonEventHandler INSTANCE = new YECommonEventHandler();
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(YEConstants.MOD_ID.equals(event.modID))
			YEConfigCore.syncConfig();
	}
}
