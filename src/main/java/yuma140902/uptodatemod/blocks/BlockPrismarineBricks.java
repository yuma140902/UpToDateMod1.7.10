package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.mcmodlib.api.IHasRecipes;
import yuma140902.mcmodlib.api.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockPrismarineBricks extends Block implements IRegisterable, IHasRecipes {
	public BlockPrismarineBricks() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("prismarine_brick"));
		setBlockTextureName(StringUtil.getDomainedTextureName("prismarine_bricks"));
		GameRegistry.registerBlock(this, "prismarine_brick");
		OreDictionary.registerOre("blockPrismarineBrick", this);
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.diamondColor;
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.prismarineBricks),
				"###",
				"###",
				"###",
				'#', MyItems.prismarineShard
				);
	}
}
