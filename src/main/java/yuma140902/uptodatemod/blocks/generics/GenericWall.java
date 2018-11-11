package yuma140902.uptodatemod.blocks.generics;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class GenericWall extends BlockWall implements IRegisterable {

	public static final int METADATA_NEUTRAL = OreDictionary.WILDCARD_VALUE;
	
	private Block block;
	private int meta;
	private String name;
	
	public GenericWall(Block block, String name) {
		this(block, METADATA_NEUTRAL, name);
	}
	
	public GenericWall(Block block, int meta, String name) {
		super(block);
		this.block = block;
		this.meta = meta;
		this.name = name;
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return this.meta == METADATA_NEUTRAL ? block.getIcon(side, meta) : block.getIcon(side, this.meta);
	}

	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		GameRegistry.registerBlock(this, name);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs craetiveTab, List list) {
		list.add(new ItemStack(item));
	}
}
