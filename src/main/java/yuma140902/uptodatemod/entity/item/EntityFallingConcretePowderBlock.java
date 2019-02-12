package yuma140902.uptodatemod.entity.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;

public class EntityFallingConcretePowderBlock extends EntityFallingBlock {
	
	private int meta;
	
	public EntityFallingConcretePowderBlock(World world) {
		super(world);
	}
	
	public EntityFallingConcretePowderBlock(World world, double posX, double posY, double posZ) {
		this(world, posX, posY, posZ, 0);
	}
	
	public EntityFallingConcretePowderBlock(World world, double posX, double posY, double posZ, int meta) {
		super(world, posX, posY, posZ, MyBlocks.concretePowder, meta);
		this.meta = meta;
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double(this.posY);
		int z = MathHelper.floor_double(this.posZ);
		
		if(this.worldObj.getBlock(x, y, z).getMaterial() == Material.water
		|| this.worldObj.getBlock(x + 1, y, z).getMaterial() == Material.water
		|| this.worldObj.getBlock(x - 1, y, z).getMaterial() == Material.water
		|| this.worldObj.getBlock(x, y, z + 1).getMaterial() == Material.water
		|| this.worldObj.getBlock(x, y, z - 1).getMaterial() == Material.water) {
			this.worldObj.setBlock(x, y, z, MyBlocks.concretePowder, this.meta, 3);
			this.setDead();
		}
	}
	
}
