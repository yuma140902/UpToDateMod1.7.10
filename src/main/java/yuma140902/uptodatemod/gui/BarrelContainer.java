package yuma140902.uptodatemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class BarrelContainer extends Container {

	private TileEntityBarrel tileEntity;
  
  public BarrelContainer(EntityPlayer player, TileEntityBarrel tileEntity) {
  	this.tileEntity = tileEntity;
      
    // プレイヤーと樽のインベントリを設定
  	for(int y = 0; y < 3; ++y) {
  		for(int x = 0; x < 9; ++x) {
  			this.addSlotToContainer(new Slot(tileEntity, x+(y*9), 8+(x*18), 18+(y*18)));
  			this.addSlotToContainer(new Slot(player.inventory, x+(y*9)+9, 8+(x*18), 84+(y*18)));
  		}
  	}
  	
  	// プレイヤーのインベントリ(下の9つ)を設定
  	for(int x = 0; x < 9; ++x) {
  		this.addSlotToContainer(new Slot(player.inventory, x, 8+(x*18), 142));
  	}
  }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	
}
