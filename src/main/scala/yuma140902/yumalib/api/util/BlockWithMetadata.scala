package yuma140902.yumalib.api.util

import net.minecraft.block.Block
import net.minecraft.world.World

case class BlockWithMetadata(block: Block, meta: Int)

object BlockWithMetadata {
  def fromCoordinate(world: World, pos: BlockPos): Option[BlockWithMetadata] = {
    val block = Option(world.getBlock(pos.x, pos.y, pos.z));
    block match {
      case None => None
      case Some(block) => {
        val meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
        Some(BlockWithMetadata(block, meta))
      }
    }
  }
}