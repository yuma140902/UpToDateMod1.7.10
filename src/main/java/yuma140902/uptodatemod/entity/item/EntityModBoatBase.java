package yuma140902.uptodatemod.entity.item;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class EntityModBoatBase extends EntityBoat {
	public static enum Type {
		ACACIA, BIRCH, DARK_OAK, JUNGLE, SPRUCE
	}
	
	protected abstract Type getType();
	
	protected abstract Item getItemBoat();
	
	protected abstract int getPlankMeta();
	
	/** true if no player in boat */
	private boolean isBoatEmpty;
	private double speedMultiplier;
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	
	public EntityModBoatBase(World world) {
		super(world);
		this.isBoatEmpty = true;
		this.speedMultiplier = 0.07D;
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 0.6F);
		this.yOffset = this.height / 2.0F;
	}
	
	@Override
	public ItemStack getPickedResult(MovingObjectPosition target) {
		return new ItemStack(getItemBoat());
	}
	
	/**
	 * Returns a boundingBox used to collide the entity with other entities and
	 * blocks. This enables the entity to be
	 * pushable on contact, like boats or minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox(Entity entityBoat) {
		return entityBoat.boundingBox;
	}
	
	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}
	
	/**
	 * Returns the Y offset from the entity's position for any entity riding this
	 * one.
	 */
	@Override
	public double getMountedYOffset() {
		return (double) this.height * 0.0D - 0.30000001192092896D;
	}
	
	/**
	 * Returns true if other Entities should be prevented from moving through this
	 * Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
	
	/**
	 * Sets the position and rotation. Only difference from the other one is no
	 * bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void setPositionAndRotation2(double par1d, double par2d, double par3d, float par4d, float par5f, int par6i) {
		if (this.isBoatEmpty) {
			this.boatPosRotationIncrements = par6i + 5;
		}
		else {
			double d3 = par1d - this.posX;
			double d4 = par2d - this.posY;
			double d5 = par3d - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;
			
			if (d6 <= 1.0D) {
				return;
			}
			
			this.boatPosRotationIncrements = 3;
		}
		
		this.boatX = par1d;
		this.boatY = par2d;
		this.boatZ = par3d;
		this.boatYaw = (double) par4d;
		this.boatPitch = (double) par5f;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}
	
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void setVelocity(double x, double y, double z) {
		this.velocityX = this.motionX = x;
		this.velocityY = this.motionY = y;
		this.velocityZ = this.motionZ = z;
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0F));
	}
	
	public EntityModBoatBase(World world, double posX, double posY, double posZ) {
		this(world);
		this.setPosition(posX, posY + (double) this.yOffset, posZ);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;
	}
	
	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float par2f) {
		if (this.isEntityInvulnerable()) {
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead) {
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + par2f * 10.0F);
			this.setBeenAttacked();
			boolean flag = damageSource.getEntity() instanceof EntityPlayer
					&& ((EntityPlayer) damageSource.getEntity()).capabilities.isCreativeMode;
			
			if (flag || this.getDamageTaken() > 40.0F) {
				if (this.riddenByEntity != null) {
					this.riddenByEntity.mountEntity(this);
				}
				
				if (!flag) {
					this.func_145778_a(getItemBoat(), 1, 0.0F);
				}
				
				this.setDead();
			}
			
			return true;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (this.getTimeSinceHit() > 0) {
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}
		
		if (this.getDamageTaken() > 0.0F) {
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		byte b0 = 5;
		double d0 = 0.0D;
		
		for (int i = 0; i < b0; ++i) {
			double d1 = this.boundingBox.minY
					+ (this.boundingBox.maxY - this.boundingBox.minY) * (double) (i + 0) / (double) b0 - 0.125D;
			double d3 = this.boundingBox.minY
					+ (this.boundingBox.maxY - this.boundingBox.minY) * (double) (i + 1) / (double) b0 - 0.125D;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(
					this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d3, this.boundingBox.maxZ);
			
			if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
				d0 += 1.0D / (double) b0;
			}
		}
		
		double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double d2;
		double d4;
		int j;
		
		if (d10 > 0.26249999999999996D) {
			d2 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D);
			d4 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D);
			
			for (j = 0; (double) j < 1.0D + d10 * 60.0D; ++j) {
				double d5 = (double) (this.rand.nextFloat() * 2.0F - 1.0F);
				double d6 = (double) (this.rand.nextInt(2) * 2 - 1) * 0.7D;
				double d8;
				double d9;
				
				if (this.rand.nextBoolean()) {
					d8 = this.posX - d2 * d5 * 0.8D + d4 * d6;
					d9 = this.posZ - d4 * d5 * 0.8D - d2 * d6;
					this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
				}
				else {
					d8 = this.posX + d2 + d4 * d5 * 0.7D;
					d9 = this.posZ + d4 - d2 * d5 * 0.7D;
					this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
				}
			}
		}
		
		double d11;
		double d12;
		
		if (this.worldObj.isRemote && this.isBoatEmpty) {
			if (this.boatPosRotationIncrements > 0) {
				d2 = this.posX + (this.boatX - this.posX) / (double) this.boatPosRotationIncrements;
				d4 = this.posY + (this.boatY - this.posY) / (double) this.boatPosRotationIncrements;
				d11 = this.posZ + (this.boatZ - this.posZ) / (double) this.boatPosRotationIncrements;
				d12 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double) this.rotationYaw);
				this.rotationYaw = (float) ((double) this.rotationYaw + d12 / (double) this.boatPosRotationIncrements);
				this.rotationPitch = (float) ((double) this.rotationPitch
						+ (this.boatPitch - (double) this.rotationPitch) / (double) this.boatPosRotationIncrements);
				--this.boatPosRotationIncrements;
				this.setPosition(d2, d4, d11);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}
			else {
				d2 = this.posX + this.motionX;
				d4 = this.posY + this.motionY;
				d11 = this.posZ + this.motionZ;
				this.setPosition(d2, d4, d11);
				
				if (this.onGround) {
					this.motionX *= 0.5D;
					this.motionY *= 0.5D;
					this.motionZ *= 0.5D;
				}
				
				this.motionX *= 0.9900000095367432D;
				this.motionY *= 0.949999988079071D;
				this.motionZ *= 0.9900000095367432D;
			}
		}
		else {
			if (d0 < 1.0D) {
				d2 = d0 * 2.0D - 1.0D;
				this.motionY += 0.03999999910593033D * d2;
			}
			else {
				if (this.motionY < 0.0D) {
					this.motionY /= 2.0D;
				}
				
				this.motionY += 0.007000000216066837D;
			}
			
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
				EntityLivingBase entitylivingbase = (EntityLivingBase) this.riddenByEntity;
				float f = this.riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
				this.motionX += -Math.sin((double) (f * (float) Math.PI / 180.0F)) * this.speedMultiplier
						* (double) entitylivingbase.moveForward * 0.05000000074505806D;
				this.motionZ += Math.cos((double) (f * (float) Math.PI / 180.0F)) * this.speedMultiplier
						* (double) entitylivingbase.moveForward * 0.05000000074505806D;
			}
			
			d2 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			
			if (d2 > 0.35D) {
				d4 = 0.35D / d2;
				this.motionX *= d4;
				this.motionZ *= d4;
				d2 = 0.35D;
			}
			
			if (d2 > d10 && this.speedMultiplier < 0.35D) {
				this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
				
				if (this.speedMultiplier > 0.35D) {
					this.speedMultiplier = 0.35D;
				}
			}
			else {
				this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;
				
				if (this.speedMultiplier < 0.07D) {
					this.speedMultiplier = 0.07D;
				}
			}
			
			int l;
			
			for (l = 0; l < 4; ++l) {
				int i1 = MathHelper.floor_double(this.posX + ((double) (l % 2) - 0.5D) * 0.8D);
				j = MathHelper.floor_double(this.posZ + ((double) (l / 2) - 0.5D) * 0.8D);
				
				for (int j1 = 0; j1 < 2; ++j1) {
					int k = MathHelper.floor_double(this.posY) + j1;
					Block block = this.worldObj.getBlock(i1, k, j);
					
					if (block == Blocks.snow_layer) {
						this.worldObj.setBlockToAir(i1, k, j);
						this.isCollidedHorizontally = false;
					}
					else if (block == Blocks.waterlily) {
						this.worldObj.func_147480_a(i1, k, j, true);
						this.isCollidedHorizontally = false;
					}
				}
			}
			
			if (this.onGround) {
				this.motionX *= 0.5D;
				this.motionY *= 0.5D;
				this.motionZ *= 0.5D;
			}
			
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			
			if (this.isCollidedHorizontally && d10 > 0.2D) {
				if (!this.worldObj.isRemote && !this.isDead) {
					this.setDead();
					
					for (l = 0; l < 3; ++l) {
						this.entityDropItem(new ItemStack(Blocks.planks, 1, getPlankMeta()), 0.0F);
					}
					
					for (l = 0; l < 2; ++l) {
						this.func_145778_a(Items.stick, 1, 0.0F);
					}
				}
			}
			else {
				this.motionX *= 0.9900000095367432D;
				this.motionY *= 0.949999988079071D;
				this.motionZ *= 0.9900000095367432D;
			}
			
			this.rotationPitch = 0.0F;
			d4 = (double) this.rotationYaw;
			d11 = this.prevPosX - this.posX;
			d12 = this.prevPosZ - this.posZ;
			
			if (d11 * d11 + d12 * d12 > 0.001D) {
				d4 = (double) ((float) (Math.atan2(d12, d11) * 180.0D / Math.PI));
			}
			
			double d7 = MathHelper.wrapAngleTo180_double(d4 - (double) this.rotationYaw);
			
			if (d7 > 20.0D) {
				d7 = 20.0D;
			}
			
			if (d7 < -20.0D) {
				d7 = -20.0D;
			}
			
			this.rotationYaw = (float) ((double) this.rotationYaw + d7);
			this.setRotation(this.rotationYaw, this.rotationPitch);
			
			if (!this.worldObj.isRemote) {
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(
						this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
				
				if (list != null && !list.isEmpty()) {
					for (int k1 = 0; k1 < list.size(); ++k1) {
						Entity entity = (Entity) list.get(k1);
						
						if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat) {
							entity.applyEntityCollision(this);
						}
					}
				}
				
				if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
					this.riddenByEntity = null;
				}
			}
		}
	}
	
	@Override
	public void updateRiderPosition() {
		if (this.riddenByEntity != null) {
			double d0 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(
					this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}
	
	/**
	 * First layer of player interaction
	 */
	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
				&& this.riddenByEntity != player) {
			return true;
		}
		else {
			if (!this.worldObj.isRemote) {
				player.mountEntity(this);
			}
			
			return true;
		}
	}
	
	/**
	 * Takes in the distance the entity has fallen this tick and whether its on
	 * the ground to update the fall distance
	 * and deal fall damage if landing on the ground. Args:
	 * distanceFallenThisTick, onGround
	 */
	@Override
	protected void updateFallState(double distanceFallenThisTick, boolean onGround) {
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);
		
		if (onGround) {
			if (this.fallDistance > 3.0F) {
				this.fall(this.fallDistance);
				
				if (!this.worldObj.isRemote && !this.isDead) {
					this.setDead();
					int l;
					
					for (l = 0; l < 3; ++l) {
						this.entityDropItem(new ItemStack(Blocks.planks, 1, getPlankMeta()), 0.0F);
					}
					
					for (l = 0; l < 2; ++l) {
						this.func_145778_a(Items.stick, 1, 0.0F);
					}
				}
				
				this.fallDistance = 0.0F;
			}
		}
		else if (this.worldObj.getBlock(i, j - 1, k).getMaterial() != Material.water && distanceFallenThisTick < 0.0D) {
			this.fallDistance = (float) ((double) this.fallDistance - distanceFallenThisTick);
		}
	}
	
	/**
	 * Sets the damage taken from the last hit.
	 */
	@Override
	public void setDamageTaken(float par1f) {
		this.dataWatcher.updateObject(19, Float.valueOf(par1f));
	}
	
	/**
	 * Gets the damage taken from the last hit.
	 */
	@Override
	public float getDamageTaken() {
		return this.dataWatcher.getWatchableObjectFloat(19);
	}
	
	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	@Override
	public void setTimeSinceHit(int par1i) {
		this.dataWatcher.updateObject(17, Integer.valueOf(par1i));
	}
	
	/**
	 * Gets the time since the last hit.
	 */
	@Override
	public int getTimeSinceHit() {
		return this.dataWatcher.getWatchableObjectInt(17);
	}
	
	/**
	 * Sets the forward direction of the entity.
	 */
	@Override
	public void setForwardDirection(int par1i) {
		this.dataWatcher.updateObject(18, Integer.valueOf(par1i));
	}
	
	/**
	 * Gets the forward direction of the entity.
	 */
	@Override
	public int getForwardDirection() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}
	
	/**
	 * true if no player in boat
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void setIsBoatEmpty(boolean isBoatEmpty) {
		this.isBoatEmpty = isBoatEmpty;
	}
}
