package yuma140902.uptodatemod.loot;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;

public class MobLootInfo {
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
	
	public static MobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item) {
		return new MobLootInfo(enabled, entityClass, item, 0, null, null);
	}
	
	public static MobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, rand -> itemNum, null); 
	}
	
	public static MobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum, Predicate<LivingDropsEvent> extraValidator) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, rand -> itemNum, extraValidator);
	}
	
	public static MobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, itemNumProvider, null);
	}
	
	public static MobLootInfo of(boolean enabled, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider, Predicate<LivingDropsEvent> extraValidator) {
		return new MobLootInfo(enabled, entityClass, item, itemMeta, itemNumProvider, extraValidator);
	}
	
	public static MobLootInfo of(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item) {
		return of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item);
	}
	
	public static MobLootInfo of(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum) {
		return of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNum);
	}
	
	public static MobLootInfo of(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, int itemNum, Predicate<LivingDropsEvent> extraValidator) {
		return of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNum, extraValidator);
	}
	
	public static MobLootInfo of(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider) {
		return of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNumProvider, null);
	}
	
	public static MobLootInfo of(EnumDisableableFeatures feature, Class<? extends Entity> entityClass, Item item, int itemMeta, Function<Random, Integer> itemNumProvider, Predicate<LivingDropsEvent> extraValidator) {
		return of(DisabledFeaturesRegistry.INSTANCE.isEnabled(feature), entityClass, item, itemMeta, itemNumProvider, extraValidator);
	}
	
	public boolean enabled() {return enabled;}
	public Class<? extends Entity> entityClass() {return entityClass;}
	public Item item() {return item;}
	public int itemMeta() {return itemMeta;}
	public int itemNum(Random rand) {return itemNumProvider.apply(rand);}
	public boolean extraValidate(LivingDropsEvent event) {return extraValidator.test(event);}
	
	public boolean matchEntityClass(Entity entity) {
		return entityClass.isInstance(entity);
	}
}
