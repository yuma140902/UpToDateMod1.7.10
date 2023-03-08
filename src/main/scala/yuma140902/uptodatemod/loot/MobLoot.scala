package yuma140902.uptodatemod.loot

import yuma140902.uptodatemod.config.ModConfigCore
import yuma140902.uptodatemod.registry.EnumDisableableFeatures._
import yuma140902.uptodatemod.{MyBlocks, MyItems}
import yuma140902.yumalib.api.loot.MobLootInfo
import yuma140902.yumalib.api.util.ItemWithMetadata
import yuma140902.yumalib.loot.MobDropHandler

import net.minecraft.entity.monster.EntityEnderman
import net.minecraft.entity.passive.{EntitySheep, EntitySquid};

object MobLoot {
  def registerBasicMobLoots(): Unit = {
    MobDropHandler.register(MobLootInfo(mutton.featureEnabled(), classOf[EntitySheep], ItemWithMetadata(MyItems.cookedMutton), rand => rand.nextInt(2) + 1, event => event.entity.isBurning))
    MobDropHandler.register(MobLootInfo(mutton.featureEnabled(), classOf[EntitySheep], ItemWithMetadata(MyItems.rawMutton), rand => rand.nextInt(2) + 1, event => !event.entity.isBurning))

    if (ModConfigCore.Alternative.altPrismarine) {
      MobDropHandler.register(MobLootInfo(prismarineStuffs.featureEnabled(), classOf[EntitySquid], ItemWithMetadata(MyItems.prismarineShard),
        rand => (rand.nextInt(6) + rand.nextInt(2)) * 2,
        _ => true
      ))
      MobDropHandler.register(MobLootInfo(prismarineStuffs.featureEnabled(), classOf[EntitySquid], ItemWithMetadata(MyItems.prismarineCrystal),
        rand => rand.nextInt(2) + rand.nextInt(2),
        _ => true
      ))
    }

    if (ModConfigCore.Alternative.altPurpur) {
      ItemWithMetadata.fromBlock(MyBlocks.purpurBlock) match {
        case Some(item) => {
          MobDropHandler.register(MobLootInfo(purpurStuffs.featureEnabled(), classOf[EntityEnderman], item,
            rand => (rand.nextInt(3) + rand.nextInt(3)) * 2,
            event => event.entity.worldObj.provider.dimensionId == 1 // エンドにいるとき
          ))
          MobDropHandler.register(MobLootInfo(purpurStuffs.featureEnabled(), classOf[EntityEnderman], item,
            rand => rand.nextInt(3),
            event => {
              val dimId = event.entity.worldObj.provider.dimensionId;
              dimId != 0 && dimId != -1 // オーバーワールドでもネザーでもないディメンションにいるとき
            }
          ))
        }
        case None => ()
      }
    }
  }
}
