package yuma140902.uptodatemod.config.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ConfigCategoryBase extends ConfigEntryBase implements IConfigCategory {

	ConfigCategoryBase(@Nullable IConfigCategory category, @Nonnull String name) {
		super(category, category != null ? category.name() + "." + name : name);
	}
	
	protected final List<IConfigEntry> subEntries = new ArrayList<IConfigEntry>();
	protected final List<String> subEntryNames = new ArrayList<String>();
	
	@Override
	public IConfigEntry addSubEntry(String name, ICategoryEntryFactory factory) {
		IConfigEntry entry = factory.build(this, name);
		this.subEntries.add(entry);
		this.subEntryNames.add(entry.name());
		return entry;
	}
	
	@SuppressWarnings("null")
	@Override
	public Iterator<IConfigEntry> subEntries() {
		return this.subEntries.iterator();
	}
	
	@Override
	public IConfigCategory addCommentLine(String comment) {
		return (IConfigCategory) super.addCommentLine(comment);
	}
	
	@Override
	public IConfigCategory setRequiresMcRestart() {
		super.setRequiresMcRestart();
		for(IConfigEntry subEntry : this.subEntries) {
			subEntry.setRequiresMcRestart();
		}
		return this;
	}

}
