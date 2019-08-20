package yuma140902.uptodatemod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.util.Stat;

public class TileEntityStonecutter extends TileEntity implements ISidedInventory {
	public static final String tileEntityId = ModUpToDateMod.MOD_ID + ".stonecutter";
	
	private static final int INVENTORY_SIZE = 2;
	public static final int SLOT_MATERIAL = 0, SLOT_PRODUCT = 1;
	
	private static final int[] slotsSideTop = new int[] { SLOT_MATERIAL };
	private static final int[] slotsBottom = new int[] { SLOT_PRODUCT };
	private ItemStack[] inventory = new ItemStack[2];
	
	// ================= ISidedInventory ここから =================
	
	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if(inventory[slot] == null) return null;
		ItemStack itemStack;
		if(inventory[slot].stackSize <= amount) {
			itemStack = inventory[slot];
			inventory[slot] = null;
			this.markDirty();
			return itemStack;
		}
		
		itemStack = inventory[slot].splitStack(amount);
		if(inventory[slot].stackSize < 1) {
			inventory[slot] = null;
		}
		this.markDirty();
		return itemStack;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		inventory[slot] = itemStack;
		if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		
		this.markDirty();
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public String getInventoryName() {
		return "container." + ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN + ".stonecutter";
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false
				: player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return slot == SLOT_MATERIAL;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == Stat.SIDE_BOTTOM) return slotsBottom;
		else return slotsSideTop;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return slot == SLOT_PRODUCT;
	}
	
	
	
	// ================= ISidedInventory ここまで =================
}
