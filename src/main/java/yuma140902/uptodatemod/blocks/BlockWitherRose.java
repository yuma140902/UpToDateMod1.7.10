package yuma140902.uptodatemod.blocks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.items.ItemPlainDye;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockWitherRose extends BlockBush implements IRegisterable, IHasRecipes {
	
	public BlockWitherRose() {
		super(Material.plants);
		setStepSound(soundTypeGrass);
		float f = 0.2F;
    this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
    this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("wither_rose"));
		setBlockTextureName(StringUtil.getDomainedTextureName("wither_rose"));
		GameRegistry.registerBlock(this, ItemBlock.class, "wither_rose");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapeless(new ItemStack(MyItems.dye, 1, ItemPlainDye.BLACK), this);
	}
	
	
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return canPlace(world, x, y, z);
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return true;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
    this.checkAndDropBlock(world, x, y, z);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		this.checkAndDropBlock(world, x, y, z);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlace(world, x, y, z);
	}
	
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		double posX = x + 0.5D + (rand.nextDouble() - 0.5D) * 0.5D;
    double posY = y + 0.6D + (rand.nextDouble() - 0.5D) * 0.5D;
    double posZ = z + 0.5D + (rand.nextDouble() - 0.5D) * 0.5D;
    double velX = 0;
    double velY = (rand.nextDouble()) * 0.05D;
    double velZ = 0;
    
    world.spawnParticle("smoke", posX, posY, posZ, velX, velY, velZ);
	}
	
	
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityLivingBase) {
			EntityLivingBase entityLiving = (EntityLivingBase) entity;
			if(!hasWitherEffect(entityLiving.getActivePotionEffects())) {
				entityLiving.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 0, true));
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private boolean hasWitherEffect(Collection potionEffects) {
		Iterator iterator = potionEffects.iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if(obj instanceof PotionEffect) {
				PotionEffect effect = (PotionEffect) obj;
				if(effect.getPotionID() == Potion.wither.id) return true;
			}
		}
		return false;
	}
	
	public static void onLivingDeathEvent(final LivingDeathEvent event) {
		if(event == null || event.entityLiving == null) {
			return;
		}
		final PotionEffect potionEffect = event.entityLiving.getActivePotionEffect(Potion.wither);
		if(potionEffect == null || potionEffect.getAmplifier() <= 0) {
			return;
		}
		
		final EntityLivingBase entityLiving = event.entityLiving;
		final int posX = (int) entityLiving.posX;
		final int posY = (int) entityLiving.posY;
		final int posZ = (int) entityLiving.posZ;
		final World world = entityLiving.worldObj;
		
		final int[] aroundX = new int[] {0, 1, -1, 0,  0, 1, -1,  1, -1};
		final int[] aroundZ = new int[] {0, 0,  0, 1, -1, 1,  1, -1, -1};
		final int[] aroundY = new int[] {0, 1, -1};
		
		for(int i=0; i<aroundX.length; ++i) {
			final int x = posX + aroundX[i];
			final int z = posZ + aroundZ[i];
			for(int j=0; j<aroundY.length; ++j) {
				final int y = posY + aroundY[j];
				
				if(world.isAirBlock(x, y, z) && canPlace(world, x, y, z)) {
					world.setBlock(x, y, z, MyBlocks.witherRose);
					return;
				}
			}
		}
		
		if(!world.isRemote) {
			entityLiving.dropItem(Item.getItemFromBlock(MyBlocks.witherRose), 1);
		}
		
	}
	
	private static boolean canPlace(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y-1, z);
		return block.isSideSolid(world, x, y-1, z, ForgeDirection.UP) || block == Blocks.farmland || block == Blocks.flower_pot || block == MyBlocks.unlimitedPot;
	}
	
	public static class ItemBlock extends net.minecraft.item.ItemBlock {

		public ItemBlock(Block block) {
			super(block);
		}
		
		@Override
		public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
			@SuppressWarnings("null")
			boolean success = MyBlocks.unlimitedPot.putItemIn(itemstack, player, world, x, y, z);
			if(success) {
				return true;
			}else {
				return super.onItemUse(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
			}
		}
		
	}
}
