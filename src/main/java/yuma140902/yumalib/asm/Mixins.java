package yuma140902.yumalib.asm;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Mixins {
	/**
	 *
	 *
	 * @return 意味はない。置き換え元のメソッドとの兼ね合いでなにかを返す必要がある。
	 */
	public static EntityItem insertItemToPlayerOrDrop(EntityPlayer player, ItemStack itemstack){
		// TODO: 実装
		System.out.println("Success!!!");
		EntityItem entityitem = player.dropPlayerItemWithRandomChoice(itemstack, false);
		if(!player.worldObj.isRemote) {
			System.out.println("give!");
			entityitem.delayBeforeCanPickup = 0;
			entityitem.func_145797_a(player.getCommandSenderName());
			//player.onItemPickup();
		}
		/*if(player.inventory.addItemStackToInventory(itemstack)) {
			//TODO: 拾ったときにインベントリの描画が更新されない
		}
		else {
			player.dropPlayerItemWithRandomChoice(itemstack, false);
		}*/
		return null;
	}
}
