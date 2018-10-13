package yuma140902.uptodatemod.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import yuma140902.uptodatemod.IRegisterable;

public class TileEntityObserver extends TileEntity implements IRegisterable {
	private int blockId;
	private int blockMeta;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.blockId = nbt.getInteger("blockId");
		this.blockMeta = nbt.getInteger("blockMeta");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("blockId", blockId);
		nbt.setInteger("blockMeta", blockMeta);
	}
	
	public void setValues(int blockId, int blockMeta) {
		this.blockId = blockId;
		this.blockMeta = blockMeta;
		System.out.println("オブザーバーのタイルエンティティの更新!");
//		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockType);
	}
	
	@Override
	public void register() {
		GameRegistry.registerTileEntity(TileEntityObserver.class, "tileentity_observer");
	}
}
