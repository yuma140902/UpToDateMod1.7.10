package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import yuma140902.uptodatemod.entity.item.EntityArmorStand;

public class ArmorStandInteractHandler implements IMessageHandler<ArmorStandInteractMessage, IMessage> {
	
	@Override
	public IMessage onMessage(ArmorStandInteractMessage message, MessageContext ctx) {
		World world = DimensionManager.getWorld(message.dimID);
		EntityArmorStand stand = (EntityArmorStand) world.getEntityByID(message.standID);
		EntityPlayer player = (EntityPlayer) world.getEntityByID(message.playerID);
		
		stand.interact(player, message.hitPos);
		return null;
	}
}
