package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.ConfigProp;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class CategoryFeatures extends ConfigCategoryBase {

	@Nonnull
	public static final String name = "DisableFeatures";
	
	public CategoryFeatures(IConfigCategory category) {
		super(category, name);
		
		addCommentLine(L10nString.of(""));
		
		for(EnumDisableableFeatures feature : EnumDisableableFeatures.values()) {
			String propName = "enable " + feature.toString();
			String comment = feature.getComment();
			
			IConfigProp<Boolean> prop = addSubValue(propName, (_category, name) -> new ConfigPropFeature(_category, name, feature));
			if(comment != null) prop.addCommentLine(L10nString.of(comment));
			
		}
	}
	
	
	
	
	
	public static class ConfigPropFeature extends ConfigProp<Boolean> {

		@Nonnull
		private EnumDisableableFeatures feature;
		
		ConfigPropFeature(@Nonnull IConfigCategory category, @Nonnull String name, @Nonnull EnumDisableableFeatures feature) {
			super(category, name);
			this.feature = feature;
		}
		
		public EnumDisableableFeatures feature() {
			return this.feature;
		}
		
	}
	
}

