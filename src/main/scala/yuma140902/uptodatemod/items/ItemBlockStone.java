package yuma140902.uptodatemod.items;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.yumalib.api.items.ItemBlockMultiName;

public class ItemBlockStone extends ItemBlockMultiName {
	public ItemBlockStone(Block block) {
		super(block, BlockStone.names);
	}
}
