package yuma140902.uptodatemod.world.generation.biome;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.mcmodlib.world.gen.biome.IBiomeDecorator;
import yuma140902.uptodatemod.MyBlocks;

public class SweetBerryGenerator implements IBiomeDecorator {

	@Override
	public void tryDecorate(World world, BiomeGenBase biome, int chunkX, int chunkZ, Random random) {
		if(!(biome instanceof BiomeGenTaiga)) {
			return;
		}
		
		if(random.nextInt(16) != 0) {
			return;
		}
		
		boolean isCold = biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.coldTaigaHills;
		int trial = isCold ? 10 + random.nextInt(6) : 3 + random.nextInt(4);
		
		for(int i=0; i < trial; ++i) {
			int x = chunkX + 8 + random.nextInt(16);
			int z = chunkZ + 8 + random.nextInt(16);
			placeOneBush(world, x, z, random);
		}
	}
	
	private void placeOneBush(World world, int x, int z, Random random) {
		int y = world.getHeightValue(x, z);
		if(world.getBlock(x, y-1, z).canSustainPlant(world, x, y, z, ForgeDirection.UP,MyBlocks.sweetBerryBush)) {
			int meta = 2 + random.nextInt(2);
			world.setBlock(x, y, z, MyBlocks.sweetBerryBush, meta, 2);
		}
	}
	
}
