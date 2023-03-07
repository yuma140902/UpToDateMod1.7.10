package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

/**
 * 既存のフルサイズのブロックに対応するフェンスブロック。
 * <p>
 * 普通は継承する必要はない
 * </p>
 */
public class BlockGenericFence extends BlockFence implements IRegisterable {
	private final String name;

	/**
	 * @param texture テクスチャ名。ModIDなし
	 * @param name 名前。ModIDなし
	 */
	public BlockGenericFence(String texture, String name) {
		super(texture, Material.wood);
		this.name = name;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	// 応急処置
	// TODO: ちゃんと実装する
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side == ForgeDirection.UP;
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
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture(name));
		GameRegistry.registerBlock(this, name);
	}
}
