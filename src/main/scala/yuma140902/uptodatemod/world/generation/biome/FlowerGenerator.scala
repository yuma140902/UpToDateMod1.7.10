package yuma140902.uptodatemod.world.generation.biome

import yuma140902.uptodatemod.MyBlocks
import yuma140902.uptodatemod.blocks.BlockNewFlower
import yuma140902.yumalib.api.world.gen.biome.BiomeDecorator

import net.minecraft.world.World
import net.minecraft.world.biome.{BiomeGenBase, BiomeGenPlains}

import java.util.Random

object FlowerGenerator extends BiomeDecorator {
  override def tryDecorate(world: World, biome: BiomeGenBase, chunkX: Int, chunkZ: Int, random: Random): Unit = {
    if (biome.isInstanceOf[BiomeGenPlains]) {
      if (random.nextInt(16) == 0) plantGroupOfFlower(world, biome, chunkX, chunkZ, random, 24, BlockNewFlower.CORNFLOWER)
    }
    else if (biome.biomeID == BiomeGenBase.forest.biomeID + 128) { // Flower Forest バイオーム
      if (random.nextInt(2) == 0) plantGroupOfFlower(world, biome, chunkX, chunkZ, random, 64, BlockNewFlower.LILY)
    }
  }

  private def plantGroupOfFlower(world: World, biome: BiomeGenBase, chunkX: Int, chunkZ: Int, random: Random, size: Int, flowerMeta: Int): Unit = {
    for (_ <- 0 until size) {
      val x = chunkX + 8 + random.nextInt(16)
      val z = chunkZ + 8 + random.nextInt(16)
      val y = world.getHeightValue(x, z) + random.nextInt(4) - random.nextInt(4)
      if (world.isAirBlock(x, y, z) && (!world.provider.hasNoSky || y < 255) && MyBlocks.flower.canBlockStay(world, x, y, z)) world.setBlock(x, y, z, MyBlocks.flower, flowerMeta, 2)
    }
  }


}
