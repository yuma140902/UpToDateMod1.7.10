package yuma140902.uptodatemod.config.model.category;

import static yuma140902.uptodatemod.util.l10n.EnumLanguage.*;
import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryRoot;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.util.UpdateChecker;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryGeneral extends ConfigCategoryRoot {

	@Nonnull
	public static final String name = "General";
	
	@Nonnull public final IConfigProp<Boolean> doUpdateChecking;
	@Nonnull public final IConfigProp<String> updateChannel;
	@Nonnull public final IConfigProp<Boolean> debugMode;
	
	public CategoryGeneral() {
		super(name);
		addCommentLine(L10nString.ml().put(en_US, "Settings of UpToDateMod").put(ja_JP, "UpToDateModの設定").nonnull());
		
		doUpdateChecking = addSubValue("doUpdateChecking");
		updateChannel = addSubValue("updateChannel");
		updateChannel.setValidStrings(UpdateChecker.RECOMMENDED_STR, UpdateChecker.LATEST_STR);
		debugMode = addSubValue("enableDebugMode");
	}
	
}
