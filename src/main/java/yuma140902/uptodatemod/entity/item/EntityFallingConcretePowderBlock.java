package yuma140902.uptodatemod.entity.item;

import java.util.ArrayList;
import java.util.Iterator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockConcretePowder;

public class EntityFallingConcretePowderBlock extends EntityFallingBlock {
	
	private Block block = MyBlocks.concretePowder;
	private int metadata;
	
	public int getMetadata() {
		return this.metadata;
	}
	
	public int time;
	public boolean canDropItem;
	private boolean field_145808_f;
	private boolean canHurtEntities;
	private int fallHurtMax;
	private float fallHurtAmount;
	public NBTTagCompound nbtTagCompound;
	
	public EntityFallingConcretePowderBlock(World world) {
		super(world);
		this.canDropItem = true;
		this.fallHurtMax = 40;
		this.fallHurtAmount = 2.0F;
	}
	
	public EntityFallingConcretePowderBlock(World world, double posX, double posY, double posZ, Block block) {
		this(world, posX, posY, posZ, block, 0);
	}
	
	public EntityFallingConcretePowderBlock(World world, double posX, double posY, double posZ, Block block, int meta) {
		super(world, posX, posY, posZ, block, meta);
		this.canDropItem = true;
		this.fallHurtMax = 40;
		this.fallHurtAmount = 2.0F;
		this.block = block;
		this.metadata = meta;
		this.preventEntitySpawning = true;
		this.setSize(1F, 1F);
		this.yOffset = this.height / 2.0F;
		this.setPosition(posX, posY, posZ);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;
	}
	
	
	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking() {
		return false;
	}
	
	protected void entityInit() {}
	
	/**
	 * Returns true if other Entities should be prevented from moving through this
	 * Entity.
	 */
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		if (this.block.getMaterial() == Material.air) {
			this.setDead();
		}
		else {
			System.out.println("EntityFallingConcretePowder#onUpdate : " + this.metadata);
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			++this.time;
			this.motionY -= 0.03999999910593033D;
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.9800000190734863D;
			this.motionY *= 0.9800000190734863D;
			this.motionZ *= 0.9800000190734863D;
			
			if (!this.worldObj.isRemote) {
				int x = MathHelper.floor_double(this.posX);
				int y = MathHelper.floor_double(this.posY);
				int z = MathHelper.floor_double(this.posZ);
				
				if (this.time == 1) {
					if (this.worldObj.getBlock(x, y, z) != this.block) {
						this.setDead();
						return;
					}
					
					this.worldObj.setBlockToAir(x, y, z);
				}
				
				if (this.onGround) {
					this.motionX *= 0.7D;
					this.motionZ *= 0.7D;
					this.motionY *= -0.5D;
					
					if (this.worldObj.getBlock(x, y, z) != Blocks.piston_extension) {
						this.setDead();
						
						if (!this.field_145808_f
								&& this.worldObj.canPlaceEntityOnSide(this.block, x, y, z, true, 1, (Entity) null, (ItemStack) null)
								&& !BlockConcretePowder.func_149831_e(this.worldObj, x, y - 1, z)
								&& this.worldObj.setBlock(x, y, z, this.block, this.metadata, 3)) {
							if (this.block instanceof BlockConcretePowder) {
								((BlockConcretePowder) this.block).func_149828_a(this.worldObj, x, y, z, this.metadata);
							}
						}
						else if (this.canDropItem && !this.field_145808_f) {
							this.entityDropItem(new ItemStack(this.block, 1, this.metadata), 0.0F);
						}
					}
				}
				else if (this.time > 100 && !this.worldObj.isRemote && (y < 1 || y > 256) || this.time > 600) {
					if (this.canDropItem) {
						this.entityDropItem(new ItemStack(this.block, 1, this.metadata), 0.0F);
					}
					
					this.setDead();
				}
			}
		}
	}
	
	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float p_70069_1_) {
		if (this.canHurtEntities) {
			int i = MathHelper.ceiling_float_int(p_70069_1_ - 1.0F);
			
			if (i > 0) {
				ArrayList arraylist = new ArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox));
				DamageSource damagesource = DamageSource.fallingBlock;
				Iterator iterator = arraylist.iterator();
				
				while (iterator.hasNext()) {
					Entity entity = (Entity) iterator.next();
					entity.attackEntityFrom(
							damagesource,
							(float) Math.min(MathHelper.floor_float((float) i * this.fallHurtAmount), this.fallHurtMax));
				}
			}
		}
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setByte("Tile", (byte) Block.getIdFromBlock(this.block));
		nbt.setInteger("TileID", Block.getIdFromBlock(this.block));
		nbt.setByte("Data", (byte) this.metadata);
		nbt.setByte("Time", (byte) this.time);
		nbt.setBoolean("DropItem", this.canDropItem);
		nbt.setBoolean("HurtEntities", this.canHurtEntities);
		nbt.setFloat("FallHurtAmount", this.fallHurtAmount);
		nbt.setInteger("FallHurtMax", this.fallHurtMax);
		
		if (this.nbtTagCompound != null) {
			nbt.setTag("TileEntityData", this.nbtTagCompound);
		}
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("TileID", 99)) {
			this.block = Block.getBlockById(nbt.getInteger("TileID"));
		}
		else {
			this.block = Block.getBlockById(nbt.getByte("Tile") & 255);
		}
		
		this.metadata = nbt.getByte("Data") & 255;
		this.time = nbt.getByte("Time") & 255;
		
		if (nbt.hasKey("HurtEntities", 99)) {
			this.canHurtEntities = nbt.getBoolean("HurtEntities");
			this.fallHurtAmount = nbt.getFloat("FallHurtAmount");
			this.fallHurtMax = nbt.getInteger("FallHurtMax");
		}
		else if (this.block == Blocks.anvil) {
			this.canHurtEntities = true;
		}
		
		if (nbt.hasKey("DropItem", 99)) {
			this.canDropItem = nbt.getBoolean("DropItem");
		}
		
		if (nbt.hasKey("TileEntityData", 10)) {
			this.nbtTagCompound = nbt.getCompoundTag("TileEntityData");
		}
		
		if (this.block.getMaterial() == Material.air) {
			this.block = Blocks.sand;
		}
	}
	
	public void func_145806_a(boolean p_145806_1_) {
		this.canHurtEntities = p_145806_1_;
	}
	
	public void addEntityCrashInfo(CrashReportCategory crashReportCategory) {
		super.addEntityCrashInfo(crashReportCategory);
		crashReportCategory.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(this.block)));
		crashReportCategory.addCrashSection("Immitating block data", Integer.valueOf(this.metadata));
	}
	
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}
	
	@SideOnly(Side.CLIENT)
	public World func_145807_e() {
		return this.worldObj;
	}
	
	/**
	 * Return whether this entity should be rendered as on fire.
	 */
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire() {
		return false;
	}
	
	public Block func_145805_f() {
		return this.block;
	}
}
