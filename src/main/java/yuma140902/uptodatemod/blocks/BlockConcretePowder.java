package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.items.ItemBlockColored;

public class BlockConcretePowder extends BlockFalling implements IRegisterable {

	public BlockConcretePowder() {
		super(Material.sand);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeSand);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".concrete_powder");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":concrete_powder");
		GameRegistry.registerBlock(this, ItemBlockColored.class, "concrete_powder");
	}
	
}
