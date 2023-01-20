package yuma140902.yumalib.api.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

/**
 * 既存のフルサイズのブロックに対応する壁ブロック。
 * <p>
 * 普通は継承する必要はなく、{@link WallBuilder}を使ってインスタンスを生成すればよい
 * </p>
 */
public class BlockGenericWall extends BlockWall implements IRegisterable, IHasRecipes {

	private final Block block;
	private final int meta;
	private final String name;
	
	protected BlockGenericWall(Block block, int meta, String name) {
		super(block);
		this.block = block;
		this.meta = meta;
		this.name = name;
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return block.getIcon(side, this.meta);
	}

	@Override
	public void register() {
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	@SuppressWarnings({"raw", "unchecked"})
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		list.add(new ItemStack(item));
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(this, 6),
				"###",
				"###",
				'#', new ItemStack(this.block, 1, this.meta));
	}
	
	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canConnectWallTo(IBlockAccess blockAccess, int x, int y, int z) {
		Block block = blockAccess.getBlock(x, y, z);
    return !(block instanceof BlockGenericWall) && !(block instanceof BlockFenceGate) ? (block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false) : true;

	}
}
