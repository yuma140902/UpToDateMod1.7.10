package yuma140902.yumalib.api.util

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item

object GameRegistryEx {
  def registerBlock(block: Block, name: Name): Block =
    GameRegistry.registerBlock(block, name.str)

  def registerItem(item: Item, name: Name): Unit =
    GameRegistry.registerItem(item, name.str)
}
