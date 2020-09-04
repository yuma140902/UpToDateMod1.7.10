package yuma140902.yumaessentials;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class YECreativeTab extends CreativeTabs {
	
	private static YECreativeTab maybeInstance = null;

	private YECreativeTab() {
		super(YEConstants.MOD_ID);
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Blocks.grass);
	}
	
	public static void setToTab(Item item) {
		if(maybeInstance == null) maybeInstance = new YECreativeTab();
		item.setCreativeTab(maybeInstance);
	}
	
	public static void setToTab(Block block) {
		if(maybeInstance == null) maybeInstance = new YECreativeTab();
		block.setCreativeTab(maybeInstance);
	}
	
}
