package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface ConfigEntryFactory {
	@Nonnull
	IConfigEntry build(@Nullable IConfigCategory category, @Nonnull String name);
}
