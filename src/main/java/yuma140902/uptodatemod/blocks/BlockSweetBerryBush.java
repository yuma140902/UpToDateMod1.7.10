package yuma140902.uptodatemod.blocks;

import java.util.ArrayList;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockSweetBerryBush extends BlockBush implements IRegisterable {
	
	public static final int META_MAX = 3;
	
	public BlockSweetBerryBush() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		setTickRandomly(true);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("sweet_berry_bush"));
		setBlockTextureName(StringUtil.getDomainedModTextureName("sweet_berry_bush_stage"));
		GameRegistry.registerBlock(this, "sweet_berry_bush");
	}
	
	
	
	// ================= 描画処理 ここから =================

	private static final float bbsize[] = new float[] {0.25F, 0.35F, 0.4F, 0.5F};
	private static final float bbheight[] = new float[] {0.45F, 0.8F, 0.9F, 1.0F};
	
	private IIcon icons[] = new IIcon[META_MAX+1];
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		meta &= 0b0011;
		float s = bbsize[meta];
		float h = bbheight[meta];
		this.setBlockBounds(0.5F - s, 0.0F, 0.5F - s, 0.5F + s, h, 0.5F + s);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		meta &= 0b0011;
		return icons[meta];
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		for(int meta = 0; meta <= META_MAX; ++meta) {
			icons[meta] = register.registerIcon(getTextureName() + meta);
		}
	}
	
	// ================= 描画処理 ここまで =================
	
	
	
	// ================= 成長処理 ここから =================

	private static boolean holdingFertilizer(EntityPlayer player) {
		ItemStack itemstack = player.getHeldItem();
		if(itemstack != null && itemstack.getItem() == Items.dye && itemstack.getItemDamage() == 15) {
			return true;
		}
		return false;
	}
	
	private static void dropSweetBerries(World world, int x, int y, int z) {
		if(world.isRemote) {
			return;
		}
		
		Random rand = world.rand;
		float xDiff = rand.nextFloat() * 0.6F + 0.1F;
		float yDiff = rand.nextFloat() * 0.6F + 0.1F;
		float zDiff = rand.nextFloat() * 0.6F + 0.1F;
		
		EntityItem entityItem = new EntityItem(world, x + xDiff, y + yDiff, z + zDiff, new ItemStack(MyItems.sweetBerries, 1));
		float motionScale = 0.025F;
		entityItem.motionX = (float) rand.nextGaussian() * motionScale;
		entityItem.motionY = (float) rand.nextGaussian() * motionScale + 0.1F;
		entityItem.motionZ = (float) rand.nextGaussian() * motionScale;
		world.spawnEntityInWorld(entityItem);
	}
	
	public static boolean canFertilizeSweetBerryBush(int meta) {
		return meta < META_MAX;
	}
	
	public static void fertilizeSweetBerryBush(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z) & 0b0011;
		
		++meta;
		ItemDye.func_150918_a(world, x, y, z, 6); //パーティクルを表示
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		int meta = world.getBlockMetadata(x, y, z);
		meta &= 0b0011;
		
		if(meta == META_MAX) {
			dropSweetBerries(world, x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, META_MAX-2, 3);
			return false;
		}
		
		if(holdingFertilizer(player)) {
			fertilizeSweetBerryBush(world, x, y, z);
			if(!player.capabilities.isCreativeMode) {
				ItemStack itemstack = player.getHeldItem();
				if(--itemstack.stackSize <= 0) {
					itemstack = null;
				}
				player.inventory.setInventorySlotContents(player.inventory.currentItem, itemstack);
			}
		}
		
		return false;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		
		int meta = world.getBlockMetadata(x, y, z);
		if(meta < META_MAX && rand.nextInt(5) == 0 && world.getBlockLightValue(x, y, z) >= 9) {
			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}
	}
	
	// ================= 成長処理 ここまで =================
	
	
	
	// ================= ドロップ用処理 ここから =================
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return MyItems.sweetBerries;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
		return null;
	}
	
	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		if(metadata == META_MAX) {
			int count = world.rand.nextInt(1) + 2 + fortune;
			list.add(new ItemStack(MyItems.sweetBerries, count));
		}
		else if(metadata == META_MAX - 1) {
			int count = world.rand.nextInt(1) + 1 + fortune;
			list.add(new ItemStack(MyItems.sweetBerries, count));
		}
		return list;
	}
	
	// ================= ドロップ用処理 ここまで =================
	
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.motionX *= 0.5D;
		entity.motionY *= 0.6D;
		entity.motionZ *= 0.5D;
		if (world.getBlockMetadata(x, y, z) > 0 && (entity.lastTickPosX != entity.posX || entity.lastTickPosZ != entity.posZ)) {
      double dx = Math.abs(entity.posX - entity.lastTickPosX);
      double dz = Math.abs(entity.posZ - entity.lastTickPosZ);
      if (dx >= (double)0.003F || dz >= (double)0.003F) {
         entity.attackEntityFrom(DamageSource.generic, 1.0F);
      }
   }
	}
	
}
