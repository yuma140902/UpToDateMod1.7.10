package yuma140902.uptodatemod.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemStackComparer {
	private ItemStackComparer() {}
	
	/**
	 * アイテムの種類、メタデータ、NBT、メタデータのワイルドカードを使用して
	 * 2つのItemStackを比較します
	 * 数は無視されます
	 * @param left
	 * @param right
	 */
	public static boolean areItemStacksEqual(ItemStack left, ItemStack right) {
		if(left == null && right == null) {
			return true;
		}
		if(left != null && right != null) {
			int leftMeta = left.getItemDamage();
			int rightMeta = right.getItemDamage();
			
			return left.isItemEqual(right)
					&& (leftMeta == rightMeta || (leftMeta == OreDictionary.WILDCARD_VALUE && rightMeta == OreDictionary.WILDCARD_VALUE))
					&& ItemStack.areItemStackTagsEqual(left, right);
		}
		return false;
	}
}
