package yuma140902.yumalib.api.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import yuma140902.yumalib.api.util.StringUtils;

public class ItemBlockMultiName extends ItemMultiTexture {
	
	public ItemBlockMultiName(Block block, String[] names) {
		super(block, block, names);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		
		if (meta < 0 || meta >= this.field_150942_c.length) 	meta = 0;
		
		return super.getUnlocalizedName() + StringUtils.surfix(".", this.field_150942_c[meta]);
	}
	
}
