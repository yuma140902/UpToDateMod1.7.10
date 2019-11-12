package yuma140902.uptodatemod.config.data;

import java.util.ArrayList;
import java.util.List;
import yuma140902.uptodatemod.config.model.IConfigEntry;

public class CategoryData implements IConfigData<List<IConfigEntry<?>>> {

	private final List<IConfigEntry<?>> subEntries = new ArrayList<>();
	
	@Override
	public List<IConfigEntry<?>> get() {
		return subEntries;
	}

	@Override
	public void set(List<IConfigEntry<?>> value) {
		throw new RuntimeException("You cannot set value to CategoryData. This is a bug. Please report to the developer.");
	}
	
}
