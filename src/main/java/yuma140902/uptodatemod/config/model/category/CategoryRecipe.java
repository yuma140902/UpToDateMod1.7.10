package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryRecipe extends ConfigCategoryBase {
	
	@Nonnull
	public static final String name = "Recipe";
	
	@Nonnull public final IConfigProp<Boolean>
			oldFence,
			oldSmoothStoneSlab;

	public CategoryRecipe(IConfigCategory category) {
		super(category, name);
		
		addCommentLine(L10nString.of(""));
		setRequiresMcRestart();
		
		oldFence = addSubValue("removeOldFenceRecipe");
		oldSmoothStoneSlab = addSubValue("addStoneSlabRecipe");
	}
	
}
