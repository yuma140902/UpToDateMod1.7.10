package yuma140902.uptodatemod.util;

import javax.annotation.Nullable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackUtil {
	
	/**
	 * アイテムが等しいかどうか比較<br>
	 * Item: 見る<br>
	 * Meta: 見ない<br>
	 * NBT:  見ない<br>
	 * Size: 見ない<br>
	 */
	public static boolean equalsItem(@Nullable ItemStack a, @Nullable ItemStack b) {
		if(a == null && b == null) return true;
		else if(a == null || b == null) return false;
		
		Item itemA = a.getItem();
		Item itemB = b.getItem();
		
		return equals(itemA, itemB);
	}
	
	/**
	 * アイテムが等しいかどうか比較
	 */
	public static boolean equals(@Nullable Item a, @Nullable Item b) {
		if(a == null && b == null) return true;
		else if(a == null || b == null) return false;
		return a == b;
	}
	
	/**
	 * アイテムとメタデータが等しいかどうか比較
	 * メタデータがWILDCARD_VALUEだったらtrue
	 * Item: 見る<br>
	 * Meta: 見る<br>
	 * NBT:  見ない<br>
	 * Size: 見ない<br>
	 */
	public static boolean equalsItemMeta(@Nullable ItemStack a, @Nullable ItemStack b) {
		if(!equalsItem(a, b)) return false;
		
		int metaA = a == null ? 0 : a.getItemDamage();
		int metaB = b == null ? 0 : b.getItemDamage();
		
		if(metaA == OreDictionary.WILDCARD_VALUE || metaB == OreDictionary.WILDCARD_VALUE) {
			return true;
		}
		
		return metaA == metaB;
	}
	
	/**
	 * アイテムとメタデータとNBTが等しいかどうか比較
	 * Item: 見る<br>
	 * Meta: 見る<br>
	 * NBT:  見る<br>
	 * Size: 見ない<br>
	 */
	public static boolean equalsItemNbtMeta(@Nullable ItemStack a, @Nullable ItemStack b) {
		if(!equalsItemMeta(a, b)) return false;
		
		if(a == null && b == null) return true;
		else if(a == null || b == null) return false;
		
		return ItemStack.areItemStackTagsEqual(a, b);
	}
	
}
