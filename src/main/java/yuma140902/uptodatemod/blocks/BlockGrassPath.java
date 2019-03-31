package yuma140902.uptodatemod.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGrassPath extends Block implements IRegisterable {
	
	private IIcon iconTop;
	
	public BlockGrassPath() {
		super(Material.ground);
		this.setLightOpacity(0);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 15.0F / 16.0F, 1.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(0.65F);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("grass_path"));
		setBlockTextureName(StringUtil.getDomainedTextureName("grass_path"));
		GameRegistry.registerBlock(this, "grass_path");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(this.getTextureName() + "_side");
		this.iconTop = register.registerIcon(this.getTextureName() + "_top");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == Stat.SIDE_TOP ? this.iconTop : side == Stat.SIDE_BOTTOM ? Blocks.dirt.getIcon(side, 0) : blockIcon;
	}
	
	@Override
	public boolean shouldSideBeRendered(	IBlockAccess world, int x, int y, int z, int side) {
		if(side == Stat.SIDE_TOP) return true;
		
		int nextX = x;// + Facing.offsetsXForSide[side];
		int nextY = y;// + Facing.offsetsYForSide[side];
		int nextZ = z;// + Facing.offsetsZForSide[side]; //これでいいのかよくわからない
		Block nextBlock = world.getBlock(nextX, nextY, nextZ);
		return !nextBlock.isOpaqueCube();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		Block blockAbove = world.getBlock(x, y+1, z);
		if(blockAbove != null && blockAbove.isOpaqueCube()) {
			world.setBlock(x, y, z, Blocks.dirt);
		}
	}
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(Blocks.dirt);
	}
}
