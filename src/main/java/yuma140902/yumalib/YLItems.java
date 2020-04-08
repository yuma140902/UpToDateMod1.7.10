package yuma140902.yumalib;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.items.ItemDummyLogo;
import yuma140902.yumalib.items.ItemGameModeSwitcher;

public class YLItems {
	private static final List<Item> items = new ArrayList<>();
	
	public static List<Item> items(){
		return ImmutableList.copyOf(items);
	}
	
	private static void add(@Nullable Item item) {
		if(item != null) {
			items.add(item);
		}
	}
	
	private static void add(@Nullable Item... itemList) {
		if(itemList == null) return;
		for(final Item item : itemList) {
			add(item);
		}
	}
	
	public static void registerAll() {
		for(final Item item : items) {
			if(item instanceof IRegisterable) {
				((IRegisterable) item).register();
			}
		}
	}
	
	public static final ItemDummyLogo dummyLogo;
	public static final ItemGameModeSwitcher gmSwitcher;
	
	static {
		add(
				dummyLogo = new ItemDummyLogo(),
				gmSwitcher = new ItemGameModeSwitcher(),
				null
		);
	}
}
