package yuma140902.uptodatemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class BarrelContainer extends Container {

	private TileEntityBarrel tileEntity;
  
  public BarrelContainer(EntityPlayer player, TileEntityBarrel tileEntity) {
      this.tileEntity = tileEntity;
  }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	
}
