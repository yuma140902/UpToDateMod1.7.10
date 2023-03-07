package yuma140902.yumalib

import yuma140902.yumalib.api.IRegisterable
import yuma140902.yumalib.items.{ItemDummyLogo, ItemGameModeSwitcher}

object YLItems {
  val dummyLogo: ItemDummyLogo.type = ItemDummyLogo
  val gmSwitcher: ItemGameModeSwitcher.type = ItemGameModeSwitcher

  private val items = List(dummyLogo, gmSwitcher)

  ModYumaLib.LOGGER.info("Items init")

  def registerAll(): Unit = {
    items.foreach(item => {
      if (item.isInstanceOf[IRegisterable]) {
        item.register()
      }
    })
  }
}
