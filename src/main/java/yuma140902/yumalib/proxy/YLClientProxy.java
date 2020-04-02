package yuma140902.yumalib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumalib.event_handlers.YLClientEventHandler;

public class YLClientProxy extends YLCommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(YLClientEventHandler.INSTANCE);
	}
	
	private boolean isYuma140902 = false;
	
	@Override
	public void checkIfIsYuma140902() {
		try {
			String playerName = Minecraft.getMinecraft().getSession().getUsername();
			this.isYuma140902 = "yuma140902".equals(playerName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isYuma140902() {
		return isYuma140902;
	}
}
