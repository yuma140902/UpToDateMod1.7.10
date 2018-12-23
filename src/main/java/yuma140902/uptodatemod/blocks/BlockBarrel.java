package yuma140902.uptodatemod.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyGuis;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;
import yuma140902.uptodatemod.util.DirectionUtil;

public class BlockBarrel extends BlockRotatedPillar implements ITileEntityProvider, IRegisterable, IHasRecipes {
	private Random random = new Random();
	private IIcon iconBottom;
	
	public BlockBarrel() {
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundTypeWood);
		setHardness(2.5F);
		isBlockContainer = true;
	}
	
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".barrel");
		setBlockTextureName(ModUpToDateMod.MOD_ID + ":barrel");
		GameRegistry.registerBlock(this, "barrel");
	}
	
	@Override
	public void registerRecipes() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	protected IIcon getSideIcon(int p_150163_1_) {
		return blockIcon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(getTextureName() + "_side");
		field_150164_N = register.registerIcon(getTextureName() + "_top");
		iconBottom = register.registerIcon(getTextureName() + "_bottom");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return meta == DirectionUtil.getBack(side) ? field_150164_N : meta == side ? iconBottom : blockIcon;
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
  {
    return DirectionUtil.getBack(side);
  }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBarrel();
	}
	
	@Override
	public boolean onBlockActivated(
			World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(ModUpToDateMod.INSTANCE, MyGuis.ID_BARREL, world, x, y, z);
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

