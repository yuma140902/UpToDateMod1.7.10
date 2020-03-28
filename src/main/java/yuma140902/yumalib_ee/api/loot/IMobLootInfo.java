package yuma140902.yumalib_ee.api.loot;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;


public interface IMobLootInfo {
	
	boolean enabled();
	
	Class<? extends Entity> entityClass();
	
	Item item();
	
	int itemMeta();
	
	int itemNum(Random rand);
	
	boolean extraValidate(LivingDropsEvent event);
	
	boolean matchEntityClass(Entity entity);
	
}
