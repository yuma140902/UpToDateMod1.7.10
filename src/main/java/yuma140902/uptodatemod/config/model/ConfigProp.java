package yuma140902.uptodatemod.config.model;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.ArrayUtils;

public class ConfigProp<T> extends ConfigEntryBase implements IConfigProp<T> {
	
	@Nonnull
	protected final IConfigCategory category;
	
	protected T value;
	
	protected boolean defaultBool = false;
	protected int defaultInt = 0;
	@Nonnull protected String defaultString = "";
	@SuppressWarnings("null")
	@Nonnull protected List<String> defaultStringList = Collections.emptyList();
	@Nonnull protected int[] defaultIntList = new int[0];
	
	@SuppressWarnings("null")
	@Nonnull protected String[] validStrings = ArrayUtils.EMPTY_STRING_ARRAY;
	

	public ConfigProp(@Nonnull IConfigCategory category, @Nonnull String name) {
		this(category, name, name);
	}
	
	public ConfigProp(@Nonnull IConfigCategory category, @Nonnull String name, @Nonnull String localizationKey) {
		super(category, name, localizationKey);
		this.category = category;
	}
	
	@Nonnull
	@Override
	public IConfigCategory category() {
		return this.category;
	}
	

	
	@Override
	public void set(T value) {
		this.value = value;
	}
	
	@SuppressWarnings("null")
	@Override
	public T get() {
		return this.value;
	}
	
	
	
	@Override
	public void setDefault(boolean bool) {
		this.defaultBool = bool;
	}
	@Override
	public boolean defaultBool() {
		return this.defaultBool;
	}

	@Override
	public void setDefault(int integer) {
		this.defaultInt = integer;
	}
	@Override
	public int defaultInt() {
		return this.defaultInt;
	}

	@Override
	public void setDefault(String string) {
		this.defaultString = string;
	}
	@Override
	public String defaultString() {
		return this.defaultString;
	}
	
	@Override
	public void setValidStrings(@Nonnull String... validStrings) {
		this.validStrings = validStrings;
	}
	@Override
	public String[] validStrings() {
		return this.validStrings;
	}

	@Override
	public void setDefaultStringList(List<String> list) {
		this.defaultStringList = list;
	}
	@Override
	public List<String> defaultStringList() {
		return this.defaultStringList;
	}

	@Override
	public void setDefaultIntList(int... list) {
		this.defaultIntList = list;
	}
	@Override
	public int[] defaultIntList() {
		return this.defaultIntList;
	}

}
