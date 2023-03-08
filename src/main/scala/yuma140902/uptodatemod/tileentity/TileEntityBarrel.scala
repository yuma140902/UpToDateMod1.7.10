package yuma140902.uptodatemod.tileentity

import yuma140902.uptodatemod.ModUpToDateMod
import yuma140902.yumalib.api.tileentity.DroppableTileEntity

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraftforge.common.util.Constants.NBT

class TileEntityBarrel extends DroppableTileEntity with IInventory {
  private val INVENTORY_SIZE: Int = 27
  private val NBT_KEY_SLOT: String = "Slot"
  private val NBT_KEY_ITEMS: String = "Items"
  protected var inventory: Array[ItemStack] = new Array[ItemStack](INVENTORY_SIZE)

  override def writeToNBT(nbt: NBTTagCompound): Unit = {
    super.writeToNBT(nbt)
    val itemNbtList = new NBTTagList
    for (i <- 0 until INVENTORY_SIZE) {
      Option(inventory(i)) match {
        case Some(itemStack) => {
          val itemNbt = new NBTTagCompound
          itemNbt.setByte(NBT_KEY_SLOT, i.toByte)
          itemStack.writeToNBT(itemNbt)
          itemNbtList.appendTag(itemNbt)
        }
        case None =>
      }
    }
    nbt.setTag(NBT_KEY_ITEMS, itemNbtList)
  }

  override def readFromNBT(nbt: NBTTagCompound): Unit = {
    super.readFromNBT(nbt)
    val itemNbtList = Option(nbt.getTagList(NBT_KEY_ITEMS, NBT.TAG_COMPOUND))
    itemNbtList match {
      case Some(itemNbtList) => {
        inventory = new Array[ItemStack](INVENTORY_SIZE)
        for (i <- 0 until INVENTORY_SIZE) {
          Option(itemNbtList.getCompoundTagAt(i)) match {
            case Some(itemNbt) => {
              val slot: Byte = itemNbt.getByte(NBT_KEY_SLOT)
              if (0 <= slot && slot < INVENTORY_SIZE) {
                Option(ItemStack.loadItemStackFromNBT(itemNbt)) match {
                  case Some(itemStack) => inventory(slot) = itemStack
                  case None =>
                }
              }
            }
            case None =>
          }
        }
      }
      case None =>
    }
  }

  override def itemStacksToDrop(): Seq[ItemStack] = inventory.filter(Option(_).isDefined)

  override def getSizeInventory: Int = INVENTORY_SIZE

  override def getStackInSlot(slot: Int): ItemStack = inventory(slot)

  override def getStackInSlotOnClosing(slot: Int): ItemStack = null

  override def decrStackSize(slot: Int, amount: Int): ItemStack = {
    if (inventory(slot) eq null) return null
    var itemStack: ItemStack = null
    if (inventory(slot).stackSize <= amount) {
      itemStack = inventory(slot)
      inventory(slot) = null
      this.markDirty()
      return itemStack
    }
    itemStack = inventory(slot).splitStack(amount)
    if (inventory(slot).stackSize < 1) inventory(slot) = null
    this.markDirty()
    itemStack
  }

  override def setInventorySlotContents(slot: Int, itemStack: ItemStack): Unit = {
    inventory(slot) = itemStack
    if (!(itemStack eq null) && itemStack.stackSize > this.getInventoryStackLimit) itemStack.stackSize = this.getInventoryStackLimit
    this.markDirty()
  }

  override def getInventoryStackLimit = 64

  override def hasCustomInventoryName: Boolean = false

  override def getInventoryName: String = "container." + ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN + ".barrel"

  override def isUseableByPlayer(player: EntityPlayer): Boolean =
    if (worldObj.getTileEntity(xCoord, yCoord, zCoord) ne this) false
    else player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D

  override def openInventory(): Unit = {}

  override def closeInventory(): Unit = {}

  override def isItemValidForSlot(slot: Int, itemStack: ItemStack) = true
}

object TileEntityBarrel {
  val tileEntityId: String = ModUpToDateMod.MOD_ID + ".barrel"
}
