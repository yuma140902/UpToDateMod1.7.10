package yuma140902.yumalib.api.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * ブロックの状態を表す。ブロックとメタデータの組み合わせ。
 * @author yuma1
 */
public class BlockState {
	@Nonnull
	public final Block block;
	public final int meta;
	
	public BlockState(@Nonnull Block block, int meta) {
		this.block = block;
		this.meta = meta;
	}
	
	@Nullable
	public static BlockState fromCoord(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		if(block == null) return null;
		int meta = world.getBlockMetadata(x, y, z);
		return new BlockState(block, meta);
	}
	
	@Nullable
	public static BlockState fromPos(World world, @Nonnull BlockPos pos) {
		Block block = WorldUtils.getBlock(world, pos);
		if(block == null) return null;
		int meta = WorldUtils.getMeta(world, pos);
		return new BlockState(block, meta);
	}
}
