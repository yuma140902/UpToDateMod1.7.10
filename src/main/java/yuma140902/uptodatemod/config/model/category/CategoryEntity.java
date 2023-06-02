package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;

public class CategoryEntity extends ConfigCategoryUpToDateModBase {

	@Nonnull
	public static final String name = "Entity";
	
	@Nonnull public final IConfigProp<Boolean> boatCrashWhenCollide;
	
	public CategoryEntity(IConfigCategory category) {
		super(category, name);
		
		boatCrashWhenCollide = addSubProp("boatCrashWhenCollide");
	}
	
}

