package yuma140902.yumalib.api.util

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class ItemWithMetadata(item: Item, meta: Int) {
  def newItemStack: ItemStack = new ItemStack(item, 1, meta)
}

object ItemWithMetadata {
  def fromBlock(block: Block, meta: Int): Option[ItemWithMetadata] = {
    val item = Option(Item.getItemFromBlock(block));
    item.map(item => ItemWithMetadata(item, meta))
  }

  def fromItemStack(itemStack: ItemStack): Option[ItemWithMetadata] = {
    val item = Option(itemStack.getItem);
    val meta = itemStack.getItemDamage;
    item.map(item => ItemWithMetadata(item, meta))
  }
}
