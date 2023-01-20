package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

/**
 * 既存のフルサイズのブロックに対応する壁ブロック。
 * <p>
 * 普通は継承する必要はなく、{@link StairsBuilder}を使ってインスタンスを生成すればよい
 * </p>
 */
public class BlockGenericStairs extends BlockStairs implements IRegisterable, IHasRecipes {

	private final Block baseBlock;
	private final int meta;
	private final String name;
	
	protected BlockGenericStairs(Block baseBlock, int meta, String name) {
		super(baseBlock, meta);
		this.baseBlock = baseBlock;
		this.meta = meta;
		this.name = name;
		this.setHarvestLevel(baseBlock.getHarvestTool(0), baseBlock.getHarvestLevel(0));
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void register() {
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(this, 4),
				"  #",
				" ##",
				"###",
				'#', new ItemStack(baseBlock, 1, meta)
				);
	}
}
