package yuma140902.uptodatemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class BarrelContainer extends Container {

//座標でGUIを開くか判定するためのもの。
  public int xCoord, yCoord, zCoord;
  public BarrelContainer(int x, int y, int z) {
      this.xCoord = x;
      this.yCoord = y;
      this.zCoord = z;
  }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64D;
	}
	
}
