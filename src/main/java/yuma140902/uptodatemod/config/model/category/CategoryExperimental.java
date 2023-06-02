package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;

public class CategoryExperimental extends ConfigCategoryUpToDateModBase {

	@Nonnull
	public static final String name = "Experimental";
	
	@Nonnull public final IConfigProp<Boolean> enableObserver;
	
	public CategoryExperimental(IConfigCategory category) {
		super(category, name);
		
		enableObserver = addSubProp("enableObserver");
	}
	
}
