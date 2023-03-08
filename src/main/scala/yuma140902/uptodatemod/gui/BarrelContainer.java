package yuma140902.uptodatemod.gui;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

@ChestContainer
public class BarrelContainer extends Container {
	
	private TileEntityBarrel tileEntity;
	
	/*
	 * スロット番号の配置
	 * 0 1 2 3 4 5 6 7 8
	 * 9 10 11 12 13 14 15 16 17
	 * 18 19 20 21 22 23 24 25 26
	 * --------------------------
	 * 36 37 38 39 40 41 42 43 44
	 * 45 46 47 48 49 50 51 52 53
	 * 54 55 56 57 58 59 60 61 62
	 * --------------------------
	 * 27 28 29 30 31 32 33 34 35
	 */
	@SuppressWarnings("unused")
	private static final int 
		barrelIndexStart = 0, 
		barrelIndexEnd = 26, 
		hotbarIndexStart = 27, 
		hotbarIndexEnd = 35,
		playerIndexStart = 36, 
		playerIndexEnd = 62, 
		numSlots = 63;
	
	
	public BarrelContainer(EntityPlayer player, TileEntityBarrel tileEntity) {
		this.tileEntity = tileEntity;
		
		// 樽のインベントリを設定
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(tileEntity, x + (y * 9), 8 + (x * 18), 18 + (y * 18)));
			}
		}
		
		// プレイヤーのインベントリを設定
		for(int y = 0; y < 3; ++y) {
			for(int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(player.inventory, x+(y*9)+9, 8+(x*18), 84+y*18));
			}
		}
		
		// プレイヤーのインベントリ(下の9つ)を設定
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(player.inventory, x, 8 + (x * 18), 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotNumber);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (slotNumber <= barrelIndexEnd) {
				if (!this.mergeItemStack(itemstack1, hotbarIndexStart, playerIndexEnd + 1, true)) {
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, barrelIndexStart, barrelIndexEnd + 1, false)) {
				return null;
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			}
			else {
				slot.onSlotChanged();
			}
		}
		
		return itemstack;
	}
	
}
