package yuma140902.uptodatemod.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import yuma140902.uptodatemod.tileentity.TileEntityBlastFurnace;

public class BlastFurnaceContainer extends Container {
	
	private TileEntityBlastFurnace tileentity;
	
	public BlastFurnaceContainer(EntityPlayer player, TileEntityBlastFurnace tileentity) {
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, TileEntityBlastFurnace.SLOT_MATERIAL, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, TileEntityBlastFurnace.SLOT_FUEL, 56, 53));
		this.addSlotToContainer(new Slot(tileentity, TileEntityBlastFurnace.SLOT_PRODUCT, 116, 35));
		
		for (int iy = 0; iy < 3; iy++) {
			for (int ix = 0; ix < 9; ix++) {
				this.addSlotToContainer(new Slot(player.inventory, ix + (iy * 9) + 9, 8 + (ix * 18), 84 + (iy * 18)));
			}
		}
		for (int ix = 0; ix < 9; ix++) {
			this.addSlotToContainer(new Slot(player.inventory, ix, 8 + (ix * 18), 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileentity.isUseableByPlayer(player);
	}
}
