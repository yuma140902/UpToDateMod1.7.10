package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryExperimental extends ConfigCategoryBase {

	@Nonnull
	public static final String name = "Experimental";
	
	@Nonnull public final IConfigProp<Boolean> enableObserver;
	
	public CategoryExperimental(IConfigCategory category) {
		super(category, name);
		
		addCommentLine(L10nString.of(""));
		
		enableObserver = addSubValue("enableObserver");
	}
	
}
