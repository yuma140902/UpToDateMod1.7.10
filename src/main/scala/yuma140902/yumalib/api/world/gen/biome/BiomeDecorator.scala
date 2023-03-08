package yuma140902.yumalib.api.world.gen.biome

import net.minecraft.world.World
import net.minecraft.world.biome.BiomeGenBase

import java.util.Random

/**
 * バイオームを装飾するインターフェース
 * <p>DecorateBiomeEvent.Preで呼び出される</p>
 */
trait BiomeDecorator {
  def tryDecorate(world: World, biome: BiomeGenBase, chunkX: Int, chunkZ: Int, random: Random): Unit
}
