package yuma140902.uptodatemod.config;

import java.util.List;
import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigProp;


public interface IConfigBridge {
	
	void save();
	
	IConfigCategory root();
	
	//カテゴリ内での並び順を設定
	void setCategoryPropertyOrder(IConfigCategory category);
	
	void initCategory(IConfigCategory category);
	
	boolean getBoolean(IConfigProp<Boolean> prop);
	
	int getInt(IConfigProp<Integer> prop);
	
	@Nonnull
	String getString(IConfigProp<String> prop);
	
	@Nonnull
	String getStringSelection(IConfigProp<String> prop);
	
	@Nonnull
	String getStringSelection(IConfigProp<String> prop, @Nonnull String dflt);
	
	@Nonnull
	List<String> getStringList(IConfigProp<List<String>> prop);
	
	@Nonnull
	int[] getIntList(IConfigProp<Integer[]> prop);
	
}
