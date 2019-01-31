package yuma140902.uptodatemod;

import cpw.mods.fml.common.registry.GameRegistry;
import yuma140902.uptodatemod.world.generation.MyMinableGenerator;
import yuma140902.uptodatemod.world.generation.UpToDateWorldGenerator;

public final class WorldGenerators {
	private WorldGenerators() {}
	
	private static final int WORLD_GENERATOR_PRIORITY = 100; //小さいほど優先度が高い
	
	public static void register() {
		GameRegistry.registerWorldGenerator(myMinableGenerator, WORLD_GENERATOR_PRIORITY);
		GameRegistry.registerWorldGenerator(uptodateWorldGenerator, 0);
	}
	
	public static final MyMinableGenerator myMinableGenerator = new MyMinableGenerator();
	public static final UpToDateWorldGenerator uptodateWorldGenerator = new UpToDateWorldGenerator();
}
