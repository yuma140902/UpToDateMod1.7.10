package yuma140902.yumalib.api.loot

import yuma140902.yumalib.api.util.ItemWithMetadata

import net.minecraft.entity.Entity
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.event.entity.living.LivingDropsEvent

import java.util.Random

/**
 * mobドロップを表すインターフェース。
 * <p>実装クラスのインスタンスは{@link yuma140902.yumalib.loot.MobDropHandler}に登録する</p>
 */
trait MobLoot {
  def enabled: Boolean
  def newItemStack(rand: Random, entity: Entity, event: LivingDropsEvent): Option[ItemStack]
}

case class MobLootInfo[E <: Entity](
                        enabled: Boolean,
                        entityClass: Class[E],
                        itemWithMetadata: ItemWithMetadata,
                        numItemsProvider: Random => Int = _ => 0,
                        extraValidator: LivingDropsEvent => Boolean = _ => true
                      ) extends MobLoot {
  override def newItemStack(rand: Random, entity: Entity, event: LivingDropsEvent): Option[ItemStack] = {
    if (matchEntityClass(entity) && extraValidator(event)) {
      Some(new ItemStack(itemWithMetadata.item, numItemsProvider(rand), itemWithMetadata.meta))
    }
    else None
  }

  private def matchEntityClass(entity: Entity): Boolean =
    entityClass.isInstance(entity)
}

