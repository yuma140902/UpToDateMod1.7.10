package yuma140902.yumalib.api.blocks.mixins

import yuma140902.yumalib.ModYumaLib
import yuma140902.yumalib.api.model.YLBlockModel

import net.minecraft.block.Block
import net.minecraft.world.IBlockAccess

trait BlockWithYLBlockModel extends Block {
  override def getRenderType: Int = ModYumaLib.ylBlockModelRenderId

  // TODO: 不要かもしれない
  override def renderAsNormalBlock(): Boolean = false

  def getYLBlockModel(world: IBlockAccess, x: Int, y: Int, z: Int): YLBlockModel
}
