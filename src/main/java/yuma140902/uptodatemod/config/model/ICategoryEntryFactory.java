package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface ICategoryEntryFactory<FluentAPI> {
	@Nonnull
	IConfigEntry<FluentAPI> build(@Nullable IConfigCategory category, @Nonnull String name);
}
