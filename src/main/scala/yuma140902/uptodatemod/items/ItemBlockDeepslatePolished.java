package yuma140902.uptodatemod.items;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.BlockDeepslateBricks;
import yuma140902.uptodatemod.blocks.BlockDeepslatePolished;
import yuma140902.yumalib.api.items.ItemBlockMultiName;

public class ItemBlockDeepslatePolished extends ItemBlockMultiName {
    public ItemBlockDeepslatePolished(Block block) {
        super(block, BlockDeepslatePolished.names);
    }
}
