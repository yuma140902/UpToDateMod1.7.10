package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import yuma140902.uptodatemod.entity.item.EntityFallingConcretePowderBlock;

public class FallingConcretePowderMessage implements IMessage {

	public int meta;
	public int entityId;
	public int dimId;
	
	public FallingConcretePowderMessage() {}
	
	public FallingConcretePowderMessage(int meta, EntityFallingConcretePowderBlock entity) {
		this.meta = meta;
		this.entityId = entity.getEntityId();
		this.dimId = entity.worldObj.provider.dimensionId;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		meta = buf.readInt();
		entityId = buf.readInt();
		dimId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(meta);
		buf.writeInt(entityId);
		buf.writeInt(dimId);
	}
	
}
