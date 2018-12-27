package yuma140902.uptodatemod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;

public class CommonEventHandler {
	private CommonEventHandler() {}
	
	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	
	@SubscribeEvent
	public void onUseHoeEvent(UseHoeEvent event) {
		BlockCoarseDirt.onUseHoeEvent(event);
	}
	
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		Entity entity = event.entityLiving;
		if(entity instanceof EntitySheep) {
			if(entity.isBurning()) {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(MyItems.cookedMutton)));
			}
			else {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(MyItems.rawMutton)));
			}
		}
	}
}
