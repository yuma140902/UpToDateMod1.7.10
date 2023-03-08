package yuma140902.yumalib.api.util

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemBlock}

object GameRegistryEx {
  def registerBlock[T <: ItemBlock](block: Block, name: Name, cls: Class[T] = classOf[ItemBlock]): Block =
    GameRegistry.registerBlock(block, cls, name.str)

  def registerItem(item: Item, name: Name): Unit =
    GameRegistry.registerItem(item, name.str)
}
