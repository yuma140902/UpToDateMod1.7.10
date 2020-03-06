package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemDye;
import net.minecraft.world.World;

public class SpawnFertilizingParticleHandler implements IMessageHandler<SpawnFertilizingParticleMessage, IMessage> {

	@Override
	public IMessage onMessage(SpawnFertilizingParticleMessage message, MessageContext ctx) {
		if(Minecraft.getMinecraft().theWorld.provider.dimensionId != message.dimensionId) {
			return null;
		}
		World world = Minecraft.getMinecraft().theWorld;
		int x = message.x;
		int y = message.y;
		int z = message.z;
		
		ItemDye.func_150918_a(world, x, y, z, 6);
		
		return null;
	}
	
	
}
