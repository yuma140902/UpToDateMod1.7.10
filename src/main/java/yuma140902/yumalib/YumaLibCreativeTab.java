package yuma140902.yumalib;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.yumalib.config.YLConfigCore;

public class YumaLibCreativeTab extends CreativeTabs {
	
	private static YumaLibCreativeTab maybeInstance = null;

	private YumaLibCreativeTab() {
		super(YLConstants.MOD_ID);
	}

	@Override
	public Item getTabIconItem() {
		return YLItems.dummyLogo;
	}
	
	public static void setToTab(Item item) {
		if(maybeInstance == null && YLConfigCore.showCreativeTab) maybeInstance = new YumaLibCreativeTab();
		if(YLConfigCore.showCreativeTab) item.setCreativeTab(maybeInstance);
	}
	
	public static void setToTab(Block block) {
		if(maybeInstance == null && YLConfigCore.showCreativeTab) maybeInstance = new YumaLibCreativeTab();
		if(YLConfigCore.showCreativeTab) block.setCreativeTab(maybeInstance);
	}
	
}
