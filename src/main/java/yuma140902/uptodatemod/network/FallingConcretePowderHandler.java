package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import yuma140902.uptodatemod.entity.item.EntityFallingConcretePowderBlock;

public class FallingConcretePowderHandler implements IMessageHandler<FallingConcretePowderMessage, IMessage> {

	@Override
	public IMessage onMessage(FallingConcretePowderMessage message, MessageContext ctx) {
		World world = DimensionManager.getWorld(message.dimId);
		EntityFallingConcretePowderBlock entity = (EntityFallingConcretePowderBlock)world.getEntityByID(message.entityId);
		
		if(entity != null)
			entity.setMetadata(message.meta);
		return null;
	}
	
}
