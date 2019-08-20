package yuma140902.uptodatemod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import yuma140902.uptodatemod.ModUpToDateMod;

public class TileEntityBarrel extends TileEntity implements IInventory, ITileEntityDroppable {
	public static final String tileEntityId = ModUpToDateMod.MOD_ID + ".barrel";
	
	protected static final int INVENTORY_SIZE = 27;
	protected static final String NBT_KEY_SLOT = "Slot";
	protected static final String NBT_KEY_ITEMS = "Items";
	protected ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		NBTTagList itemNbtList = new NBTTagList();
		for(int i = 0; i < INVENTORY_SIZE; ++i) {
			if(inventory[i] == null) continue;
			NBTTagCompound itemNbt = new NBTTagCompound();
			itemNbt.setByte(NBT_KEY_SLOT, (byte)i);
			inventory[i].writeToNBT(itemNbt);
			itemNbtList.appendTag(itemNbt);
		}
		nbt.setTag(NBT_KEY_ITEMS, itemNbtList);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList itemNbtList = nbt.getTagList(NBT_KEY_ITEMS, NBT.TAG_COMPOUND);
		inventory = new ItemStack[INVENTORY_SIZE];
		for(int i = 0; i < INVENTORY_SIZE; ++i) {
			NBTTagCompound itemNbt = itemNbtList.getCompoundTagAt(i);
			byte slot = itemNbt.getByte(NBT_KEY_SLOT);
			if(0 <= slot && slot < INVENTORY_SIZE) {
				ItemStack tmp = ItemStack.loadItemStackFromNBT(itemNbt);
				if(tmp != null) {
					inventory[slot] = tmp;
				}
			}
		}
	}
	
	
	// ================= ITileEntityDroppable ここから =================
	
	@Override
	public void drop() {
		TileEntityUtils.dropItems(this, worldObj, xCoord, yCoord, zCoord);
	}
	
	// ================= ITileEntityDroppable ここまで =================
	
	
	
	// ================= IInventory ここから =================
	
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
		return "container." + ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN + ".barrel";
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
		return true;
	}
	
	// ================= IInventory ここまで =================
}
