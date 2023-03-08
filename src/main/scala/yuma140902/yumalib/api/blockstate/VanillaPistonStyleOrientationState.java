package yuma140902.yumalib.api.blockstate;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * {@link net.minecraft.block.BlockPistonBase}と同じ仕様のブロックの向き
 */
public class VanillaPistonStyleOrientationState implements IBlockState {
	
	protected int meta;
	
	public VanillaPistonStyleOrientationState(World world, int x, int y, int z, EntityLivingBase placer){
		this.meta = BlockPistonBase.determineOrientation(world, x, y, z, placer);
	}
	
	@Override
	public int metadata() {
		return this.meta;
	}
}
