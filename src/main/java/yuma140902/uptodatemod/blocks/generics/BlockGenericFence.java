package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGenericFence extends BlockFence implements IRegisterable {
	private String name;
	
	public BlockGenericFence(String texture, String name) {
		super(texture, Material.wood);
		this.name = name;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	/**
   * Returns true if the specified block can be connected by a fence
   */
	@Override
  public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_)
  {
    Block block = p_149826_1_.getBlock(p_149826_2_, p_149826_3_, p_149826_4_);
    if(block.getMaterial().isOpaque() && block.renderAsNormalBlock()) {
    	return block.getMaterial() != Material.gourd;
    }
    else if(
    		block == this
    		|| block instanceof BlockFenceGate
    		|| block instanceof BlockGenericFence
    		) {
    	return true;
    }
    else {
    	return false;
    }
  }

	@Override
	public void register() {
		this.setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		this.setBlockTextureName(StringUtil.getDomainedTextureName(name));
		GameRegistry.registerBlock(this, name);
	}
}
