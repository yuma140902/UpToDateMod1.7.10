package yuma140902.yumalib.api.blocks.mixins

import yuma140902.yumalib.api.tileentity.DroppableTileEntity
import yuma140902.yumalib.api.util.BlockPos

import net.minecraft.block.Block
import net.minecraft.entity.item.EntityItem
import net.minecraft.world.World

trait DroppableTileEntityProvider[T <: DroppableTileEntity] extends TileEntityProvider[T] {
  override def breakBlock(world: World, x: Int, y: Int, z: Int, block: Block, meta: Int): Unit = {
    val tileEntity = getTileEntity(world, BlockPos(x, y, z))
    val itemStacks = tileEntity match {
      case Some(tileEntity) => tileEntity.itemStacksToDrop()
      case _ => Seq()
    }
    itemStacks.foreach(itemStack => {
      if (!world.isRemote) {
        val rand = world.rand
        val xDiff = rand.nextFloat * 0.6F + 0.1F
        val yDiff = rand.nextFloat * 0.6F + 0.1F
        val zDiff = rand.nextFloat * 0.6F + 0.1F
        val entityItem = new EntityItem(world, x + xDiff, y + yDiff, z + zDiff, itemStack.copy)
        val motionScale = 0.025F
        entityItem.motionX = rand.nextGaussian.toFloat * motionScale
        entityItem.motionY = rand.nextGaussian.toFloat * motionScale + 0.1F
        entityItem.motionZ = rand.nextGaussian.toFloat * motionScale
        world.spawnEntityInWorld(entityItem)
      }
    })
    super.breakBlock(world, x, y, z, block, meta)
  }
}
