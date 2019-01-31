package yuma140902.uptodatemod.world.generation;

import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.chunk.IChunkProvider;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.util.ListUtils;

public class UpToDateWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(
			Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(!ListUtils.contains(ModConfigCore.worldGen_genCoarseDirt_blackList, world.provider.dimensionId)) {
			if(ModConfigCore.worldGen_genCoarseDirt) generateCoarseDirt(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		if(!ListUtils.contains(ModConfigCore.worldGen_genFossiles_blackList, world.provider.dimensionId)) {
			if(ModConfigCore.worldGen_genFossiles) generateFossile(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	private void generateCoarseDirt(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int heightMax = world.getActualHeight();
		for(int x = chunkX * 16; x < chunkX * 16 + 16; ++x) {
			for(int z = chunkZ * 16; z < chunkZ * 16 + 16; ++z) {
				for(int y = 0; y < heightMax; ++y) {
					if(world.getBlock(x, y, z) == Blocks.dirt && world.getBlockMetadata(x, y, z) == 1) { //メタデータ1の土を粗い土に置き換え
						world.setBlock(x, y, z, MyBlocks.coarseDirt, 0, 2);
					}
				}
			}
		}
	}
	
	private void generateFossile(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(random.nextInt(64) != 0) return;
		BiomeGenBase biome = world.provider.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
		if(biome instanceof BiomeGenDesert || biome instanceof BiomeGenSwamp) {
			int x = chunkX * 16 + random.nextInt(16);
			int y;
			int z = chunkZ * 16 + random.nextInt(16);
			
			for(y = 255; y >= 0; --y) {
				if(world.isAirBlock(x, y, z)) {
					continue;
				}else {
					break;
				}
			}
			
			int fossileType = random.nextInt(8);
			Fossiles fossile = Fossiles.getFossileByType(fossileType);
			fossile.spawnAt(world, x, y - random.nextInt(20), z, random);
		}
	}
}
