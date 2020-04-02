package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockNetherWart extends Block implements IRegisterable, IHasRecipes {

	public BlockNetherWart() {
		super(Material.grass);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(StringUtil.name.domainedUnlocalized("nether_wart_block"));
		this.setBlockTextureName(StringUtil.name.domainedTexture("nether_wart_block"));
		GameRegistry.registerBlock(this, "nether_wart_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.redColor;
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.netherWartBlock),
				"###",
				"###",
				"###",
				'#', Items.nether_wart
				);
	}
	
}
