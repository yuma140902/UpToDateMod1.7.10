package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;

public class CategoryRecipe extends ConfigCategoryUpToDateModBase {
	
	@Nonnull
	public static final String name = "Recipe";
	
	@Nonnull public final IConfigProp<Boolean>
			removeOldFence,
			addOldSmoothStoneSlab;

	public CategoryRecipe(IConfigCategory category) {
		super(category, name);
		
		setRequiresMcRestart();
		
		removeOldFence = addSubProp("removeOldFenceRecipe", "remove_old_fence_recipe");
		addOldSmoothStoneSlab = addSubProp("addStoneSlabRecipe"); //TODO なぜかこの項目だけ表示されない
	}
	
}
