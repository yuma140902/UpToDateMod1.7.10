package yuma140902.yumalib.api.world.gen.biome;

import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBiomeDecorator {
	void tryDecorate(@Nonnull World world, @Nonnull BiomeGenBase biome, int chunkX, int chunkZ, @Nonnull Random random);
}
