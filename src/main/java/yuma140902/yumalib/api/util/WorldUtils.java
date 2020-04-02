package yuma140902.yumalib.api.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldUtils {
	public static Block getBlock(World world, BlockPos pos) {
		return world.getBlock(pos.x(), pos.y(), pos.z());
	}
	
	public static boolean isAir(World world, BlockPos pos) {
		return world.isAirBlock(pos.x(), pos.y(), pos.z());
	}
}
