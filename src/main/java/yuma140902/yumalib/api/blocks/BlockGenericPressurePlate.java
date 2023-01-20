package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

/**
 * 既存のフルサイズのブロックに対応する感圧板ブロック。
 * <p>
 * 普通は継承する必要はない
 * </p>
 */
public class BlockGenericPressurePlate extends BlockPressurePlate implements IRegisterable, IHasRecipes {

	private final int plankMeta;
	private final String name;

	/**
	 * @param plankMeta テクスチャとして使用するBlockPlankのメタ
	 * @param name 名前。ModIDなし
	 */
	public BlockGenericPressurePlate(int plankMeta, String name) {
		super("planks_oak", Material.wood, Sensitivity.everything);
		this.plankMeta = plankMeta;
		this.name = name;
		setHardness(0.5F);
		setStepSound(soundTypeWood);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.planks.getIcon(0, plankMeta);
	}
	
	@Override
	public void register() {
		setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(this),
				"##",
				'#', new ItemStack(Blocks.planks, 1, plankMeta)
				);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || isBlockFence(world.getBlock(x, y - 1, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		boolean flag = false;
		
		if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !isBlockFence(world.getBlock(x, y - 1, z))) {
			flag = true;
		}
		
		if (flag) {
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
	
	// net.minecraft.BlockFence.func_149825_a(Block)の代わり。
	// TODO: いつかMixinかなにかで置き換えたい
	public static boolean isBlockFence(Block block){
		return block instanceof BlockFence;
	}
	
}
