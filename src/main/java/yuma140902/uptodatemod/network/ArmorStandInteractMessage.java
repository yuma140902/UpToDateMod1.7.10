package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import yuma140902.uptodatemod.entity.item.EntityArmorStand;

public class ArmorStandInteractMessage implements IMessage {

	public int dimID, standID, playerID;
	public Vec3 hitPos;

	public ArmorStandInteractMessage() {
	}

	public ArmorStandInteractMessage(int dimID, EntityArmorStand stand, EntityPlayer player) {
		this.dimID = dimID;
		standID = stand.getEntityId();
		playerID = player.getEntityId();
		MovingObjectPosition hit = Minecraft.getMinecraft().objectMouseOver;
		hitPos = Vec3.createVectorHelper(hit.hitVec.xCoord - stand.posX, hit.hitVec.yCoord - stand.posY, hit.hitVec.zCoord - stand.posZ);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dimID = buf.readInt();
		standID = buf.readInt();
		playerID = buf.readInt();
		hitPos = Vec3.createVectorHelper(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dimID);
		buf.writeInt(standID);
		buf.writeInt(playerID);
		buf.writeDouble(hitPos.xCoord);
		buf.writeDouble(hitPos.yCoord);
		buf.writeDouble(hitPos.zCoord);
	}
}