package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;

public class SpawnFertilizingParticleMessage implements IMessage {

	public int dimensionId;
	public int x, y, z;
	
	public SpawnFertilizingParticleMessage() {}
	
	public SpawnFertilizingParticleMessage(World world, int x, int y, int z) {
		this(world.provider.dimensionId, x, y, z);
	}
	
	public SpawnFertilizingParticleMessage(int dimId, int x, int y, int z) {
		this.dimensionId = dimId;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		dimensionId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dimensionId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
}
