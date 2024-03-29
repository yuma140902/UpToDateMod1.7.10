package yuma140902.yumalib.api.items;

import yuma140902.uptodatemod.util.ColorUtil;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * メタデータで16色の色を表現するブロックに対応する汎用のItemBlockです
 *
 * @author yuma1
 */
public class ItemBlockColored extends ItemBlockWithMetadata {

    public ItemBlockColored(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "." + ColorUtil.metaToString(itemStack.getItemDamage());
    }
}
