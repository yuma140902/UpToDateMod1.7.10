package yuma140902.uptodatemod.world.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import yuma140902.uptodatemod.util.ListUtils;
import yuma140902.uptodatemod.util.Pair;

public class MyMinableGenerator implements IWorldGenerator {
	public static class Config {
		public Config(boolean enabled, int spawnSize, int spawnTries, int minHeight, int maxHeight) {
			this(enabled, spawnSize, spawnTries, minHeight, maxHeight, new int[] {-1, 1});
		}
		
		public Config(boolean enabled, int spawnSize, int spawnTries, int minHeight, int maxHeight, int[] dimansionIDBlockList) {
			this.enabled = enabled;
			this.spawnSize = spawnSize;
			this.spawnTries = spawnTries;
			this.minHeight = minHeight;
			this.maxHeight = maxHeight;
			this.dimensionIDBlackList = dimansionIDBlockList;
		}
		
		public boolean enabled = true;
		public int spawnSize;
		public int spawnTries;
		public int minHeight;
		public int maxHeight;
		private int heightDiff; // maxHeight - minHeight + 1; 自動で計算される
		public int[] dimensionIDBlackList;
	}
	
	public MyMinableGenerator() {	}

	private List<Pair<WorldGenMinable, Config>> _worldGenMinabes = new ArrayList<Pair<WorldGenMinable, Config>>();
	
	public void addOreGenerator(Block ore, Config config) {
		addOreGenerator(ore, 0, config);
	}
	
	public void addOreGenerator(Block ore, int meta, Config config) {
		if(!config.enabled) return;
		
		if (config.minHeight < 0 || config.maxHeight > 256 || config.minHeight > config.maxHeight)
      throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		config.heightDiff = config.maxHeight - config.minHeight + 1;
		
		_worldGenMinabes.add(new Pair<WorldGenMinable, Config>(new WorldGenMinable(ore, meta, config.spawnSize, Blocks.stone), config));
	}

	
	private void runGenerator(WorldGenerator generator, Config config, World world, Random rand, int chunk_X, int chunk_Z) {
		for(int i = 0; i < config.spawnTries; ++i) {
			int x = chunk_X * 16  + rand.nextInt(16);
			int y = config.minHeight + rand.nextInt(config.heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, x, y, z);
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (Pair<WorldGenMinable, Config> pair : _worldGenMinabes) {
			WorldGenMinable generator = pair.getLeft();
			Config config = pair.getRight();
			
			if(!ListUtils.contains(config.dimensionIDBlackList, world.provider.dimensionId))
				runGenerator(generator, config, world, random, chunkX, chunkZ);
		}
	}
}
