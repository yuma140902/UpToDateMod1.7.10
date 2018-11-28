package yuma140902.uptodatemod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;

public class ServerEventHandler {
	private ServerEventHandler() {}
	
	public static final ServerEventHandler INSTANCE = new ServerEventHandler();
	
	@SubscribeEvent
	public void onUseHoeEvent(UseHoeEvent event) {
		BlockCoarseDirt.onUseHoeEvent(event);
	}
}
