package yuma140902.yumalib.api

import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.api.util.{GameRegistryEx, Name}

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock

trait RegisterableBlock[T <: ItemBlock] extends Block with IRegisterable {
  val name: Name
  val cls: Class[T] = classOf[ItemBlock].asInstanceOf[Class[T]]

  def register(): Unit = {
    this.setBlockName(Contexts.current().nameProvider.domainedUnlocalized(name.str))
    GameRegistryEx.registerBlock(this, name, cls)
  }
}
