package yuma140902.yumalib.items

import yuma140902.yumalib.YumaLibCreativeTab
import yuma140902.yumalib.api.IRegisterable
import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.config.YLConfigCore

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.command.CommandGameMode
import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.ChatComponentText
import net.minecraft.world.World

object ItemGameModeSwitcher extends Item with IRegisterable {

  override def register(): Unit = {
    this.setUnlocalizedName(Contexts.DEFAULT.nameProvider.domainedUnlocalized("gm_switcher"))
    this.setTextureName(Contexts.DEFAULT.nameProvider.domainedTexture("gm_switcher"))
    GameRegistry.registerItem(this, "gm_switcher")
    YumaLibCreativeTab.setToTab(this)
  }

  override def addInformation(itemStack: ItemStack, player: EntityPlayer, list: java.util.List[_], isAdvMode: Boolean): Unit = {
    list.asInstanceOf[java.util.List[String]].add("[EXPERIMENTAL]")
  }

  override def onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
    if (!player.isInstanceOf[EntityPlayerMP]) {
      return itemStack
    }
    if (!(YLConfigCore.enableGMSwitcher)) {
      player.addChatMessage(new ChatComponentText("GameModeSwitcher is disabled"))
      return itemStack
    }
    try {
      if (player.capabilities.isCreativeMode) {
        new CommandGameMode().processCommand(player, Array[String]("survival"))
      }
      else {
        new CommandGameMode().processCommand(player, Array[String]("creative"))
      }
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    itemStack
  }
}
