package yuma140902.yumalib_ee.api.loot;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MobLootInfo implements IMobLootInfo {
	private boolean enabled;
	private Class<? extends Entity> entityClass;
	private Item item;
	private int itemMeta = 0;
	private Function<Random, Integer> itemNumProvider;
	private Predicate<LivingDropsEvent> extraValidator;
	
	private MobLootInfo(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, @Nullable Function<Random, Integer> itemNumProvider, @Nullable Predicate<LivingDropsEvent> extraValidator) {
		this.enabled = enabled;
		this.entityClass = entityClass;
		this.item = item;
		this.itemMeta = itemMeta;
		if(itemNumProvider != null) this.itemNumProvider = itemNumProvider;
		else this.itemNumProvider = rand -> 1;
		if(extraValidator != null) this.extraValidator = extraValidator;
		else this.extraValidator = event -> true;
	}
	
	public static IMobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item) {
		return new MobLootInfo(enabled, entityClass, item, 0, null, null);
	}
	
	public static IMobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, rand -> itemNum, null); 
	}
	
	public static IMobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum, Predicate<LivingDropsEvent> extraValidator) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, rand -> itemNum, extraValidator);
	}
	
	public static IMobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, itemNumProvider, null);
	}
	
	public static IMobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider, Predicate<LivingDropsEvent> extraValidator) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, itemNumProvider, extraValidator);
	}
	
	@Override
	public boolean enabled() {return enabled;}
	@Override
	public Class<? extends Entity> entityClass() {return entityClass;}
	@Override
	public Item item() {return item;}
	@Override
	public int itemMeta() {return itemMeta;}
	@Override
	public int itemNum(Random rand) {return itemNumProvider.apply(rand);}
	@Override
	public boolean extraValidate(LivingDropsEvent event) {return extraValidator.test(event);}
	
	@Override
	public boolean matchEntityClass(Entity entity) {
		return entityClass.isInstance(entity);
	}
}
