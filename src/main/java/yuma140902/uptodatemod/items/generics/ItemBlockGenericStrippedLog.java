package yuma140902.uptodatemod.items.generics;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenericStrippedLog extends ItemBlockWithMetadata {
	public ItemBlockGenericStrippedLog(Block blockStrippedLog) {
		super(blockStrippedLog, blockStrippedLog);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		return this.getUnlocalizedName() + ((meta & 0b0100) == 0b0100 ? "_wood" : "");
	}
}
