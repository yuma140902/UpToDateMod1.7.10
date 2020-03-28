package yuma140902.uptodatemod.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;

public class MobDropHandler {
	
	public static MobDropHandler INSTANCE = new MobDropHandler();
	
	private MobDropHandler() {}
	
	private List<MobLootInfo> registry = new ArrayList<MobLootInfo>();
	
	public void register(MobLootInfo info) {
		if(info != null && info.enabled()) registry.add(info);
	}
	
	public void registerBasicMobLoots() {
		register(MobLootInfo.of(EnumDisableableFeatures.mutton, EntitySheep.class, MyItems.cookedMutton, 0, rand -> rand.nextInt(2) + 1, event -> event.entity.isBurning()));
		register(MobLootInfo.of(EnumDisableableFeatures.mutton, EntitySheep.class, MyItems.rawMutton, 0, rand -> rand.nextInt(2) + 1, event -> !event.entity.isBurning()));
		if(ModConfigCore.Alternative.altPrismarine()) {
			register(MobLootInfo.of(EnumDisableableFeatures.prismarineStuffs, EntitySquid.class, MyItems.prismarineShard, 0, 
					rand -> (rand.nextInt(6) + rand.nextInt(2))*2));
			register(MobLootInfo.of(EnumDisableableFeatures.prismarineStuffs, EntitySquid.class, MyItems.prismarineCrystal, 0, 
					rand -> (rand.nextInt(2) + rand.nextInt(2))*1));
		}
		if(ModConfigCore.Alternative.altPurpur()) {
			register(MobLootInfo.of(EnumDisableableFeatures.purpurStuffs, EntityEnderman.class, Item.getItemFromBlock(MyBlocks.purpurBlock), 0, 
					rand -> (rand.nextInt(3) + rand.nextInt(3))*2, 
					event -> event.entity.worldObj.provider.dimensionId == 1));  // エンドにいるとき
			register(MobLootInfo.of(EnumDisableableFeatures.purpurStuffs, EntityEnderman.class, Item.getItemFromBlock(MyBlocks.purpurBlock), 0,
					rand -> rand.nextInt(3),
					event -> {
						int dimId = event.entity.worldObj.provider.dimensionId;
						return dimId != 0 && dimId != -1;     // オーバーワールドでもネザーでもないところにいるとき
					}));
		}
		
	}
	
	public void onLivingDrop(LivingDropsEvent event) {
		Entity entity = event.entityLiving;
		Random rand = event.entityLiving.worldObj.rand;
		
		for(final MobLootInfo info : registry) {
			if(info != null && info.matchEntityClass(entity) && info.extraValidate(event)) {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(info.item(), info.itemNum(rand), info.itemMeta())));
			}
		}
		
	}
}
