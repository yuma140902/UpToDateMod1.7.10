package yuma140902.uptodatemod.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import yuma140902.uptodatemod.blocks.BlockBarrel;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class BarrelGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != BlockBarrel.GUI_ID || !world.blockExists(x, y, z)) {
			return null;
		}
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityBarrel) {
			return new BarrelContainer(player, (TileEntityBarrel) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != BlockBarrel.GUI_ID || !world.blockExists(x, y, z)) {
			return null;
		}
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityBarrel) {
			return new BarrelGuiContainer(player, (TileEntityBarrel) tileEntity);
		}
		return null;
	}
	
}
