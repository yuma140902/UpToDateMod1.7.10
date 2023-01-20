package yuma140902.yumalib.api.world.gen.biome;

import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * バイオームを装飾するインターフェース
 * <p>DecorateBiomeEvent.Preで呼び出される</p>
 */
public interface IBiomeDecorator {
	void tryDecorate(@Nonnull World world, @Nonnull BiomeGenBase biome, int chunkX, int chunkZ, @Nonnull Random random);
}
