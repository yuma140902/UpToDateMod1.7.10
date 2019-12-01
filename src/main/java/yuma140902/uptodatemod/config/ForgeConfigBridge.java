package yuma140902.uptodatemod.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.ArrayUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigEntry;
import yuma140902.uptodatemod.config.model.IConfigProp;

public class ForgeConfigBridge implements IConfigBridge {
	@Nonnull final IConfigCategory root;
	@Nonnull final Configuration cfg;
	
	public ForgeConfigBridge(@Nonnull IConfigCategory root, @Nonnull Configuration configFile) {
		this.root = root;
		this.cfg = configFile;
	}
	
	@Override
	public void save() {
		this.cfg.save();
	}
	
	@Override
	public IConfigCategory root() {
		return this.root;
	}
	
	//カテゴリ内での並び順を設定
	@Override
	public void setCategoryPropertyOrder(IConfigCategory category) {
		cfg.setCategoryPropertyOrder(category.name(), category.propterties());
	}
	
	public void initCategories() {
		initCategory(root);
		
		Iterator<IConfigEntry> iterator = root.subEntries();
		while (iterator.hasNext()) {
			IConfigEntry entry = iterator.next();
			if(!(entry instanceof IConfigCategory)) continue;
			initCategory((IConfigCategory) entry);
		}
	}
	
	@Override
	public void initCategory(IConfigCategory category) {
		cfg.setCategoryComment(category.name(), category.comment());
		cfg.setCategoryRequiresMcRestart(category.name(), category.requireMcRestart());
		cfg.setCategoryRequiresWorldRestart(category.name(), category.requireWorldRestart());
	}
	
	@Override
	public boolean getBoolean(IConfigProp<Boolean> prop) {
		Property forgeProp = cfg.get(prop.category().name(), prop.name(), prop.defaultBool());
		forgeProp.comment = prop.comment();
		forgeProp.setRequiresMcRestart(prop.requireMcRestart());
		forgeProp.setRequiresWorldRestart(prop.requireWorldRestart());
		
		boolean bool = forgeProp.getBoolean();
		prop.set(bool);
		return bool;
	}
	
	@Override
	public int getInt(IConfigProp<Integer> prop) {
		Property forgeProp = cfg.get(prop.category().name(), prop.name(), prop.defaultInt());
		forgeProp.comment = prop.comment();
		forgeProp.setRequiresMcRestart(prop.requireMcRestart());
		forgeProp.setRequiresWorldRestart(prop.requireWorldRestart());
		
		int integer = forgeProp.getInt();
		prop.set(integer);
		return integer;
	}
	
	@Override
	public String getString(IConfigProp<String> prop) {
		Property forgeProp = cfg.get(prop.category().name(), prop.name(), prop.defaultString());
		forgeProp.comment = prop.comment();
		forgeProp.setRequiresMcRestart(prop.requireMcRestart());
		forgeProp.setRequiresWorldRestart(prop.requireWorldRestart());
		
		String string = forgeProp.getString();
		if(string == null) string = "";
		prop.set(string);
		return string;
	}
	
	@Override
	public String getStringSelection(IConfigProp<String> prop) {
		String[] validStrings = prop.validStrings();
		String dflt = validStrings.length > 0 ? validStrings[0] : "";
		return getStringSelection(prop, dflt);
	}
	
	@Override
	public String getStringSelection(IConfigProp<String> prop, String dflt) {
		Property forgeProp = cfg.get(prop.category().name(), prop.name(), prop.defaultString(), prop.comment(), prop.validStrings());
		forgeProp.setRequiresMcRestart(prop.requireMcRestart());
		forgeProp.setRequiresWorldRestart(prop.requireWorldRestart());
		
		String string = forgeProp.getString();
		if(string == null) string = dflt;
		prop.set(string);
		return string;
	}
	
	@Override
	@SuppressWarnings("null")
	public List<String> getStringList(IConfigProp<List<String>> prop) {
		List<String> dflt = prop.defaultStringList();
		Property forgeProp = cfg.get(prop.category().name(), prop.name(), dflt.toArray(new String[dflt.size()]));
		forgeProp.comment = prop.comment();
		forgeProp.setRequiresMcRestart(prop.requireMcRestart());
		forgeProp.setRequiresWorldRestart(prop.requireWorldRestart());
		
		List<String> stringList = Arrays.asList(forgeProp.getStringList());
		if(stringList == null) stringList = Collections.emptyList();
		prop.set(stringList);
		return stringList;
	}
	
	@Override
	@SuppressWarnings("null")
	public int[] getIntList(IConfigProp<Integer[]> prop) {
		Property forgeProp = cfg.get(prop.category().name(), prop.name(), prop.defaultIntList());
		forgeProp.comment = prop.comment();
		forgeProp.setRequiresMcRestart(prop.requireMcRestart());
		forgeProp.setRequiresWorldRestart(prop.requireWorldRestart());
		
		int[] intArray = forgeProp.getIntList();
		if(intArray == null) intArray = ArrayUtils.EMPTY_INT_ARRAY;
		prop.set(ArrayUtils.toObject(intArray));
		return intArray;
	}
}
