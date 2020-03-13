package yuma140902.uptodatemod.world.generation.biome;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;
import yuma140902.mcmodlib.world.gen.biome.IBiomeDecorator;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockNewFlower;

public class FlowerGenerator implements IBiomeDecorator {

	@Override
	public void tryDecorate(World world, BiomeGenBase biome, int chunkX, int chunkZ, Random random) {
		if(biome instanceof BiomeGenPlains) {
			if(random.nextInt(16) == 0)
				plantGroupOfFlower(world, biome, chunkX, chunkZ, random, 24, BlockNewFlower.CORNFLOWER);
		}
		else if(biome.biomeID == BiomeGenBase.forest.biomeID+128) {  // Flower Forest バイオーム
			if(random.nextInt(2) == 0)
				plantGroupOfFlower(world, biome, chunkX, chunkZ, random, 64, BlockNewFlower.LILY);
		}
	}
	
	public void plantGroupOfFlower(World world, BiomeGenBase biome, int chunkX, int chunkZ, Random random, int size, int flowerMeta) {
		for (int i = 0; i < size; ++i) {
			int x = chunkX + 8 + random.nextInt(16);
			int z = chunkZ + 8 + random.nextInt(16);
			int y = world.getHeightValue(x, z) + random.nextInt(4) - random.nextInt(4);
			
			if (world.isAirBlock(x, y, z) && (!world.provider.hasNoSky || y < 255) && MyBlocks.flower.canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, MyBlocks.flower, flowerMeta, 2);
			}
    }
	}
	
}
