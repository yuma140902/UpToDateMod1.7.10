package yuma140902.yumalib_ee.event_handlers;

import java.util.Random;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import yuma140902.yumalib_ee.YumaLibConstants;
import yuma140902.yumalib_ee.api.world.gen.biome.BiomeDecorators;
import yuma140902.yumalib_ee.config.YumaLibConfigCore;

public class CommonEventHandler {
private CommonEventHandler() {}
	
	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	
	
	@SubscribeEvent
	public void onBiomeDecoration(DecorateBiomeEvent.Pre event) {
		World world = event.world;
		int chunkX = event.chunkX;
		int chunkZ = event.chunkZ;
		Random random = event.rand;
		
		if(world == null || random == null) {
			return;
		}
		BiomeDecorators.decorate(world, chunkX, chunkZ, random);
	}
	
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(YumaLibConstants.MOD_ID.equals(event.modID))
			YumaLibConfigCore.syncConfig();
	}
}
