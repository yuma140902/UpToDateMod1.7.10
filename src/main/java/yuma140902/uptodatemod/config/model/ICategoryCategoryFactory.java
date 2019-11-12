package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ICategoryCategoryFactory extends ICategoryEntryFactory<IConfigCategory> {
	@Nonnull
	IConfigCategory build(@Nullable IConfigCategory category, @Nonnull String name);
}
