package yuma140902.yumalib.api.blocks.mixins

import yuma140902.yumalib.api.blockstate.VanillaPistonStyleOrientationState
import yuma140902.yumalib.api.util.WorldExtensions._
import yuma140902.yumalib.api.util.{BlockPos, SetBlockFlag}

import net.minecraft.block.Block
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * [[net.minecraft.block.BlockPistonBase]]と同じ仕様で、ブロック設置時のプレイヤーの向きによってブロックの向きが変わる
 */
trait VanillaPistonStyleOrientation extends Block {
  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, player: EntityLivingBase, itemStack: ItemStack): Unit = {
    val state = new VanillaPistonStyleOrientationState(world, x, y, z, player)
    world.setMeta(BlockPos(x, y, z), state.metadata, SetBlockFlag(blockUpdate = false, clientUpdate = true, preventReRendering = false))
  }
}
