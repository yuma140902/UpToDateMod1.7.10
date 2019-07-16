package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import yuma140902.uptodatemod.registry.EnumNoteBlockInstrument;

public class NoteBlockPlayHandler implements IMessageHandler<NoteBlockPlayMessage, IMessage> {

	@Override
	public IMessage onMessage(NoteBlockPlayMessage message, MessageContext ctx) {
		int dimId = message.dimId;
		int x = message.x;
		int y = message.y;
		int z = message.z;
		EnumNoteBlockInstrument instrument = message.instrument;
		int noteId = message.noteId;
		
		WorldServer worldServer = DimensionManager.getWorld(dimId);
		String soundName = instrument.getSoundName();
		
		float f = (float)Math.pow(2.0D, (double)(noteId - 12) / 12.0D);
		worldServer.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, soundName, 3.0F, f);
		Minecraft.getMinecraft().renderGlobal.spawnParticle("note", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, (double)noteId / 24.0D, 0.0D, 0.0D);
		
		return null;
	}
	
}
