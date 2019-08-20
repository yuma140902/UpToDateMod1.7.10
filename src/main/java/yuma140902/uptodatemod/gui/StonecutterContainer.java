package yuma140902.uptodatemod.gui;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.tileentity.SlotStonecutterProduct;
import yuma140902.uptodatemod.tileentity.TileEntityStonecutter;

@ChestContainer
public class StonecutterContainer extends Container {
	
	private TileEntityStonecutter tileentity;
	
	/*
	 * スロット番号の配置
	 *  0                       1
	 * --------------------------
	 * 11 12 13 14 15 16 17 18 19
	 * 20 21 22 23 24 25 26 27 28
	 * 29 30 31 32 33 34 35 36 37
	 * --------------------------
	 *  2  3  4  5  6  7  8  9 10
	 */
	private static final int
		stonecutterIndexStart = 0,
		stonecutterIndexEnd = 1,
		hotbarIndexStart = 2,
		playerIndexEnd = 37;
	
	public StonecutterContainer(EntityPlayer player, TileEntityStonecutter tileentity) {
		this.tileentity = tileentity;
		
		// Stonecutterのインベントリを設定
		this.addSlotToContainer(new Slot(tileentity, TileEntityStonecutter.SLOT_MATERIAL, 20, 33));
		this.addSlotToContainer(new SlotStonecutterProduct(tileentity, TileEntityStonecutter.SLOT_PRODUCT, 143, 33));
		
		// プレイヤーのインベントリを設定
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(player.inventory, x + (y * 9) + 9, 8 + (x * 18), 84 + y * 18));
			}
		}
		
		// プレイヤーのインベントリ(下の9つ)を設定
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(player.inventory, x, 8 + (x * 18), 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileentity.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNo) {
		Slot slot = (Slot) this.inventorySlots.get(slotNo);
		
		if(slot == null || !slot.getHasStack()) {
			return null;
		}
		
		ItemStack itemstack = slot.getStack();
		ItemStack itemstackBak = itemstack.copy();
		
		if(slotNo <= stonecutterIndexEnd) {
			if(!mergeItemStack(itemstack, hotbarIndexStart, playerIndexEnd+1, true)) {
				return null;
			}
		}
		else {
			if(!mergeItemStack(itemstack, stonecutterIndexStart, stonecutterIndexStart+1, false)) {
				return null;
			}
		}
		
		if (itemstack.stackSize == 0) {
			slot.putStack((ItemStack) null);
		}
		else {
			slot.onSlotChanged();
		}
		
		return itemstackBak;
	}
}
