package yuma140902.uptodatemod.items;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.Recipes;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;
import yuma140902.uptodatemod.util.StringUtil;

public abstract class ItemModBoatBase extends ItemBoat implements IRegisterable, IHasRecipes {
	
	protected abstract String getName();
	
	protected abstract Type getType();
	
	protected abstract EntityModBoatBase getNewEntityModBoat(World world, double d1, double d2, double d3);
	
	public ItemModBoatBase() {
		super();
		this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	@Override
	public void register() {
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, new BehaviorDefaultDispenseItem() {
			private final BehaviorDefaultDispenseItem field_150842_b = new BehaviorDefaultDispenseItem();
			
			/**
			 * Dispense the specified stack, play the dispense sound and spawn
			 * particles.
			 */
			public ItemStack dispenseStack(IBlockSource blockSource, ItemStack itemStack) {
				EnumFacing enumfacing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata());
				World world = blockSource.getWorld();
				double d0 = blockSource.getX() + (double) ((float) enumfacing.getFrontOffsetX() * 1.125F);
				double d1 = blockSource.getY() + (double) ((float) enumfacing.getFrontOffsetY() * 1.125F);
				double d2 = blockSource.getZ() + (double) ((float) enumfacing.getFrontOffsetZ() * 1.125F);
				int i = blockSource.getXInt() + enumfacing.getFrontOffsetX();
				int j = blockSource.getYInt() + enumfacing.getFrontOffsetY();
				int k = blockSource.getZInt() + enumfacing.getFrontOffsetZ();
				Material material = world.getBlock(i, j, k).getMaterial();
				double d3;
				
				if (Material.water.equals(material)) {
					d3 = 1.0D;
				}
				else {
					if (!Material.air.equals(material) || !Material.water.equals(world.getBlock(i, j - 1, k).getMaterial())) {
						return this.field_150842_b.dispense(blockSource, itemStack);
					}
					
					d3 = 0.0D;
				}
				
				EntityModBoatBase entityboat = getNewEntityModBoat(world, d0, d1 + d3, d2);
				world.spawnEntityInWorld(entityboat);
				itemStack.splitStack(1);
				return itemStack;
			}
			
			/**
			 * Play the dispense sound from the specified block.
			 */
			protected void playDispenseSound(IBlockSource p_82485_1_) {
				p_82485_1_.getWorld().playAuxSFX(1000, p_82485_1_.getXInt(), p_82485_1_.getYInt(), p_82485_1_.getZInt(), 0);
			}
		});
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName(getName()));
		this.setTextureName(StringUtil.getDomainedTextureName(getName()));
		GameRegistry.registerItem(this, getName());
	}
	
	@Override
	public void registerRecipes() {
		EntityModBoatBase.Type type = getType();
		ItemStack plank;
		switch (type) {
			case ACACIA: plank = Recipes.PLANK_ACACIA; break;
			case BIRCH: plank = Recipes.PLANK_BIRCH; break;
			case DARK_OAK: plank = Recipes.PLANK_DARK_OAK; break;
			case JUNGLE: plank = Recipes.PLANK_JUNGLE; break;
			case SPRUCE: plank = Recipes.PLANK_SPRUCE; break;
			default: return;
		}
		
		GameRegistry.addRecipe(
				new ItemStack(this),
				"# #",
				"###",
				'#', plank
				);
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
			@SuppressWarnings("rawtypes")
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
					
					EntityModBoatBase entitymodboat = getNewEntityModBoat(
							p_77659_2_, (double) ((float) i + 0.5F), (double) ((float) j + 1.0F), (double) ((float) k + 0.5F));
					entitymodboat.rotationYaw
							= (float) (((MathHelper.floor_double((double) (p_77659_3_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1)
									* 90);
					
					if (!p_77659_2_
							.getCollidingBoundingBoxes(entitymodboat, entitymodboat.boundingBox.expand(-0.1D, -0.1D, -0.1D))
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
