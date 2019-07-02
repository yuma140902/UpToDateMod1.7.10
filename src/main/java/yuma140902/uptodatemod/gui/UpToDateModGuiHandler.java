package yuma140902.uptodatemod.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyGuis;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class UpToDateModGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(ID) {
			case MyGuis.ID_BARREL:
				return new BarrelContainer(player, (TileEntityBarrel) tileEntity);
				
			default: return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(ID) {
			case MyGuis.ID_BARREL:
				return new BarrelGuiContainer(player, (TileEntityBarrel) tileEntity);
				
			default: return null;
		}
	}
	
}
