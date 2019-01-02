package yuma140902.uptodatemod.entity.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityArmorStand extends EntityLiving {

	public EntityArmorStand(World world) {
		super(world);
		this.setSize(1.0F, 1.0F);
		this.yOffset = 2.0F;
	}
	
	public EntityArmorStand(World world, int x, int y, int z) {
		this(world);
		this.setPosition(x + 0.5, y, z + 0.5);
	}

	@Override
	protected void entityInit() {
		// TODO 自動生成されたメソッド・スタブ
		super.entityInit();
	}
	
	@Override
	public void onUpdate() {
		// TODO 自動生成されたメソッド・スタブ
		super.onUpdate();
		this.rotationYaw = 0;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public ItemStack getHeldItem() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
