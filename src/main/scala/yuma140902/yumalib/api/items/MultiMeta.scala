package yuma140902.yumalib.api.items

import yuma140902.yumalib.api.IRegisterable
import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.api.util.{GameRegistryEx, Name, StringUtils}

import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemFood, ItemStack}
import net.minecraft.util.IIcon

import java.util

/**
 * メタによって名前とテクスチャが変わるItemを実現するトレイト
 */
trait MultiMeta extends Item with IRegisterable {
  val name: Name
  val names: Array[String]
  val icons: Array[IIcon] = new Array[IIcon](names.length)
  protected val _textureNames: Array[String]
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

  override def register(): Unit = {
    this.setUnlocalizedName(Contexts.current.nameProvider.domainedUnlocalized(name.str))
    GameRegistryEx.registerItem(this, name)
  }

  override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]): Unit =
    for (meta <- names.indices)
      list.asInstanceOf[util.List[ItemStack]].add(new ItemStack(item, 1, meta))

  override def registerIcons(register: IIconRegister): Unit =
    for (meta <- names.indices)
      icons(meta) = register.registerIcon(textureNames(meta))

  override def getIconFromDamage(meta: Int): IIcon =
    icons(meta % (names.length))

  override def getUnlocalizedName(itemStack: ItemStack): String = {
    val meta = itemStack.getItemDamage
    if (0 <= meta && meta < names.length)
      super.getUnlocalizedName + StringUtils.suffix(".", names(meta))
    else
      super.getUnlocalizedName
  }
}

/**
 * メタによって名前とテクスチャが変わるItem.
 */
class ItemMultiMeta(
                     /** アイテム名。ModIDなし */
                     val name: Name,

                     /** langファイルのキーの末尾につけられる文字列のリスト */
                     val names: Array[String],

                     /** テクスチャ名のリスト。ModIDなし */
                     protected val _textureNames: Array[String]
                   )
  extends MultiMeta

/**
 * メタによって名前とテクスチャが変わるItemFood.
 * <p>
 * マイクラの仕様により回復量をメタによって変えることはできない？
 * </p>
 */
class ItemFoodMultiMeta(
                         healAmount: Int,
                         saturationModifier: Float,

                         /** アイテム名 */
                         val name: Name,

                         /** langファイルのキーの末尾につけられる文字列のリスト */
                         val names: Array[String],

                         /** テクスチャ名のリスト */
                         protected val _textureNames: Array[String]
                       )
  extends ItemFood(healAmount, saturationModifier, false) with MultiMeta
