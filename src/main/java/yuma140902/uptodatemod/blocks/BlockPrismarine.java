package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockPrismarine extends Block implements IRegisterable, IHasRecipes {
	
	public BlockPrismarine() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("prismarine_block"));
		setBlockTextureName(StringUtil.getDomainedTextureName("prismarine_block"));
		GameRegistry.registerBlock(this, "prismarine_block");
		OreDictionary.registerOre("blockPrismarine", this);
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.cyanColor;
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.prismarineBlock),
				"##",
				"##",
				'#', MyItems.prismarineShard
				);
	}
}
