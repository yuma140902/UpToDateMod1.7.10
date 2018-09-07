package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceBirch extends BlockFence implements IRegisterable {
	public FenceBirch() {
		super("planks_birch", Material.wood);
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_birch");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_birch");
		GameRegistry.registerBlock(this, "fence_birch");
	}
}
