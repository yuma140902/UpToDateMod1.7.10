package yuma140902.uptodatemod.integration;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.category.ConfigCategoryUpToDateModBase;

public class CategoryIntegration extends ConfigCategoryUpToDateModBase {
	@Nonnull
	public static final String name = "Integration";
	
	public CategoryIntegration(IConfigCategory category) {
		super(category, name);
		setRequiresMcRestart();
	}
}
