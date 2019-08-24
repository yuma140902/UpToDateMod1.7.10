package yuma140902.uptodatemod.tileentity;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.api.UpToDateModAPI;
import yuma140902.uptodatemod.api.recipes.IStonecutterRecipe;
import yuma140902.uptodatemod.util.ItemStackUtil;
import yuma140902.uptodatemod.util.Stat;

public class TileEntityStonecutter extends TileEntity implements ISidedInventory, ITileEntityDroppable {
	public static final String tileEntityId = ModUpToDateMod.MOD_ID + ".stonecutter";
	
	private static final int INVENTORY_SIZE = 2;
	public static final int SLOT_MATERIAL = 0, SLOT_PRODUCT = 1;
	
	private static final int[] slotsSideTop = new int[] { SLOT_MATERIAL };
	private static final int[] slotsBottom = new int[] { SLOT_PRODUCT };
	
	private static final String KEY_RECIPE_INDEX = "RecipeIndex";
	private static final String KEY_SLOT = "Slot";
	private static final String KEY_STATUS = "Status";
	
	private static final int STAT_IDLE = 0;
	private static final int STAT_CRAFTING = 1;
	
	private int status = STAT_IDLE;
	
	private ItemStack[] inventory = new ItemStack[2];
	
	private int guiScroll;
	
	public int getGuiScroll() {
		return guiScroll;
	}
	
	public void setGuiScroll(int guiScroll) {
		this.guiScroll = guiScroll;
	}
	
	private int selectedRecipeIndex = 0;
	
	public int getSelectedRecipeIndex() {
		return selectedRecipeIndex;
	}
	
	public void setRecipeIndex(int selectedRecipeIndex) {
		this.selectedRecipeIndex = selectedRecipeIndex;
		this.markDirty();
	}
	
	// ================= NBT ここから =================
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		tag.setInteger(KEY_RECIPE_INDEX, selectedRecipeIndex);
		tag.setInteger(KEY_STATUS, status);
		
		NBTTagList itemNbtList = new NBTTagList();
		for(int i = 0; i < INVENTORY_SIZE; ++i) {
			if(inventory[i] == null) continue;
			NBTTagCompound itemNbt = new NBTTagCompound();
			itemNbt.setByte(KEY_SLOT, (byte)i);
			inventory[i].writeToNBT(itemNbt);
			itemNbtList.appendTag(itemNbt);
		}
		tag.setTag(KEY_SLOT, itemNbtList);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		selectedRecipeIndex = tag.getInteger(KEY_RECIPE_INDEX);
		status = tag.getInteger(KEY_STATUS);
		
		NBTTagList itemNbtList = tag.getTagList(KEY_SLOT, NBT.TAG_COMPOUND);
		inventory = new ItemStack[INVENTORY_SIZE];
		for(int i = 0; i < INVENTORY_SIZE; ++i) {
			NBTTagCompound itemNbt = itemNbtList.getCompoundTagAt(i);
			byte slot = itemNbt.getByte(KEY_SLOT);
			if(0 <= slot && slot < INVENTORY_SIZE) {
				ItemStack tmp = ItemStack.loadItemStackFromNBT(itemNbt);
				if(tmp != null) {
					inventory[slot] = tmp;
				}
			}
		}
	}
	
	// ================= NBT ここまで =================
	
	
	
	// ================= レシピ処理 ここから =================
	
	@Override
	public void updateEntity() {
		if(status == STAT_IDLE) {
			ItemStack material = inventory[SLOT_MATERIAL];
			if(material == null) {
				return;
			}
			else {
				IStonecutterRecipe recipe = getRecipe(material, selectedRecipeIndex);
				if(recipe == null) {
					return;
				}
				if(material.stackSize < recipe.getMaterialNum()) {
					return;
				}
				ItemStack product = recipe.getProduct();
				int productNum = recipe.getProductNum();
				if(inventory[SLOT_PRODUCT] == null) {
					status = STAT_CRAFTING;
					return;
				}
				else if(ItemStackUtil.equalsItemNbtMeta(product, inventory[SLOT_PRODUCT]) && inventory[SLOT_PRODUCT].stackSize + productNum <= 64) {
					status = STAT_CRAFTING;
					return;
				}
			}
		}
		
		else {
			ItemStack material = inventory[SLOT_MATERIAL];
			IStonecutterRecipe recipe = getRecipe(material, selectedRecipeIndex);
			if(recipe == null) {
				status = STAT_IDLE;
				return;
			}
			ItemStack materialBak = material.copy();
			ItemStack product = recipe.getCraftingResult(material);
			if(material.stackSize <= 0) {
				material = null;
			}
			if(product == null) {
				status = STAT_IDLE;
				return;
			}
			int productNum = product.stackSize;
			if(inventory[SLOT_PRODUCT] == null) {
				inventory[SLOT_PRODUCT] = product;
				inventory[SLOT_MATERIAL] = material;
				return;
			}
			else if(ItemStackUtil.equalsItemNbtMeta(product, inventory[SLOT_PRODUCT]) && inventory[SLOT_PRODUCT].stackSize + productNum <= 64) {
				inventory[SLOT_PRODUCT].stackSize += productNum;
				inventory[SLOT_MATERIAL] = material;
				return;
			}
			else {
				inventory[SLOT_MATERIAL] = materialBak;
				status = STAT_IDLE;
				return;
			}
		}
	}
	
	@Nullable
	private IStonecutterRecipe getRecipe(ItemStack material, int selectedRecipeIndex) {
		if(selectedRecipeIndex < 0) {
			return null;
		}
		int cnt = 0;
		Iterator<IStonecutterRecipe> recipes = UpToDateModAPI.getStonecutterRecipeRegistry().getRecipes(material);
		while (recipes.hasNext()) {
			IStonecutterRecipe recipe = recipes.next();
			if(cnt++ == selectedRecipeIndex) {
				return recipe;
			}
		}
		return null;
	}
	
	// ================= レシピ処理 ここまで =================
	
	
	
	//================= ITileEntityDroppable ここから =================
	
	@Override
	public void drop() {
		TileEntityUtils.dropItems(this, worldObj, xCoord, yCoord, zCoord);
	}
	
	// ================= ITileEntityDroppable ここまで =================
	
	
	
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
