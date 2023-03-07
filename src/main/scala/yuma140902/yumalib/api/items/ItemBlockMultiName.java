package yuma140902.yumalib.api.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import yuma140902.yumalib.api.util.StringUtils;

/**
 * 複数のメタを持ち、メタによって名前が変わるブロックに対応するItemBlock
 */
public class ItemBlockMultiName extends ItemMultiTexture {

	/**
	 * @param block ブロック
	 * @param names langファイルのキーの末尾につけられる文字列のリスト
	 */
	public ItemBlockMultiName(Block block, String[] names) {
		super(block, block, names);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		
		if (meta < 0 || meta >= this.field_150942_c.length) 	meta = 0;
		
		return super.getUnlocalizedName() + StringUtils.suffix(".", this.field_150942_c[meta]);
	}
	
}
