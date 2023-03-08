package yuma140902.uptodatemod.world.generation.biome

import yuma140902.uptodatemod.MyBlocks
import yuma140902.yumalib.api.world.gen.biome.BiomeDecorator

import net.minecraft.world.World
import net.minecraft.world.biome.{BiomeGenBase, BiomeGenTaiga}
import net.minecraftforge.common.util.ForgeDirection

import java.util.Random

object SweetBerryGenerator extends BiomeDecorator {
  override def tryDecorate(world: World, biome: BiomeGenBase, chunkX: Int, chunkZ: Int, random: Random): Unit = {
    if (!biome.isInstanceOf[BiomeGenTaiga]) return
    if (random.nextInt(16) != 0) return

    val isCold = (biome eq BiomeGenBase.coldTaiga) || (biome eq BiomeGenBase.coldTaigaHills)
    val trial = if (isCold) 10 + random.nextInt(6)
    else 3 + random.nextInt(4)
    for (_ <- 0 until trial) {
      val x = chunkX + 8 + random.nextInt(16)
      val z = chunkZ + 8 + random.nextInt(16)
      placeOneBush(world, x, z, random)
    }
  }

  private def placeOneBush(world: World, x: Int, z: Int, random: Random): Unit = {
    val y = world.getHeightValue(x, z)
    if (world.getBlock(x, y - 1, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, MyBlocks.sweetBerryBush)) {
      val meta = 2 + random.nextInt(2)
      world.setBlock(x, y, z, MyBlocks.sweetBerryBush, meta, 2)
    }
  }

}
