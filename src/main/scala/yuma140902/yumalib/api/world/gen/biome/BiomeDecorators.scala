package yuma140902.yumalib.api.world.gen.biome

import net.minecraft.world.World

import java.util.Random
import scala.collection.mutable

// TODO: registryへ移動

/**
 * [[BiomeDecorator]]のレジストリ
 */
object BiomeDecorators {
  private val decorators: mutable.ArrayStack[BiomeDecorator] = new mutable.ArrayStack[BiomeDecorator]()

  def register(decorator: BiomeDecorator): Unit = decorators.push(decorator)

  def decorate(world: World, chunkX: Int, chunkZ: Int, random: Random): Unit = {
    val biome = Option(world.getBiomeGenForCoords(chunkX + 8, chunkZ + 8))
    biome match {
      case None =>
      case Some(biome) =>
        decorators.foreach(decorator => decorator.tryDecorate(world, biome, chunkX, chunkZ, random))
    }
  }
}
