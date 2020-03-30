package yuma140902.yumalib.api.util;

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
}
