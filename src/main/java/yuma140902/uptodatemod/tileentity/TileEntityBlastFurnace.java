package yuma140902.uptodatemod.tileentity;

import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import yuma140902.uptodatemod.Recipes;
import yuma140902.uptodatemod.recipe.BlastFurnaceRecipe;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public class TileEntityBlastFurnace extends TileEntity implements ISidedInventory, ITileEntityDroppable {
	public static final String tileEntityId = StringUtil.getDomainedUnlocalizedName("blastFurnace");
	
	public static final String INVENTORY_NAME = "container." + tileEntityId;
	
	public static final int MAX_PROC_TICK = 100;
	public static final int MAX_FUEL_TICK = 50;
	
	protected static final String
		NBTKEY_SLOT_MATERIAL = "SlotMaterial",
		NBTKEY_SLOT_FUEL = "SlotFuel",
		NBTKEY_SLOT_PRODUCT = "SlotProduct",
		NBTKEY_PROC_TICK = "ProcTick",
		NBTKEY_FUEL_TICK = "FuelTick",
		NBTKEY_STATUS = "Status";
	
	protected static final int
		STATUS_IDLING = 0,
		STATUS_PROCESSING = 1;
	
	protected static final int INVENTORY_SIZE = 3;
	
	public static final int
		SLOT_MATERIAL = 0,
		SLOT_FUEL = 1,
		SLOT_PRODUCT = 2;
	
	
	@Nullable
	protected ItemStack slotMaterial;
	@Nullable
	protected ItemStack slotFuel;
	@Nullable
	protected ItemStack slotProduct;
	public int procTick = 0;
	public int fuelTick = 0;
	protected int status = STATUS_IDLING;
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		NBTTagCompound tagMaterial = new NBTTagCompound();
		if(slotMaterial != null) {
			slotMaterial.writeToNBT(tagMaterial);
			tag.setTag(NBTKEY_SLOT_MATERIAL, tagMaterial);
		}
		
		NBTTagCompound tagFuel = new NBTTagCompound();
		if(slotFuel != null) {
			slotFuel.writeToNBT(tagFuel);
			tag.setTag(NBTKEY_SLOT_FUEL, tagFuel);
		}
		
		NBTTagCompound tagProduct = new NBTTagCompound();
		if(slotProduct != null) {
			slotProduct.writeToNBT(tagProduct);
			tag.setTag(NBTKEY_SLOT_PRODUCT, tagProduct);
		}
		
		tag.setInteger(NBTKEY_PROC_TICK, this.procTick);
		tag.setInteger(NBTKEY_FUEL_TICK, this.fuelTick);
		tag.setInteger(NBTKEY_STATUS, this.status);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		slotMaterial = slotFuel = slotProduct = null;
		
		if(tag.hasKey(NBTKEY_SLOT_MATERIAL)) {
			NBTTagCompound tagMaterial = tag.getCompoundTag(NBTKEY_SLOT_MATERIAL);
			slotMaterial = ItemStack.loadItemStackFromNBT(tagMaterial);
		}
		if(tag.hasKey(NBTKEY_SLOT_FUEL)) {
			NBTTagCompound tagFuel = tag.getCompoundTag(NBTKEY_SLOT_FUEL);
			slotFuel = ItemStack.loadItemStackFromNBT(tagFuel);
		}
		if(tag.hasKey(NBTKEY_SLOT_PRODUCT)) {
			NBTTagCompound tagProduct = tag.getCompoundTag(NBTKEY_SLOT_PRODUCT);
			slotProduct = ItemStack.loadItemStackFromNBT(tagProduct);
		}
		
		procTick = fuelTick = 0;
		status = STATUS_IDLING;
		
		if(tag.hasKey(NBTKEY_PROC_TICK)) {
			procTick = tag.getInteger(NBTKEY_PROC_TICK);
		}
		if(tag.hasKey(NBTKEY_FUEL_TICK)) {
			fuelTick = tag.getInteger(NBTKEY_FUEL_TICK);
		}
		if(tag.hasKey(NBTKEY_STATUS)) {
			status = tag.getInteger(NBTKEY_STATUS);
		}
	}
	
	
	// ================= データ同期 ここから =================
	
	@Override
	public Packet getDescriptionPacket() {
		return TileEntitySyncer.getDescriptionPacket(this);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		TileEntitySyncer.onDataPacket(this, net, pkt);
	}
	
	// ================= データ同期 ここまで =================
	
	
	
	// ================= レシピ処理 ここから =================
	
	@Override
	public void updateEntity() {
		if(status == STATUS_IDLING) {
			startProcessingIfNecessary();
		}
		else if(status == STATUS_PROCESSING) {
			processTick();
		}
		else {
			status = STATUS_IDLING;
		}
	}
	
	private void startProcessingIfNecessary() {
		if(slotMaterial == null) {
			return;
		}
		
		BlastFurnaceRecipe recipe = Recipes.blastFurnace.getRecipe(slotMaterial);
		if(recipe == null) {
			return;
		}
		
		int productAmount = recipe.getProduct().stackSize + (slotProduct == null ? 0 : slotProduct.stackSize);
		if(productAmount <= this.getSizeInventory() && slotFuel != null) {
			status = STATUS_PROCESSING;
		}
	}
	
	/**
	 * 処理を進めます
	 */
	private void processTick() {
		
		if(procTick >= MAX_PROC_TICK) {
			BlastFurnaceRecipe recipe = Recipes.blastFurnace.getRecipe(slotMaterial);
			
			if(this.slotProduct == null) {
				this.slotProduct = (recipe != null) ? recipe.getProduct() : null;
			}
			else {
				@Nonnull
				ItemStack product = (recipe != null) ? recipe.getProduct() : null;
				this.slotProduct.stackSize += product.stackSize;
			}
			
			procTick = 0;
			if(slotMaterial != null && recipe != null) {
				this.slotMaterial.stackSize -= recipe.getMaterial().stackSize;
				
				if(this.slotMaterial.stackSize <= 0) {
					this.slotMaterial = null;
				}
			}
		}
		else ++procTick;
		
		if(fuelTick >= MAX_FUEL_TICK) {
			fuelTick = 0;
			--this.slotFuel.stackSize;
			
			if(this.slotFuel.stackSize <= 0) {
				this.slotFuel = null;
			}
		}
		else ++fuelTick;
		
		if(this.slotMaterial == null || this.slotFuel == null) {
			procTick = 0;
			status = STATUS_IDLING;
			return;
		}
	}
	
	public int getStatus() {
		return status;
	}
	
	// ================= レシピ処理 ここまで =================
	
	
	
	// ================= ITileEntityDroppable ここから =================
	
	private void dropItemStack(ItemStack itemstack) {
		if(itemstack != null && !worldObj.isRemote) {
			Random rand = worldObj.rand;
			float xDiff = rand.nextFloat() * 0.6F + 0.1F;
			float yDiff = rand.nextFloat() * 0.6F + 0.1F;
			float zDiff = rand.nextFloat() * 0.6F + 0.1F;
			
			EntityItem entityItem = new EntityItem(worldObj, xCoord + xDiff, yCoord + yDiff, zCoord + zDiff, itemstack.copy());
			float motionScale = 0.025F;
			entityItem.motionX = (float) rand.nextGaussian() * motionScale;
			entityItem.motionY = (float) rand.nextGaussian() * motionScale + 0.1F;
			entityItem.motionZ = (float) rand.nextGaussian() * motionScale;
			worldObj.spawnEntityInWorld(entityItem);
		}
	}
	
	public void drop() {
		dropItemStack(slotMaterial);
		dropItemStack(slotFuel);
		dropItemStack(slotProduct);
	}
	
	// ================= ITileEntityDroppable ここまで =================
	
	
	
	// ================= IInventory ここから =================
	
	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		if(slot == SLOT_MATERIAL) return slotMaterial;
		else if(slot == SLOT_FUEL) return slotFuel;
		else return slotProduct;
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack targetItemStack = getStackInSlot(slot);
		
		if(targetItemStack == null) return null;
		ItemStack itemStack;
		if(targetItemStack.stackSize <= amount) {
			itemStack = targetItemStack;
			targetItemStack = null;
			this.markDirty();
			return itemStack;
		}
		
		itemStack = targetItemStack.splitStack(amount);
		if(targetItemStack.stackSize < 1) {
//			targetItemStack = null;
			if(slot == SLOT_MATERIAL) slotMaterial = null;
			else if(slot == SLOT_FUEL) slotFuel = null;
			else if(slot == SLOT_PRODUCT) slotProduct = null;
		}
		this.markDirty();
		return itemStack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		System.out.println("setinvsolot " + (itemstack == null ? "null" : itemstack.getDisplayName() + "*" + itemstack.stackSize));
		if(!isItemValidForSlot(slot, itemstack)) {
			return;
		}
		
		if(slot == SLOT_MATERIAL) slotMaterial = itemstack;
		else if(slot == SLOT_FUEL) slotFuel = itemstack;
		else if(slot == SLOT_PRODUCT) slotProduct = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
    }
		
		this.markDirty();
	}
	
	@Override
	public String getInventoryName() {
		return INVENTORY_NAME;
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		if(slot == SLOT_MATERIAL) {
			return true;
		}
		else if(slot == SLOT_FUEL) {
			return isFuel(itemstack);
		}
		else {
			return false;
		}
	}
	
	// ================= IInventory ここまで =================
	
	
	
	// ================= その他のスロット処理 ここから =================
	
	public static boolean isFuel(ItemStack itemstack) {
		return TileEntityFurnace.isItemFuel(itemstack);
	}
	
	// ================= その他のスロット処理 ここまで =================
	
	
	
	// ================= ISidedInventory ここから =================
	
	protected static final int[] slotsBottom = new int[] {SLOT_PRODUCT, SLOT_FUEL};
	protected static final int[] slotsTop = new int[] {SLOT_MATERIAL};
	protected static final int[] slotsSides = new int[] {SLOT_FUEL};
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == Stat.SIDE_BOTTOM) {
			return slotsBottom;
		}
		else if(side == Stat.SIDE_TOP) {
			return slotsTop;
		}
		else {
			return slotsSides;
		}
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return this.isItemValidForSlot(slot, itemstack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return side != Stat.SIDE_BOTTOM || slot != SLOT_FUEL || itemstack.getItem() == Items.bucket;
	}
	
	// ================= ISidedInventory ここまで =================

}
