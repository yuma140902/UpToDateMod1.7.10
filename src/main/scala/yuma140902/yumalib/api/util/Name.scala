package yuma140902.yumalib.api.util

import net.minecraft.block.Block
import net.minecraft.item.Item

/** ModIDを含まない名前 */
case class Name(str: String)

/** ModIDを含む名前 */
case class NameWithModID(str: String)

object NameExtensions {

  implicit class BlockEx(self: Block) {
    def setBlockTextureName(name: NameWithModID): Unit =
      self.setBlockTextureName(name.str)
  }

  implicit class ItemEx(self: Item) {
    def setTextureName(name: NameWithModID): Unit =
      self.setTextureName(name.str)
  }
}