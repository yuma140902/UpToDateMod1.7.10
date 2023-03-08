package yuma140902.uptodatemod.items;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.BlockDeepslateBricks;
import yuma140902.yumalib.api.items.ItemBlockMultiName;

public class ItemBlockDeepslateBricks extends ItemBlockMultiName {
    public ItemBlockDeepslateBricks(Block block) {
        super(block, BlockDeepslateBricks.names);
    }
}
