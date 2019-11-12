package yuma140902.uptodatemod.config.model;

import java.util.Iterator;
import javax.annotation.Nonnull;

public interface IConfigCategory extends IConfigEntry<IConfigCategory> {
	
	@Nonnull Iterator<IConfigEntry<?>> subEntries();
	
	@Nonnull
	<T extends IConfigEntry<?>> IConfigEntry<T> addSubEntry(@Nonnull String name, @Nonnull ICategoryEntryFactory<T> factory);
	
	@Nonnull
	default IConfigCategory addSubCategory(@Nonnull String name, @Nonnull ICategoryCategoryFactory factory) {
		return (IConfigCategory) addSubEntry(name, factory);
	}
	
	@Nonnull
	default IConfigCategory addSubCategory(@Nonnull String name) {
		return addSubCategory(name, ConfigCategoryBase::new);
	}
	
	
}