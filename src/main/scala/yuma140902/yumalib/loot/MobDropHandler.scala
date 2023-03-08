package yuma140902.yumalib.loot

import yuma140902.yumalib.api.loot.MobLoot

import net.minecraft.entity.item.EntityItem
import net.minecraftforge.event.entity.living.LivingDropsEvent

import scala.collection.mutable

// TODO: yuma140902.yumalib.api.lootに移動する？
// TODO: registryとhandlerを分ける必要がある
object MobDropHandler {
  private val registry: mutable.ArrayStack[MobLoot] = new mutable.ArrayStack[MobLoot]()

  def register(mobLoot: MobLoot): Unit =
    if (mobLoot.enabled) registry.push(mobLoot)

  def onLivingDrop(event: LivingDropsEvent): Unit = {
    val entity = event.entityLiving
    val rand = event.entityLiving.worldObj.rand;

    registry.foreach(loot => {
      loot.newItemStack(rand, entity, event).map(stack => {
        event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack))
      })
    })
  }
}
