package yuma140902.yumalib.api.loot;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

/**
 * mobドロップを表すインターフェース。
 * <p>実装クラスのインスタンスは{@link yuma140902.yumalib.loot.MobDropHandler}に登録する</p>
 * <p>実装クラスとして{@link MobLootInfo}が用意されているので基本的にそれを使えば良い</p>
 */
public interface IMobLootInfo {
	
	boolean enabled();
	
	Class<? extends Entity> entityClass();
	
	Item item();
	
	int itemMeta();
	
	int itemNum(Random rand);
	
	boolean extraValidate(LivingDropsEvent event);
	
	boolean matchEntityClass(Entity entity);
	
}
