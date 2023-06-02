package yuma140902.yumalib.proxy;

import yuma140902.yumalib.event_handlers.YLCommonEventHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class YLCommonProxy {
    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(YLCommonEventHandler.INSTANCE);
        FMLCommonHandler.instance().bus().register(YLCommonEventHandler.INSTANCE);
    }

    public void checkIfIsYuma140902() {
    }

    public boolean isYuma140902() {
        return false;
    }

    public int newUniqueRenderId() {
        return -1;
    }

    public void registerRenderers() {
    }
}
