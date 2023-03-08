package yuma140902.yumalib.items

import yuma140902.yumalib.api.IRegisterable
import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.api.util.Name
import yuma140902.yumalib.api.util.NameExtensions._

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object ItemDummyLogo extends Item with IRegisterable {
  override def register(): Unit = {
    this.setUnlocalizedName(Contexts.DEFAULT.nameProvider.domainedUnlocalized("dummy_logo"))
    this.setTextureName(Contexts.DEFAULT.nameProvider.domainedTexture(Name("dummy_logo")))
    GameRegistry.registerItem(this, "dummy_logo")
  }

  override def getSubItems(item: Item, tab: CreativeTabs, list: java.util.List[_]): Unit = {}
}
