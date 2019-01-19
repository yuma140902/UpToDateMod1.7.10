package yuma140902.uptodatemod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.command.server.CommandMessageRaw;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.world.WorldEvent;
import yuma140902.uptodatemod.util.UpdateChecker;

public class ClientEventHandler {
private ClientEventHandler() {}
	
	public static final ClientEventHandler INSTANCE = new ClientEventHandler();
	
	private boolean hasNotifiedAboutUpdate = false;
	@SubscribeEvent
	public void onWorldLoaded(WorldEvent.Load event) {
		if(!event.world.isRemote || !UpdateChecker.INSTANCE.config_doCheckUpdate || hasNotifiedAboutUpdate) {
			return;
		}
		if(!UpdateChecker.INSTANCE.hasNewVersionAvailable()) {
			return;
		}
		IntegratedServer integratedServer = Minecraft.getMinecraft().getIntegratedServer();
		if(integratedServer == null) {
			return;
		}
		
		String msgRaw = StatCollector.translateToLocalFormatted("text.uptodate.update_notify", UpdateChecker.INSTANCE.availableNewVersion, UpdateChecker.INSTANCE.getNewVersionUrl());
		new CommandMessageRaw().processCommand(integratedServer, new String[] {"@a", msgRaw});
		
		hasNotifiedAboutUpdate = true;
	}
}
