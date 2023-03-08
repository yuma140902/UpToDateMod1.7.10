package yuma140902.yumalib.api.blocks.mixins

import yuma140902.yumalib.api.util.BlockPos

import net.minecraft.block.{Block, ITileEntityProvider}
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

trait TileEntityProvider[T <: TileEntity] extends Block with ITileEntityProvider {

  /**
   * worldからTileEntityを得る
   */
  def getTileEntity(world: World, pos: BlockPos): Option[T] = {
    Option(world.getTileEntity(pos.x, pos.y, pos.z)).map(_.asInstanceOf[T])
  }
}
