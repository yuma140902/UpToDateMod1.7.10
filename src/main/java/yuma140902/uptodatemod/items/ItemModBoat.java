package yuma140902.uptodatemod.items;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.entity.item.EntityModBoat;
import yuma140902.uptodatemod.entity.item.EntityModBoat.Type;

public class ItemModBoat extends ItemBoat implements IRegisterable{
	
	private String name;
	private Type type;
	
	public ItemModBoat(String name, Type type) {
		super();
		this.name = name;
		this.type = type;
		this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + "." + name);
		this.setTextureName(ModUpToDateMod.MOD_ID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		float f = 1.0F;
		float f1 = p_77659_3_.prevRotationPitch + (p_77659_3_.rotationPitch - p_77659_3_.prevRotationPitch) * f;
		float f2 = p_77659_3_.prevRotationYaw + (p_77659_3_.rotationYaw - p_77659_3_.prevRotationYaw) * f;
		double d0 = p_77659_3_.prevPosX + (p_77659_3_.posX - p_77659_3_.prevPosX) * (double) f;
		double d1 = p_77659_3_.prevPosY + (p_77659_3_.posY - p_77659_3_.prevPosY) * (double) f + 1.62D
				- (double) p_77659_3_.yOffset;
		double d2 = p_77659_3_.prevPosZ + (p_77659_3_.posZ - p_77659_3_.prevPosZ) * (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		MovingObjectPosition movingobjectposition = p_77659_2_.rayTraceBlocks(vec3, vec31, true);
		
		if (movingobjectposition == null) {
			return p_77659_1_;
		}
		else {
			Vec3 vec32 = p_77659_3_.getLook(f);
			boolean flag = false;
			float f9 = 1.0F;
			List list = p_77659_2_.getEntitiesWithinAABBExcludingEntity(
					p_77659_3_, p_77659_3_.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3)
							.expand((double) f9, (double) f9, (double) f9));
			int i;
			
			for (i = 0; i < list.size(); ++i) {
				Entity entity = (Entity) list.get(i);
				
				if (entity.canBeCollidedWith()) {
					float f10 = entity.getCollisionBorderSize();
					AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double) f10, (double) f10, (double) f10);
					
					if (axisalignedbb.isVecInside(vec3)) {
						flag = true;
					}
				}
			}
			
			if (flag) {
				return p_77659_1_;
			}
			else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;
					
					if (p_77659_2_.getBlock(i, j, k) == Blocks.snow_layer) {
						--j;
					}
					
					EntityModBoat entitymodboat = new EntityModBoat(
							type, p_77659_2_, (double) ((float) i + 0.5F), (double) ((float) j + 1.0F), (double) ((float) k + 0.5F));
					entitymodboat.rotationYaw
							= (float) (((MathHelper.floor_double((double) (p_77659_3_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1)
									* 90);
					
					if (!p_77659_2_.getCollidingBoundingBoxes(entitymodboat, entitymodboat.boundingBox.expand(-0.1D, -0.1D, -0.1D))
							.isEmpty()) {
						return p_77659_1_;
					}
					
					if (!p_77659_2_.isRemote) {
						p_77659_2_.spawnEntityInWorld(entitymodboat);
					}
					
					if (!p_77659_3_.capabilities.isCreativeMode) {
						--p_77659_1_.stackSize;
					}
				}
				
				return p_77659_1_;
			}
		}
	}
}
