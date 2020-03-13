package yuma140902.mcmodlib.blocks;

import javax.annotation.Nullable;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import yuma140902.mcmodlib.IHasRecipes;
import yuma140902.mcmodlib.IRegisterable;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGenericStairs extends BlockStairs implements IRegisterable, IHasRecipes {

	public static @Nullable BlockGenericStairs constructIfNotNull(@Nullable Block baseBlock, int meta, String name) {
		return (baseBlock == null) ? null : new BlockGenericStairs(baseBlock, meta, name);
	}
	
	private Block baseBlock;
	private int meta;
	private String name;
	
	public BlockGenericStairs(Block baseBlock, int meta, String name) {
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
		this.setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		GameRegistry.registerBlock(this, name);
	}
	
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
