package yuma140902.yumalib.api.items

import yuma140902.yumalib.api.IRegisterable
import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.api.util.StringUtils

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.IIcon

import java.util

/**
 * メタによって名前とテクスチャが変わるItem.
 */
class ItemMultiMeta(
                     /** アイテム名。ModIDなし */
                     val name: String,

                     /** langファイルのキーの末尾につけられる文字列のリスト */
                     val names: Array[String],

                     /** テクスチャ名のリスト。ModIDなし */
                     _textureNames: Array[String]
                   )
  extends Item with IRegisterable {
  protected val textureNames: Array[String] =
    if (_textureNames.length < names.length) {
      val sanitized = new Array[String](names.length)
      for (i <- sanitized.indices) {
        sanitized(i) =
          if (i < _textureNames.length) _textureNames(i)
          else _textureNames(0)
      }
      sanitized
    }
    else _textureNames
  protected val MAX_META: Int = names.length - 1
  protected val icons: Array[IIcon] = new Array[IIcon](names.length);

  override def register(): Unit = {
    this.setUnlocalizedName(Contexts.current.nameProvider.domainedUnlocalized(name))
    GameRegistry.registerItem(this, name)
  }

  override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]): Unit =
    for (meta <- 0 to MAX_META)
      list.asInstanceOf[util.List[ItemStack]].add(new ItemStack(item, 1, meta))

  override def registerIcons(register: IIconRegister): Unit =
    for (meta <- 0 to MAX_META)
      icons(meta) = register.registerIcon(textureNames(meta))

  override def getIconFromDamage(meta: Int): IIcon =
    icons(meta % (MAX_META + 1))

  override def getUnlocalizedName(itemStack: ItemStack): String = {
    val meta = itemStack.getItemDamage
    if (0 <= meta && meta <= MAX_META)
      super.getUnlocalizedName + StringUtils.suffix(".", names(meta))
    else
      super.getUnlocalizedName
  }
}
