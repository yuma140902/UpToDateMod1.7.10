package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryEntity extends ConfigCategoryBase {

	@Nonnull
	public static final String name = "Entity";
	
	@Nonnull public final IConfigProp<Boolean> boatCrashWhenCollide;
	
	public CategoryEntity(IConfigCategory category) {
		super(category, name);
		
		addCommentLine(L10nString.of(""));
		
		boatCrashWhenCollide = addSubValue("boatCrashWhenCollide");
	}
	
}

