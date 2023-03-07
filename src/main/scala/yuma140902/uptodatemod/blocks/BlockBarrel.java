package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.blockstate.VanillaPistonStyleOrientationState;
import yuma140902.yumalib.api.blockstate.VanillaRotatedPillarState;

import java.util.List;

public class BlockBarrel extends BlockRotatedPillar implements ITileEntityProvider, IRegisterable, IHasRecipes {
	private IIcon iconBottom;
	private static final int metaForItemRender = 1;
	
	public BlockBarrel() {
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabDecorations);
		setStepSound(soundTypeWood);
		setHardness(2.5F);
		isBlockContainer = true;
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("barrel"));
		setBlockTextureName(StringUtil.name.domainedTexture("barrel"));
		GameRegistry.registerBlock(this, "barrel");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, metaForItemRender));
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
	public int getRenderType() {
		return ModUpToDateMod.barrelRenderId;
	}
	
	@Override
	public int damageDropped(int p_149692_1_) {
		return metaForItemRender;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		VanillaPistonStyleOrientationState state = new VanillaPistonStyleOrientationState(world, x, y, z, player);
    world.setBlockMetadataWithNotify(x, y, z, state.metadata(), 2);
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

