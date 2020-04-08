package yuma140902.yumalib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class YumaLibCreativeTab extends CreativeTabs {

	public YumaLibCreativeTab() {
		super(YLConstants.MOD_ID);
	}

	@Override
	public Item getTabIconItem() {
		return YLItems.dummyLogo;
	}
	
}
