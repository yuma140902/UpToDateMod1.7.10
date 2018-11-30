package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockGenericStairs extends BlockStairs implements IRegisterable {

	private Block baseBlock;
	private int meta;
	private String name;
	
	public BlockGenericStairs(Block baseBlock, int meta, String name) {
		super(baseBlock, meta);
		this.baseBlock = baseBlock;
		this.meta = meta;
		this.name = name;
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		GameRegistry.registerBlock(this, name);
	}
}
