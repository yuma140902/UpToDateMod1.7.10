package yuma140902.yumalib.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ItemWithMetadata {
	public final Item item;
	public final int meta;
	
	public ItemWithMetadata(Item item, int meta){
		this.item = item;
		this.meta = meta;
	}
	
	public ItemWithMetadata(Block block, int meta){
		this.item = Item.getItemFromBlock(block);
		this.meta = meta;
	}
	
	public ItemStack newItemStack(){
		return new ItemStack(item, 1, meta);
	}
	
	public static ItemWithMetadata fromItemStack(ItemStack itemStack){
		return new ItemWithMetadata(itemStack.getItem(), itemStack.getItemDamage());
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ItemWithMetadata)) return false;
		ItemWithMetadata that = (ItemWithMetadata) o;
		return meta == that.meta &&
						Objects.equals(item, that.item);
	}
	
	@Override public int hashCode() {
		return Objects.hash(item, meta);
	}
}
