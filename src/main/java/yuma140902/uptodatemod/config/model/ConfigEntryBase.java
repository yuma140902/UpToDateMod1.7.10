package yuma140902.uptodatemod.config.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ConfigEntryBase<FluentAPI extends ConfigEntryBase<?>> implements IConfigEntry<FluentAPI> {

	ConfigEntryBase(@Nullable IConfigCategory category, @Nonnull String name) {
		this.category = category;
		this.name = name;
	}
	
	
	@Nullable
	protected final IConfigCategory category;
	@Nonnull
	protected final String name;
	@Nonnull
	protected final List<String> comments = new ArrayList<String>();
	protected boolean requireMcRestart = false;
	
	
	@Override
	public IConfigCategory category() {
		return this.category;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String comment() {
		String comment = String.join("\n", this.comments);
		return comment != null ? comment : "";
	}
	
	@Override
	public boolean requireMcRestart() {
		return this.requireMcRestart;
	}

	@Override
	public FluentAPI addCommentLine(String comment) {
		this.comments.add(comment);
		return this;
	}

	@Override
	public FluentAPI setRequiresMcRestart() {
		this.requireMcRestart = true;
		return this;
	}

}
