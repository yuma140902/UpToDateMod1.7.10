package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockSmoothStone extends Block implements IRegisterable, IHasRecipes {
	
	public BlockSmoothStone() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("smooth_stone"));
		setBlockTextureName("minecraft:stone_slab_top");
		GameRegistry.registerBlock(this, "smooth_stone");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addSmelting(Blocks.stone, new ItemStack(this), 1.0f);
		RecipeRegister.addShaped(
				new ItemStack(Blocks.stone_slab, 6),
				"###",
				'#', new ItemStack(this, 1)
				);
	}
	
}
