package yuma140902.yumalib.api.util

import yuma140902.yumalib.api.McConst

import net.minecraft.block.{Block, BlockSlab, BlockStairs}
import net.minecraft.init.Blocks
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection

object WorldExtensions {
  implicit class WorldEx(val self: World) {
    def setMeta(pos: BlockPos, meta: Int, flag: SetBlockFlag = SetBlockFlag.NORMAL): Unit =
      self.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, meta, flag.toInt)

    /**
     * ブロックが当たり判定を持たないかどうかを判定する
     *
     * @return 当たり判定を持たないならtrue、持つならfalse
     */
    def hasNoCollisionBox(pos: BlockPos): Boolean = {
      self.getBlock(pos).map(block =>
        Option(block.getCollisionBoundingBoxFromPool(self, pos.x, pos.y, pos.z)).isEmpty
      ).getOrElse(true)
    }
  }

  implicit class BlockAccessEx(val self: IBlockAccess) {

    def isAir(pos: BlockPos): Boolean =
      self.isAirBlock(pos.x, pos.y, pos.z)

    // デフォルトの{@link World#isSideSolid()}だと下置きハーフブロックの下面の判定などができなかったため作成
    def isSideSolid(pos: BlockPos, direction: ForgeDirection, default: Boolean): Boolean = {
      val block = self.getBlock(pos).getOrElse(() -> Blocks.air)
      val meta = self.getMeta(pos)
      // 下置きハーフブロック
      if ((direction eq ForgeDirection.DOWN) && block.isInstanceOf[BlockSlab]) (McConst.Meta.IS_SLAB_UPPER & meta) != McConst.Meta.IS_SLAB_UPPER
      // 階段
      else if ((direction eq ForgeDirection.DOWN) && block.isInstanceOf[BlockStairs]) (McConst.Meta.IS_STAIRS_UPSIDE_DOWN & meta) != McConst.Meta.IS_STAIRS_UPSIDE_DOWN
      else self.isSideSolid(pos.x, pos.y, pos.z, direction, default)
    }

    def getMeta(pos: BlockPos): Int =
      self.getBlockMetadata(pos.x, pos.y, pos.z)

    def getBlock(pos: BlockPos): Option[Block] =
      Option(self.getBlock(pos.x, pos.y, pos.z))

  }
}

case class SetBlockFlag(blockUpdate: Boolean, clientUpdate: Boolean, preventReRendering: Boolean) {
  def toInt: Int =
    (if (blockUpdate) 0x1 else 0) | (if (clientUpdate) 0x2 else 0) | (if (preventReRendering) 0x4 else 0)
}

object SetBlockFlag {
  val NORMAL: SetBlockFlag = SetBlockFlag(blockUpdate = true, clientUpdate = true, preventReRendering = false)
}