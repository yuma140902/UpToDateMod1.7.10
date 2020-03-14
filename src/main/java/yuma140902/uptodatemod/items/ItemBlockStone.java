package yuma140902.uptodatemod.items;

import net.minecraft.block.Block;
import yuma140902.mcmodlib.api.items.ItemBlockMultiName;
import yuma140902.uptodatemod.blocks.BlockStone;

public class ItemBlockStone extends ItemBlockMultiName {
	public ItemBlockStone(Block block) {
		super(block, BlockStone.names);
	}
}
