package yuma140902.yumalib.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.command.server.CommandMessageRaw;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import yuma140902.yumalib.api.registry.UpdateCheckerRegistry;
import yuma140902.yumalib.api.update.IUpdateChecker;

public class YLClientEventHandler {
	private YLClientEventHandler() {}
	
	public static final YLClientEventHandler INSTANCE = new YLClientEventHandler();
	
	
	private boolean hasNotifiedAboutUpdate = false;
	
	private void updateNotify(WorldEvent.Load event) {
		if(!event.world.isRemote || hasNotifiedAboutUpdate) {
			return;
		}
		IntegratedServer integratedServer = Minecraft.getMinecraft().getIntegratedServer();
		if(integratedServer == null) {
			return;
		}
		
		for(IUpdateChecker updateChecker : UpdateCheckerRegistry.INSTANCE.list()) {
			if(!updateChecker.hasNewVersionAvailable()) {
				continue;
			}
			String msgRaw = StatCollector.translateToLocalFormatted("text.yumalib.update_notify", updateChecker.getModName(), updateChecker.getAvailableNewVersion(), updateChecker.getNewVersionUrl());
			new CommandMessageRaw().processCommand(integratedServer, new String[] {"@a", msgRaw});
		}
		
		hasNotifiedAboutUpdate = true;
	}
	
	@SubscribeEvent
	public void onWorldLoaded(WorldEvent.Load event) {
		updateNotify(event);
	}
	
	@SubscribeEvent
	public void onTooltipShown(ItemTooltipEvent event) {
		TooltipHandler.onTooltipShown(event);
	}
	
}
