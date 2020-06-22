package yuma140902.uptodatemod.world.generation.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class UpToDateModBiomeManager {
	private static int nextBiomeId = 40;
	
	public static int getNextFreeBiomeId() {
		for (int i = nextBiomeId; i < 256; i++)
		{
			if (BiomeGenBase.getBiomeGenArray()[i] != null)
			{
				if (i == 255) throw new IllegalArgumentException("There are no more biome ids available!");
				continue;
			}
			else
			{
				nextBiomeId = i + 1;
				return i;
			}
		}
		
		return -1;
	}
}
