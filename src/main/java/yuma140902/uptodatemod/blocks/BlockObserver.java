package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.tileentity.TileEntityObserver;
import yuma140902.uptodatemod.util.BlockCoordinate3;
import yuma140902.uptodatemod.util.DirectionUtil;
import yuma140902.uptodatemod.util.Stat;

public class BlockObserver extends BlockContainer implements IRegisterable {
	
	//メタデータの仕様
	/* 
	 * 0b000 = 0 : 北(オフ)
	 * 0b001 = 1 : 南(オフ)
	 * 0b010 = 2 : 西(オフ)
	 * 0b011 = 3 : 東(オフ)
	 * 
	 * 0b100 = 4 : 北(オン)
	 * 0b101 = 5 : 南(オン)
	 * 0b110 = 6 : 西(オン)
	 * 0b111 = 7 : 東(オン)
	*/
	
	//RS出力について
	// http://greyminecraftcoder.blogspot.com/2015/11/redstone.html
	
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	private IIcon frontIcon;
	private IIcon sideIcon;
	private IIcon backOffIcon;
	private IIcon backOnIcon;
	
	public BlockObserver() {
		super(Material.rock);
		setHardness(3.0F);
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		try {
			return new TileEntityObserver();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		super.onNeighborBlockChange(world, x, y, z, block);
		
		if(!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			BlockCoordinate3 coordinate = DirectionUtil.getCoordBySide(meta + 2, x, y, z);
			int targetX = coordinate.x;
			int targetY = coordinate.y;
			int targetZ = coordinate.z;
  		
  		int targetId = Block.getIdFromBlock(world.getBlock(targetX, targetY, targetZ));
  		int targetMeta = world.getBlockMetadata(targetX, targetY, targetZ);
  		
  		TileEntityObserver tileEntity = (TileEntityObserver) world.getTileEntity(x, y, z);
  		
  		if(targetId != tileEntity.getTargetId() || targetMeta != tileEntity.getTargetMeta()) {
  			world.setBlockMetadataWithNotify(x, y, z, meta | 4, 2);
  			tileEntity.setValues(targetId, targetMeta);
  			world.notifyBlockChange(x, y, z, block); //念の為
  		}
		}
	}
	
	@Override
	public boolean canProvidePower() {
		return true;
	}
	
	@Override
	public int isProvidingStrongPower(
			IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
		return 15;
	}
	
	@Override
	public int isProvidingWeakPower(
			IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
		return 15;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingEntity, ItemStack itemStack) {
		int rotation = (int) (livingEntity.rotationYaw + 180);
		int l = 0;
		if(45 <= rotation && rotation < 135) {
			l = Stat.SIDE_EAST - 2;
		}
		else if(135 <= rotation && rotation < 225) {
			l = Stat.SIDE_SOUTH - 2;
		}
		else if(225 <= rotation && rotation < 315) {
			l = Stat.SIDE_WEST - 2;
		}
		else {
			l = Stat.SIDE_NORTH - 2;
		}
    world.setBlockMetadataWithNotify(x, y, z, l, 2);
    
    if(!world.isRemote) {
    }
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public int tickRate(World p_149738_1_) {
		return 20;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		int blockFacing = (meta & 3) + 2;
		
		if(side == 1) return topIcon;
		else if(side == blockFacing) return frontIcon;
		else if(DirectionUtil.getBack(side) == blockFacing) return backOffIcon;
		else if(DirectionUtil.getRightSide(side) == blockFacing) return sideIcon;
		else if(DirectionUtil.getLeftSide(side) == blockFacing) return sideIcon;
		return sideIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		String prefix = ModUpToDateMod.MOD_ID + ":observer";
		//topIcon = register.registerIcon(prefix + "_top");
		topIcon = register.registerIcon(prefix + "_side");
		frontIcon = register.registerIcon(prefix + "_front");
		sideIcon = register.registerIcon(prefix + "_side");
		backOffIcon = register.registerIcon(prefix + "_back");
		backOnIcon = register.registerIcon(prefix + "_back_on");
	}

	@Override
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".observer");
		GameRegistry.registerBlock(this, "observer");
	}
	
}
