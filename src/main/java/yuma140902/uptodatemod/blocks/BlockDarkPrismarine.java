package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockDarkPrismarine extends Block implements IRegisterable, IHasRecipes {
	public BlockDarkPrismarine() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("dark_prismarine_block"));
		setBlockTextureName(StringUtil.name.domainedTexture("dark_prismarine_block"));
		GameRegistry.registerBlock(this, "dark_prismarine_block");
		OreDictionary.registerOre("blockPrismarineDark", this);
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.diamondColor;
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.prismarineDark),
				"###",
				"#I#",
				"###",
				'#', MyItems.prismarineShard,
				'I', new ItemStack(Items.dye, 1, 0)
				);
	}
}
