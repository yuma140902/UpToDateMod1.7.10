package yuma140902.yumalib.api

import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.api.util.{GameRegistryEx, Name}

import net.minecraft.block.Block

trait RegisterableBlock extends Block with IRegisterable {
  val name: Name

  def register(): Unit = {
    this.setBlockName(Contexts.current().nameProvider.domainedUnlocalized(name.str))
    GameRegistryEx.registerBlock(this, name)
  }
}
