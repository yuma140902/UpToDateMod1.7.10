package yuma140902.mcmodlib.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import yuma140902.mcmodlib.blocks.BlockGenericSlab;

public class ItemBlockGenericSlab extends ItemSlab {
	
	private BlockGenericSlab slab;
	
	public ItemBlockGenericSlab(Block block) {
		super(block, ((BlockGenericSlab)block).getSlab(), ((BlockGenericSlab)block).getSlabDouble(), ((BlockGenericSlab)block).isDouble());
		this.slab = (BlockGenericSlab) block;
	}

	@Override
	public String getUnlocalizedName(ItemStack p_77667_1_) {
		return slab.getUnlocalizedName();
	}
}
