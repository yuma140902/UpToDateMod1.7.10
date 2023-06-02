package yuma140902.uptodatemod.config.model;

import java.util.List;
import javax.annotation.Nonnull;

public interface IConfigProp<T> extends IConfigEntry {
	@Nonnull IConfigCategory category();
	
	void set(@Nonnull T value);
	@Nonnull
	T get();
	
	
	void setDefault(boolean bool);
	boolean defaultBool();
	
	void setDefault(int integer);
	int defaultInt();
	
	void setDefault(@Nonnull String string);
	@Nonnull String defaultString();
	
	void setValidStrings(@Nonnull String... validStrings);
	@Nonnull String[] validStrings();
	
	void setDefaultStringList(@Nonnull List<String> list);
	@Nonnull List<String> defaultStringList();
	
	void setDefaultIntList(@Nonnull int... list);
	@Nonnull int[] defaultIntList();
}
