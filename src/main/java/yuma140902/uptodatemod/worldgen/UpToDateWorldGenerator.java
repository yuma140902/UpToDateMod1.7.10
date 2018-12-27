package yuma140902.uptodatemod.worldgen;

import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import yuma140902.uptodatemod.MyBlocks;

public class UpToDateWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(
			Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 1) generateCoarseDirt(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
	}
	
	private void generateCoarseDirt(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int heightMax = world.getActualHeight();
		for(int x = chunkX * 16; x < chunkX * 16 + 16; ++x) {
			for(int z = chunkZ * 16; z < chunkZ * 16 + 16; ++z) {
				for(int y = 0; y < heightMax; ++y) {
					if(world.getBlock(x, y, z) == Blocks.dirt && world.getBlockMetadata(x, y, z) == 1) { //メタデータ1の土を粗い土に置き換え
						world.setBlock(x, y, z, MyBlocks.coarseDirt);
					}
				}
			}
		}
	}
}
