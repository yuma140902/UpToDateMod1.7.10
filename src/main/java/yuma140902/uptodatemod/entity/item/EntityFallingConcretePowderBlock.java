package yuma140902.uptodatemod.entity.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFallingConcretePowderBlock extends EntityFallingBlock {
	
	private Block block;
	private int meta;
	
	public EntityFallingConcretePowderBlock(World world) {
		super(world);
	}
	
	public EntityFallingConcretePowderBlock(World world, double posX, double posY, double posZ, Block block) {
		this(world, posX, posY, posZ, block, 0);
	}
	
	public EntityFallingConcretePowderBlock(World world, double posX, double posY, double posZ, Block block, int meta) {
		super(world, posX, posY, posZ, block, meta);
		this.block = block;
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
			this.worldObj.setBlock(x, y, z, this.block, this.meta, 3);
			this.setDead();
		}
	}
	
}
