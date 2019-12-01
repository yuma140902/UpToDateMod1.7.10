package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.uptodatemod.util.l10n.L10nString;

public interface IConfigEntry {
	@Nullable IConfigCategory category();
	@Nonnull String name();
	@Nonnull String comment();
	@Nullable
	default String tooltipComment() {
		return comment();
	}
	boolean requireMcRestart();
	boolean requireWorldRestart();
	
	void addCommentLine(@Nonnull L10nString comment);
	
	void setRequiresMcRestart();
	
	void setRequiresWorldRestart();
	
}
