package yuma140902.uptodatemod.worldgen;

import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGeneratorBase extends WorldGenerator implements IWorldGenerator {
	@Override
	public abstract void generate(
			Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		//Do nothing
		return true;
	}
	
	//ユーティリティ: ブロックを設置
	protected void setBlockAt(World world, Random rand, Block block, int x, int y, int z) {
		setBlockAt(world, rand, block, x, y, z, 0);
	}
	
	//ユーティリティ: ブロックを設置
	protected void setBlockAt(World world, Random rand, Block block, int x, int y, int z, int meta) {
		this.setBlockAndNotifyAdequately(world, x, y, z, block, meta); //これの第6引数はデータ型(メタデータ)
	}
}
