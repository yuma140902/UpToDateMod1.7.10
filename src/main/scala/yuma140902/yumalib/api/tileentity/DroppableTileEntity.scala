package yuma140902.yumalib.api.tileentity

import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity

/** 破壊時に中身をドロップすべきTileEntity */
trait DroppableTileEntity extends TileEntity {
  def itemStacksToDrop(): Seq[ItemStack]
}
