package yuma140902.uptodatemod.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import yuma140902.uptodatemod.blocks.BlockBarrel;

public class BarrelGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == BlockBarrel.GUI_ID) {
			return new BarrelContainer(x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == BlockBarrel.GUI_ID) {
			return new BarrelGuiContainer(x, y, z);
		}
		return null;
	}
	
}
