package yuma140902.uptodatemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockStone extends ItemBlockWithMetadata {
	public ItemBlockStone(Block block) {
		super(block, block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}
}
