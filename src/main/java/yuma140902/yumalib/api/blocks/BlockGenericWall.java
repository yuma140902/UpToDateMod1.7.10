package yuma140902.yumalib.api.blocks;

import java.util.List;
import javax.annotation.Nullable;
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
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.util.NameProvider;

public class BlockGenericWall extends BlockWall implements IRegisterable, IHasRecipes {

	public static @Nullable BlockGenericWall constructIfNotNull(@Nullable Block block, int meta, String name, NameProvider nameProvider) {
		return (block == null) ? null : new BlockGenericWall(block, meta, name, nameProvider);
	}
	
	private Block block;
	private int meta;
	private String name;
	private NameProvider nameProvider;
	
	public BlockGenericWall(Block block, int meta, String name, NameProvider nameProvider) {
		super(block);
		this.block = block;
		this.meta = meta;
		this.name = name;
		this.nameProvider = nameProvider;
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return block.getIcon(side, this.meta);
	}

	@Override
	public void register() {
		this.setBlockName(nameProvider.domainedUnlocalized(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs craetiveTab, List list) {
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
	public boolean canConnectWallTo(IBlockAccess blockAccess, int x, int y, int z) {
		Block block = blockAccess.getBlock(x, y, z);
    return !(block instanceof BlockGenericWall) && !(block instanceof BlockFenceGate) ? (block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false) : true;

	}
}
