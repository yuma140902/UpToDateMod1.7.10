package yuma140902.uptodatemod.loot;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.yumalib.api.loot.IMobLootInfo;
import yuma140902.yumalib.api.loot.MobLootInfo;
import yuma140902.yumalib.loot.MobDropHandler;

public class MobLoot {
	private MobLoot() {}
	
	@SuppressWarnings("unused")
	private static IMobLootInfo mobLootInfoOf(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item) {
		return MobLootInfo.of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item);
	}
	
	@SuppressWarnings("unused")
	private static IMobLootInfo mobLootInfoOf(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum) {
		return MobLootInfo.of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNum);
	}
	
	@SuppressWarnings("unused")
	private static IMobLootInfo mobLootInfoOf(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum, Predicate<LivingDropsEvent> extraValidator) {
		return MobLootInfo.of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNum, extraValidator);
	}
	
	private static IMobLootInfo mobLootInfoOf(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider) {
		return MobLootInfo.of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNumProvider, null);
	}
	
	private static IMobLootInfo mobLootInfoOf(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider, Predicate<LivingDropsEvent> extraValidator) {
		return MobLootInfo.of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNumProvider, extraValidator);
	}
	
	public static void registerBasicMobLoots() {
		MobDropHandler.INSTANCE.register(mobLootInfoOf(EnumDisableableFeatures.mutton, EntitySheep.class, MyItems.cookedMutton, 0, rand -> rand.nextInt(2) + 1, event -> event.entity.isBurning()));
		MobDropHandler.INSTANCE.register(mobLootInfoOf(EnumDisableableFeatures.mutton, EntitySheep.class, MyItems.rawMutton, 0, rand -> rand.nextInt(2) + 1, event -> !event.entity.isBurning()));
		if(ModConfigCore.Alternative.altPrismarine()) {
			MobDropHandler.INSTANCE.register(mobLootInfoOf(EnumDisableableFeatures.prismarineStuffs, EntitySquid.class, MyItems.prismarineShard, 0, 
					rand -> (rand.nextInt(6) + rand.nextInt(2))*2));
			MobDropHandler.INSTANCE.register(mobLootInfoOf(EnumDisableableFeatures.prismarineStuffs, EntitySquid.class, MyItems.prismarineCrystal, 0, 
					rand -> (rand.nextInt(2) + rand.nextInt(2))*1));
		}
		if(ModConfigCore.Alternative.altPurpur()) {
			MobDropHandler.INSTANCE.register(mobLootInfoOf(EnumDisableableFeatures.purpurStuffs, EntityEnderman.class, Item.getItemFromBlock(MyBlocks.purpurBlock), 0, 
					rand -> (rand.nextInt(3) + rand.nextInt(3))*2, 
					event -> event.entity.worldObj.provider.dimensionId == 1));  // エンドにいるとき
			MobDropHandler.INSTANCE.register(mobLootInfoOf(EnumDisableableFeatures.purpurStuffs, EntityEnderman.class, Item.getItemFromBlock(MyBlocks.purpurBlock), 0,
					rand -> rand.nextInt(3),
					event -> {
						int dimId = event.entity.worldObj.provider.dimensionId;
						return dimId != 0 && dimId != -1;     // オーバーワールドでもネザーでもないところにいるとき
					}));
		}
		
	}
}
