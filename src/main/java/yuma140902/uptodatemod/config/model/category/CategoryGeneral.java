package yuma140902.uptodatemod.config.model.category;

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
		
		setLocalizationKey("config.uptodate.category.general");
		addCommentLine(L10nString.ofKey("config.uptodate.category.general.tooltip"));
		
		doUpdateChecking = addSubProp("doUpdateChecking");
		doUpdateChecking.setDefault(true);
		updateChannel = addSubProp("updateChannel");
		updateChannel.setValidStrings(UpdateChecker.RECOMMENDED_STR, UpdateChecker.LATEST_STR);
		updateChannel.setDefault(UpdateChecker.RECOMMENDED_STR);
		debugMode = addSubProp("enableDebugMode");
	}
	
}
