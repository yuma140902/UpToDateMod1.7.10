package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IConfigEntry<FluentAPI extends IConfigEntry<?>> {
	@Nullable IConfigCategory category();
	@Nonnull String name();
	@Nonnull String comment();
	boolean requireMcRestart();
	
	@Nonnull
	FluentAPI addCommentLine(@Nonnull String comment);
	
	@Nonnull
	FluentAPI setRequiresMcRestart();
	
}
