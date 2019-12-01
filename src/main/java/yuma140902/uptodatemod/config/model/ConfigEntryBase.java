package yuma140902.uptodatemod.config.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.uptodatemod.util.l10n.L10nString;

public abstract class ConfigEntryBase implements IConfigEntry {
	ConfigEntryBase(@Nullable IConfigCategory category, @Nonnull String name) {
		this.category = category;
		this.name = name;
	}
	
	
	@Nullable
	protected final IConfigCategory category;
	@Nonnull
	protected final String name;
	@Nonnull
	protected final List<L10nString> comments = new ArrayList<L10nString>();
	protected boolean requireMcRestart = false;
	protected boolean requireWorldRestart = false;
	
	
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
		String comment = this.comments.stream().map(cmnt -> cmnt.message()).collect(Collectors.joining("\n"));
		return comment != null ? comment : "";
	}
	
	@Override
	public boolean requireMcRestart() {
		return this.requireMcRestart;
	}
	
	@Override
	public boolean requireWorldRestart() {
		return this.requireWorldRestart;
	}
	
	@Override
	public void addCommentLine(L10nString comment) {
		this.comments.add(comment);
	}

	@Override
	public void setRequiresMcRestart() {
		this.requireMcRestart = true;
	}
	
	@Override
	public void setRequiresWorldRestart() {
		this.requireWorldRestart = true;
	}
}
