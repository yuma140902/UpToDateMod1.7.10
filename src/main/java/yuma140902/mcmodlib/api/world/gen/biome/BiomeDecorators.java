package yuma140902.mcmodlib.api.world.gen.biome;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDecorators {
	
	@Nonnull
	private final static List<IBiomeDecorator> decorators = new LinkedList<IBiomeDecorator>();
	
	public static void register(@Nonnull IBiomeDecorator decorator) {
		decorators.add(decorator);
	}
	
	
	public static void decorate(@Nonnull World world, int chunkX, int chunkZ, @Nonnull Random random) {
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX+8, chunkZ+8);
		if(biome == null) {
			return;
		}
		
		Iterator<IBiomeDecorator> iterator = decorators.iterator();
		while (iterator.hasNext()) {
			IBiomeDecorator decorator = iterator.next();
			decorator.tryDecorate(world, biome, chunkX, chunkZ, random);
		}
	}
	
	
}
