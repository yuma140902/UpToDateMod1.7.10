package yuma140902.uptodatemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.biome.BiomeGenBase;
import yuma140902.uptodatemod.blocks.BlockNewFlower;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.world.generation.MyMinableGenerator;
import yuma140902.uptodatemod.world.generation.UpToDateWorldGenerator;
import yuma140902.uptodatemod.world.generation.biome.FlowerGenerator;
import yuma140902.uptodatemod.world.generation.biome.SweetBerryGenerator;
import yuma140902.yumalib.api.world.gen.biome.BiomeDecorators;

public final class WorldGenerators {
	private WorldGenerators() {}
	
	private static final int WORLD_GENERATOR_PRIORITY = 100; //小さいほど優先度が高い
	
	public static void register() {
		GameRegistry.registerWorldGenerator(myMinableGenerator, WORLD_GENERATOR_PRIORITY);
		GameRegistry.registerWorldGenerator(uptodateWorldGenerator, 0);
		if(EnumDisableableFeatures.sweetBerry.featureEnabled()) BiomeDecorators.register(new SweetBerryGenerator());
		if(EnumDisableableFeatures.flower.featureEnabled()) {
			BiomeDecorators.register(new FlowerGenerator());
			// 骨粉を撒いたときに追加の花が生成されるようにする
			BiomeGenBase.plains.addFlower(MyBlocks.flower, BlockNewFlower.CORNFLOWER, 3);
			BiomeGenBase.getBiome(BiomeGenBase.forest.biomeID + 128).addFlower(MyBlocks.flower, BlockNewFlower.LILY, 10);  // forest.biomeID+128 は Flower Forest バイオーム
		}
	}
	
	public static final MyMinableGenerator myMinableGenerator = new MyMinableGenerator();
	public static final UpToDateWorldGenerator uptodateWorldGenerator = new UpToDateWorldGenerator();
}
