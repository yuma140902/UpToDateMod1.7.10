package yuma140902.uptodatemod.config.model.category;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.uptodatemod.config.ConfigUtil;
import yuma140902.uptodatemod.config.model.ConfigCategoryBase;
import yuma140902.uptodatemod.config.model.ConfigProp;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;

public class ConfigCategoryUpToDateModBase extends ConfigCategoryBase {
	public ConfigCategoryUpToDateModBase(@Nullable IConfigCategory category, @Nonnull String name) {
		this(category, name, name.toLowerCase());
	}
	
	public ConfigCategoryUpToDateModBase(@Nullable IConfigCategory category, @Nonnull String name, String langName) {
		super(category, name, "config.uptodate.category." + langName);
		ConfigUtil.setL10nNameAndTooltipToCategory(this, langName);
	}
	
	@Override
	public <T> IConfigProp<T> addSubProp(String name) {
		return super.addSubProp(name, (_cate, _name) -> {
			IConfigProp<T> prop = new ConfigProp<T>(_cate, name);
			ConfigUtil.setL10nNameAndTooltipToProp(prop, name.toLowerCase());
			return prop;
		});
	}
	
	@Nonnull
	public <T> IConfigProp<T> addSubPropWithoutTooltip(@Nonnull String name) {
		return super.addSubProp(name);
	}
	
	@Nonnull
	public <T> IConfigProp<T> addSubProp(@Nonnull String name, @Nonnull String langName) {
		return super.addSubProp(name, (_cate, _name) -> {
			IConfigProp<T> prop = new ConfigProp<T>(_cate, _name);
			ConfigUtil.setL10nNameAndTooltipToProp(prop, langName);
			return prop;
		});
	}
}
