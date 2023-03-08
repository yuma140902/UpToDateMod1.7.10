package yuma140902.uptodatemod.world.generation;

import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.chunk.IChunkProvider;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.ListUtils;

public class UpToDateWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(
			Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(!ListUtils.contains(ModConfigCore.WorldGen.coarseDirtBlackList(), world.provider.dimensionId)) {
			if(ModConfigCore.WorldGen.genCoarseDirt() && EnumDisableableFeatures.coarseDirt.featureEnabled())
				generateCoarseDirt(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		if(!ListUtils.contains(ModConfigCore.WorldGen.deepslateBlackList(), world.provider.dimensionId)) {
			if(ModConfigCore.WorldGen.genDeepslate() && EnumDisableableFeatures.deepslateStone.featureEnabled())
				generateDeepslateOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		if(!ListUtils.contains(ModConfigCore.WorldGen.fossilesBlackList(), world.provider.dimensionId)) {
			if(ModConfigCore.WorldGen.genFossiles() && EnumDisableableFeatures.boneBlockAndFossile.featureEnabled())
				generateFossile(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
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

	private void generateDeepslateOres(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for(int x = chunkX * 16; x < chunkX * 16 + 16; ++x) {
			for(int z = chunkZ * 16; z < chunkZ * 16 + 16; ++z) {
				for(int y = 0; y <= 16; ++y) {
					Block block = world.getBlock(x, y, z);
					if(block == Blocks.coal_ore) world.setBlock(x, y, z, MyBlocks.deepslateCoalOre, 0, 2);
					else if(block == Blocks.diamond_ore) world.setBlock(x, y, z, MyBlocks.deepslateDiamondOre, 0, 2);
					else if(block == Blocks.emerald_ore) world.setBlock(x, y, z, MyBlocks.deepslateEmeraldOre, 0, 2);
					else if(block == Blocks.gold_ore) world.setBlock(x, y, z, MyBlocks.deepslateGoldOre, 0, 2);
					else if(block == Blocks.iron_ore) world.setBlock(x, y, z, MyBlocks.deepslateIronOre, 0, 2);
					else if(block == Blocks.lapis_ore) world.setBlock(x, y, z, MyBlocks.deepslateLapisOre, 0, 2);
					else if(block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore)
						world.setBlock(x, y, z, MyBlocks.deepslateRedstoneOre, 0, 2);
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
