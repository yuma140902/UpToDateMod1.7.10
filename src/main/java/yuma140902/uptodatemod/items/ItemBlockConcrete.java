package yuma140902.uptodatemod.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.util.ColorUtil;

public class ItemBlockConcrete extends ItemBlockWithMetadata {

	public ItemBlockConcrete(Block block) {
		super(block, block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + ColorUtil.metaToString(itemStack.getItemDamage());
	}
}
