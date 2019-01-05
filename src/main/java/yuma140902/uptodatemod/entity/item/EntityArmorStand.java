package yuma140902.uptodatemod.entity.item;

import java.util.List;
import net.minecraft.entity.Entity;
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
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected void collideWithEntity(Entity entity) {}
	
	@Override
	protected void collideWithNearbyEntities() {
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox());

    for (int i = 0; i < list.size(); ++i)
    {
        Entity entity = (Entity) list.get(i);

        if (this.getDistanceSq(entity.posX, entity.posY, entity.posZ) <= 0.2D)
        {
            entity.applyEntityCollision(this);
        }
    }
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
