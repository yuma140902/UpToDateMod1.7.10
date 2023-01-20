package yuma140902.yumalib.api.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import yuma140902.yumalib.api.blocks.BlockGenericSlab;

/**
 * {@link BlockGenericSlab}に対応するItemBlock
 */
public class ItemBlockGenericSlab extends ItemSlab {
	
	private final BlockGenericSlab slab;

	// ジェネリクスで呼び出される関係で引数の型をBlockGenericSlabにできない
	public ItemBlockGenericSlab(Block block) {
		super(block, ((BlockGenericSlab)block).getSlab(), ((BlockGenericSlab)block).getSlabDouble(), ((BlockGenericSlab)block).isDouble());
		this.slab = (BlockGenericSlab) block;
	}

	@Override
	public String getUnlocalizedName(ItemStack p_77667_1_) {
		return slab.getUnlocalizedName();
	}
}
