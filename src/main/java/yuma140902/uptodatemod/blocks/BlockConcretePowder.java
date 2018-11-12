package yuma140902.uptodatemod.blocks;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.items.ItemBlockColored;
import yuma140902.uptodatemod.util.ColorUtil;

public class BlockConcretePowder extends BlockFalling implements IRegisterable {
	public static final int META_MAX = 15;
//	private static final int idWater = Block.getIdFromBlock(Blocks.water);
//	private static final int idFlowingWater = Block.getIdFromBlock(Blocks.flowing_water);
	
	private IIcon[] iicons = new IIcon[META_MAX + 1];
	public static boolean fallInstantly;
	
	public BlockConcretePowder() {
		super(Material.sand);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeSand);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".concrete_powder");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":concrete_powder");
		GameRegistry.registerBlock(this, ItemBlockColored.class, "concrete_powder");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		for (int meta = 0; meta <= META_MAX; ++meta) {
			iicons[meta] = register.registerIcon(this.getTextureName() + "_" + ColorUtil.metaToString(meta));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return iicons[meta];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for (int meta = 0; meta <= META_MAX; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public MapColor getMapColor(int meta) {
		return ColorUtil.metaToMapColor(meta);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		tryReplaceToConcrete(world, x, y, z);
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		tryReplaceToConcrete(world, x, y, z);
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
	}
	
	private boolean tryReplaceToConcrete(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		Block[] aroundBlocks = new Block[5];
		aroundBlocks[0] = (world.getBlock(x + 1, y, z));
		aroundBlocks[1] = (world.getBlock(x - 1, y, z));
		aroundBlocks[2] = (world.getBlock(x, y + 1, z));
		aroundBlocks[3] = (world.getBlock(x, y, z + 1));
		aroundBlocks[4] = (world.getBlock(x, y, z - 1));
		
		for (int i = 0; i < 5; ++i) {
			if (aroundBlocks[i].getMaterial() == Material.water) {
				world.setBlock(x, y, z, MyBlocks.blockConcrete, meta, 3);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			this.dropBlockFrom(world, x, y, z);
		}
	}
	
	private void dropBlockFrom(World world, int x, int y, int z) {
		if (shouldNotStopFalling(world, x, y - 1, z) && y >= 0) {
			byte offset = 32;
			
			if (!fallInstantly
					&& world.checkChunksExist(x - offset, y - offset, z - offset, x + offset, y + offset, z + offset)) {
				if (!world.isRemote) {
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(
							world, (double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), this,
							world.getBlockMetadata(x, y, z));
					func_149829_a(entityfallingblock);
					world.spawnEntityInWorld(entityfallingblock);
				}
			}
			else {
				world.setBlockToAir(x, y, z);
				
				while (shouldNotStopFalling(world, x, y - 1, z) && y > 0) {
					--y;
				}
				
				if (y > 0) {
					world.setBlock(x, y, z, this);
				}
			}
		}
	}
	
	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World p_149738_1_) {
		return 2;
	}
	
	public static boolean shouldNotStopFalling(World world, int x, int y, int z) {
		Block blockUnder = world.getBlock(x, y, z);
		
		return blockUnder.isAir(world, x, y, z)
				 || blockUnder == Blocks.fire
				 || blockUnder.getMaterial() == Material.lava
				 || blockUnder.getMaterial() == Material.water;
		
	}
	
}
