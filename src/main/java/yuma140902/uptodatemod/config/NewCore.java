package yuma140902.uptodatemod.config;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryRoot;
import yuma140902.uptodatemod.config.model.IConfigCategory;

public class NewCore {
	
	@Nonnull
	public static final String 
			CAT_GENERAL = "General",
			CAT_WORLDGEN = "WorldGen",
			CAT_RECIPE = "Recipe",
			CAT_EXPERIMENTAL = "Experimental",
			CAT_ENTITY = "Entity",
			CAT_DISABLE_FEATURES = "DisableFeatures";
	
	@Nonnull public final ConfigCategoryRoot root;
	@Nonnull public final IConfigCategory worldgen;
	@Nonnull public final IConfigCategory recipe;
	
	
	public NewCore() {
		root = (ConfigCategoryRoot) new ConfigCategoryRoot(CAT_GENERAL).addCommentLine("Settings of UpToDateMod");
		worldgen = root.addSubCategory(CAT_WORLDGEN).addCommentLine("").setRequiresMcRestart();
		recipe = root.addSubCategory(CAT_RECIPE);
	}
}
