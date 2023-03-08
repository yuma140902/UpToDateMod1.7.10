package yuma140902.yumalib.proxy;

import yuma140902.yumalib.event_handlers.YLClientEventHandler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class YLClientProxy extends YLCommonProxy {
    private boolean isYuma140902 = false;

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(YLClientEventHandler.INSTANCE);
    }

    @Override
    public void checkIfIsYuma140902() {
        try {
            String playerName = Minecraft.getMinecraft().getSession().getUsername();
            this.isYuma140902 = "yuma140902".equals(playerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isYuma140902() {
        return isYuma140902;
    }

    @Override
    public int newUniqueRenderId() {
        return RenderingRegistry.getNextAvailableRenderId();
    }
}
