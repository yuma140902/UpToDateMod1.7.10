package yuma140902.yumalib.api.util

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

case class BlockPos(x: Int, y: Int, z: Int) {
  def offset(direction: ForgeDirection, distance: Int = 1): BlockPos =
    BlockPos(
      this.x + direction.offsetX * distance,
      this.y + direction.offsetY * distance,
      this.z + direction.offsetZ * distance
    )

  def writeToNBT(tag: NBTTagCompound): Unit = {
    tag.setInteger("X", x);
    tag.setInteger("Y", y);
    tag.setInteger("Z", z);
  }
}

object BlockPos {
  def readFromNBT(tag: NBTTagCompound): BlockPos = {
    val x = tag.getInteger("X")
    val y = tag.getInteger("Y")
    val z = tag.getInteger("Z")
    BlockPos(x, y, z)
  }
}