package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockRedNetherBricks extends Block implements IRegisterable, IHasRecipes {

	public BlockRedNetherBricks() {
		super(Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void register() {
		this.setBlockName(StringUtil.name.domainedUnlocalized("red_nether_bricks"));
		this.setBlockTextureName(StringUtil.name.domainedTexture("red_nether_bricks"));
		GameRegistry.registerBlock(this, "red_nether_bricks");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.redNetherBricks),
				"WB",
				"BW",
				'B', Items.netherbrick,
				'W', Items.nether_wart
				);
		
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.redNetherBricks),
				"BW",
				"WB",
				'B', Items.netherbrick,
				'W', Items.nether_wart
				);
	}
}
