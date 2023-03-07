package yuma140902.yumalib;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.yumalib.config.YLConfigCore;

/**
 * YumaLibのクリエイティブタブ
 */
public class YumaLibCreativeTab extends CreativeTabs {

	/*
	 * このクラスの唯一のインスタンス
	 * setToTab()の呼び出しのタイミングでnullならnewされる
	 */
	private static YumaLibCreativeTab maybeInstance = null;

	private YumaLibCreativeTab() {
		super(YLConstants.MOD_ID);
	}

	@Override
	public Item getTabIconItem() {
		return YLItems.dummyLogo();
	}

	/**
	 * アイテムをこのタブに表示するよう設定する
	 * @param item 対象のアイテム
	 */
	public static void setToTab(Item item) {
		if(maybeInstance == null && YLConfigCore.showCreativeTab) maybeInstance = new YumaLibCreativeTab();
		if(YLConfigCore.showCreativeTab) item.setCreativeTab(maybeInstance);
	}

	/**
	 * ブロックをこのタブに表示するよう設定する
	 * @param block 対象のブロック
	 */
	public static void setToTab(Block block) {
		if(maybeInstance == null && YLConfigCore.showCreativeTab) maybeInstance = new YumaLibCreativeTab();
		if(YLConfigCore.showCreativeTab) block.setCreativeTab(maybeInstance);
	}
	
}
