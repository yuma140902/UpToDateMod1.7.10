package yuma140902.yumalib.api.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPos {
	public BlockPos(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private final int x;
	private final int y;
	private final int z;
	
	public int x() {return x;}
	public int y() {return y;}
	public int z() {return z;}
	
	public BlockPos offset(ForgeDirection direction) {
		return offset(direction, 1);
	}
	
	public BlockPos offset(ForgeDirection direction, int distance) {
		int x = this.x + direction.offsetX*distance;
		int y = this.y + direction.offsetY*distance;
		int z = this.z + direction.offsetZ*distance;
		return new BlockPos(x, y, z);
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("X", x);
		tag.setInteger("Y", y);
		tag.setInteger("Z", z);
	}
	
	public static BlockPos readFromNBT(NBTTagCompound tag) {
		int x = tag.getInteger("X");
		int y = tag.getInteger("Y");
		int z = tag.getInteger("Z");
		return new BlockPos(x, y, z);
	}
}
