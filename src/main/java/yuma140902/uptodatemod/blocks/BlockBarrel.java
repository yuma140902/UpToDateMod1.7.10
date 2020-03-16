package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyGuis;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;
import yuma140902.uptodatemod.util.DirectionUtil;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib_ee.api.IHasRecipes;
import yuma140902.yumalib_ee.api.IRegisterable;

public class BlockBarrel extends BlockRotatedPillar implements ITileEntityProvider, IRegisterable, IHasRecipes {
	private IIcon iconBottom;
	
	public BlockBarrel() {
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundTypeWood);
		setHardness(2.5F);
		isBlockContainer = true;
	}
	
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("barrel"));
		setBlockTextureName(StringUtil.getDomainedTextureName("barrel"));
		GameRegistry.registerBlock(this, "barrel");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapedOre(new ItemStack(this),
				"#_#",
				"# #",
				"#_#",
				'#', "plankWood",
				'_', "slabWood"
				);
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
		return meta == DirectionUtil.getBack(side) ? iconBottom : meta == side ? field_150164_N : blockIcon;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		int l = BlockPistonBase.determineOrientation(world, x, y, z, player);
    world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBarrel();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(ModUpToDateMod.INSTANCE, MyGuis.ID_BARREL, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// TileEntityの内部にあるアイテムをドロップさせる。
		TileEntityBarrel tileentity = (TileEntityBarrel) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			tileentity.drop();
		}
		super.breakBlock(world, x, y, z, block, meta);
		
	}
}

