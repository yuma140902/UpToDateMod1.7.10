package yuma140902.uptodatemod.integration;

import static yuma140902.uptodatemod.util.l10n.EnumLanguage.*;
import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryIntegration extends ConfigCategoryBase {
	@Nonnull
	public static final String name = "General";
	
	public CategoryIntegration(IConfigCategory category) {
		super(category, name);
		addCommentLine(L10nString.ml().put(en_US, "Settings to cooperate with other mods").put(ja_JP, "他Modとの連携のための設定").nonnull());
		setRequiresMcRestart();
	}
}
