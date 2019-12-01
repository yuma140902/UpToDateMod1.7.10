package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface ConfigCategoryFactoryWithoutName {
	@Nonnull
	IConfigCategory build(@Nullable IConfigCategory category);
}
