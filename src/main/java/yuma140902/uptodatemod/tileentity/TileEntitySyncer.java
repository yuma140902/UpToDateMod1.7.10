package yuma140902.uptodatemod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public final class TileEntitySyncer {
	private TileEntitySyncer() {}
	
	public static Packet getDescriptionPacket(TileEntity tileentity) {
		NBTTagCompound tagCompound = new NBTTagCompound();
		tileentity.writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, 1, tagCompound);
	}
	
	public static void onDataPacket(TileEntity tileentity, NetworkManager net, S35PacketUpdateTileEntity pkt) {
		tileentity.readFromNBT(pkt.func_148857_g());
	}
}
