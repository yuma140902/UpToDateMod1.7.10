package yuma140902.yumalib.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import yuma140902.yumalib.api.loot.IMobLootInfo;

public class MobDropHandler {
	
	public static MobDropHandler INSTANCE = new MobDropHandler();
	
	private MobDropHandler() {}
	
	private List<IMobLootInfo> registry = new ArrayList<IMobLootInfo>();
	
	public void register(IMobLootInfo info) {
		if(info != null && info.enabled()) registry.add(info);
	}
	
	public void onLivingDrop(LivingDropsEvent event) {
		Entity entity = event.entityLiving;
		Random rand = event.entityLiving.worldObj.rand;
		
		for(final IMobLootInfo info : registry) {
			if(info != null && info.matchEntityClass(entity) && info.extraValidate(event)) {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(info.item(), info.itemNum(rand), info.itemMeta())));
			}
		}
		
	}
}
