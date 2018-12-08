package yuma140902.uptodatemod.blocks.generics;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.items.generics.ItemGenericSlab;

public class BlockGenericSlab extends BlockSlab {

	private Block baseBlock;
	private int meta;
	private String name;
	private BlockGenericSlab slab;
	private BlockGenericSlab slabDouble;
	
	public BlockGenericSlab(boolean isDouble, Block baseBlock, int meta, String name) {
		super(isDouble, baseBlock.getMaterial());
		this.baseBlock = baseBlock;
		this.meta = meta;
		this.name = name;
		setLightOpacity(0);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public void register(BlockGenericSlab slab, BlockGenericSlab doubleSlab) {
		this.slab = slab;
		this.slabDouble = doubleSlab;
		setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		GameRegistry.registerBlock(this, ItemGenericSlab.class, (isDouble() ? "double_" : "") + name);
	}
	
	public BlockGenericSlab getSlab() {
		return this.slab;
	}
	
	public BlockGenericSlab getSlabDouble() {
		return this.slabDouble;
	}
	
	public boolean isDouble() {
		return this.field_150004_a;
	}
	
	@Override
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(this.slab);
	}
	
	@Override
	public int damageDropped(int p_149692_1_) {
		return 0;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(this.slab);
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return isDouble() ? 2 : 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return baseBlock.getIcon(side, (meta & 0b0001) == 0 ? this.meta : 0);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		if(!isDouble())
			super.getSubBlocks(item, creativeTab, list);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
		return ModUpToDateMod.MOD_ID + "." + name;
	}
}
