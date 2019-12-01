package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryWorldgen extends ConfigCategoryBase {

	@Nonnull
	public static final String name = "WorldGen";
	
	@Nonnull public final IConfigProp<Boolean> genStones;
	@Nonnull public final IConfigProp<Integer[]> stoneDimBlackList;
	@Nonnull public final IConfigProp<Boolean> genFossiles;
	@Nonnull public final IConfigProp<Integer[]> fossilesDimBlackList;
	@Nonnull public final IConfigProp<Boolean> genCoarseDirt;
	@Nonnull public final IConfigProp<Integer[]> coarseDirtDimBlackList;
	
	public CategoryWorldgen(IConfigCategory category) {
		super(category, name);
		
		addCommentLine(L10nString.of(""));
		setRequiresMcRestart();
		
		genStones = addSubValue("genStones");
		stoneDimBlackList = addSubValue("genStonesDimensionBlackList");
		genFossiles = addSubValue("genFossiles");
		fossilesDimBlackList = addSubValue("genFossilesDimensionBlackList");
		genCoarseDirt = addSubValue("genCoarseDirt");
		coarseDirtDimBlackList = addSubValue("genCoarseDirtDimensionBlackList");
	}
}
