package yuma140902.uptodatemod.gui;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import yuma140902.uptodatemod.tileentity.TileEntityStonecutter;

@ChestContainer
public class StonecutterContainer extends Container {
	
	private TileEntityStonecutter tileentity;
	
	public StonecutterContainer(EntityPlayer player, TileEntityStonecutter tileentity) {
		this.tileentity = tileentity;
		
		// Stonecutterのインベントリを設定
		this.addSlotToContainer(new Slot(tileentity, TileEntityStonecutter.SLOT_MATERIAL, 20, 33));
		this.addSlotToContainer(new Slot(tileentity, TileEntityStonecutter.SLOT_PRODUCT, 143, 33));
		
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
}
