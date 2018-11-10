package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
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
	
}
