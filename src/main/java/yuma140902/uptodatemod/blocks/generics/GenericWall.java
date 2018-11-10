package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class GenericWall extends BlockWall implements IRegisterable {

	private Block block;
	private String name;
	
	public GenericWall(Block block, String name) {
		super(block);
		this.block = block;
		this.name = name;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return block.getIcon(side, meta);
	}

	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		GameRegistry.registerBlock(this, name);
	}
	
}
