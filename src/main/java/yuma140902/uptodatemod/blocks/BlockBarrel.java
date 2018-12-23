package yuma140902.uptodatemod.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyGuis;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class BlockBarrel extends Block implements ITileEntityProvider, IRegisterable, IHasRecipes {
	public static final int GUI_ID = MyGuis.INSTANCE.getNextGuiId();
	
	private Random random = new Random();
	
	public BlockBarrel() {
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabBlock);
		isBlockContainer = true;
	}
	
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".barrel");
		// setBlockTextureName(ModUpToDateMod.MOD_ID + ":barrel");
		setBlockTextureName("log_oak_top");
		GameRegistry.registerBlock(this, "barrel");
	}
	
	@Override
	public void registerRecipes() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBarrel();
	}
	
	@Override
	public boolean onBlockActivated(
			World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(ModUpToDateMod.INSTANCE, BlockBarrel.GUI_ID, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// TileEntityの内部にあるアイテムをドロップさせる。
		TileEntityBarrel tileentity = (TileEntityBarrel) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			for (int i = 0; i < tileentity.getSizeInventory(); i++) {
				ItemStack itemStack = tileentity.getStackInSlot(i);
				
				if (itemStack != null) {
					float f = random.nextFloat() * 0.6F + 0.1F;
					float f1 = random.nextFloat() * 0.6F + 0.1F;
					float f2 = random.nextFloat() * 0.6F + 0.1F;
					
					while (itemStack.stackSize > 0) {
						int j = random.nextInt(21) + 10;
						
						if (j > itemStack.stackSize) {
							j = itemStack.stackSize;
						}
						
						itemStack.stackSize -= j;
						EntityItem entityItem = new EntityItem(
								world, x + f, y + f1, z + f2, new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
						
						if (itemStack.hasTagCompound()) {
							entityItem.getEntityItem().setTagCompound(((NBTTagCompound) itemStack.getTagCompound().copy()));
						}
						
						float f3 = 0.025F;
						entityItem.motionX = (float) random.nextGaussian() * f3;
						entityItem.motionY = (float) random.nextGaussian() * f3 + 0.1F;
						entityItem.motionZ = (float) random.nextGaussian() * f3;
						world.spawnEntityInWorld(entityItem);
					}
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, meta);
		
	}
}

