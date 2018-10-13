package yuma140902.uptodatemod.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import yuma140902.uptodatemod.IRegisterable;

public class TileEntityObserver extends TileEntity implements IRegisterable {
	private int targetId;
	private int targetMeta;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.targetId = nbt.getInteger("targetId");
		this.targetMeta = nbt.getInteger("targetMeta");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("targetId", targetId);
		nbt.setInteger("targetMeta", targetMeta);
	}
	
	public void setValues(int targetId, int targetMeta) {
		this.targetId = targetId;
		this.targetMeta = targetMeta;
		System.out.println("オブザーバーのタイルエンティティの更新!");
//		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockType);
	}
	
	@Override
	public void register() {
		GameRegistry.registerTileEntity(TileEntityObserver.class, "tileentity_observer");
	}
}
