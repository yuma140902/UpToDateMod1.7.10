package yuma140902.uptodatemod.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.util.ColorUtil;

/**
 * メタデータで16色の色を表現するブロックに対応する汎用のItemBlockです
 * @author yuma1
 *
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
