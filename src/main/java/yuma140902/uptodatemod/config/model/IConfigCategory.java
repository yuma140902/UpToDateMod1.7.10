package yuma140902.uptodatemod.config.model;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

public interface IConfigCategory extends IConfigEntry {
	
	@Nonnull Iterator<IConfigEntry> subEntries();
	
	@Nonnull List<String> propterties();
	
	@Nonnull
	IConfigEntry addSubEntry(@Nonnull Supplier<IConfigEntry> supplier);
	
	@SuppressWarnings("unchecked")
	@Nonnull
	default <T> IConfigProp<T> addSubValue(@Nonnull String name, @Nonnull ConfigPropFactory<T> factory) {
		return (IConfigProp<T>) addSubEntry(() -> factory.build(this, name));
	}
	
	@Nonnull
	default <T> IConfigProp<T> addSubValue(@Nonnull String name) {
		return addSubValue(name, ConfigProp::new);
	}
	
	@Nonnull
	default IConfigCategory addSubCategory(@Nonnull String name, @Nonnull ConfigCategoryFactory factory) {
		return (IConfigCategory) addSubEntry(() -> factory.build(this, name));
	}
	
	@Nonnull
	default IConfigCategory addSubCategory(@Nonnull ConfigCategoryFactoryWithoutName factory) {
		return (IConfigCategory) addSubEntry(() -> factory.build(this));
	}
	
	@Nonnull
	default IConfigCategory addSubCategory(@Nonnull String name) {
		return addSubCategory(name, ConfigCategoryBase::new);
	}
	
}