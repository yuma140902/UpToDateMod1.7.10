package yuma140902.uptodatemod.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import yuma140902.uptodatemod.gui.inventory.BlastFurnaceContainer;
import yuma140902.uptodatemod.gui.inventory.BlastFurnaceGuiContainer;
import yuma140902.uptodatemod.tileentity.TileEntityBlastFurnace;

public class ModGuiHandler implements IGuiHandler {
	public static ModGuiHandler instance = new ModGuiHandler();
	
	private ModGuiHandler() {}
	
	public static final int
		BLAST_FURNACE = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == BLAST_FURNACE) {
			return new BlastFurnaceContainer(player, (TileEntityBlastFurnace)world.getTileEntity(x, y, z));
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == BLAST_FURNACE) {
			return new BlastFurnaceGuiContainer(player, (TileEntityBlastFurnace)world.getTileEntity(x, y, z));
		}
		return null;
	}
}
