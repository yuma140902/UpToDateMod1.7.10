package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;

public class CategoryWorldgen extends ConfigCategoryUpToDateModBase {

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
		
		setRequiresMcRestart();
		
		genStones = addSubProp("genStones");
		genStones.setDefault(true);
		stoneDimBlackList = addSubProp("genStonesDimensionBlackList");
		stoneDimBlackList.setDefaultIntList(1, -1);
		
		genFossiles = addSubProp("genFossiles");
		genFossiles.setDefault(true);
		fossilesDimBlackList = addSubProp("genFossilesDimensionBlackList");
		fossilesDimBlackList.setDefaultIntList(1, -1);
		
		genCoarseDirt = addSubProp("genCoarseDirt");
		genCoarseDirt.setDefault(true);
		coarseDirtDimBlackList = addSubProp("genCoarseDirtDimensionBlackList");
		coarseDirtDimBlackList.setDefaultIntList(1, -1);
	}
}
