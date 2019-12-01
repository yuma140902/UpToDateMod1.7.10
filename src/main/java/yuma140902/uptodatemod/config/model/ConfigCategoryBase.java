package yuma140902.uptodatemod.config.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ConfigCategoryBase extends ConfigEntryBase implements IConfigCategory {

	public ConfigCategoryBase(@Nullable IConfigCategory category, @Nonnull String name) {
		super(category, category != null ? category.name() + "." + name : name);
	}
	
	@Nonnull protected final List<IConfigEntry> subEntries = new ArrayList<IConfigEntry>();
	@Nonnull protected final List<String> subEntryNames = new ArrayList<String>();
	
	@Override
	public IConfigEntry addSubEntry(Supplier<IConfigEntry> supplier) {
		IConfigEntry entry = supplier.get();
		
		if(requireMcRestart) entry.setRequiresMcRestart();
		
		this.subEntries.add(entry);
		this.subEntryNames.add(entry.name());
		return this;
	}
	
	@SuppressWarnings("null")
	@Override
	public Iterator<IConfigEntry> subEntries() {
		return this.subEntries.iterator();
	}
	
	@Override
	public List<String> propterties() {
		return subEntryNames;
	}
	
	
	
	@Override
	public void setRequiresMcRestart() {
		super.setRequiresMcRestart();
		for(IConfigEntry subEntry : this.subEntries) {
			subEntry.setRequiresMcRestart();
		}
	}
	
	@Override
	public void setRequiresWorldRestart() {
		super.setRequiresWorldRestart();
		for(IConfigEntry subEntry : this.subEntries) {
			subEntry.setRequiresWorldRestart();
		}
	}

}
