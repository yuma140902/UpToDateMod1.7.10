package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceAcacia extends BlockFence implements IRegisterable {
	public FenceAcacia() {
		super("planks_acacia", Material.wood);
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_acacia");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_acacia");
		GameRegistry.registerBlock(this, "fence_acacia");
	}
	
//	/**
//   * Returns true if the specified block can be connected by a fence
//   */
//	@Override
//  public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_)
//  {
//    Block block = p_149826_1_.getBlock(p_149826_2_, p_149826_3_, p_149826_4_);
//    if(block.getMaterial().isOpaque() && block.renderAsNormalBlock()) {
//    	return block.getMaterial() != Material.gourd;
//    }
//    else if(block == this || block == Blocks.fence_gate || block == Blocks.fence) {
//    	return true;
//    }
//    else {
//    	return false;
//    }
//  } //異なる種類のフェンスが互いにくっつかないのは仕様?
}
