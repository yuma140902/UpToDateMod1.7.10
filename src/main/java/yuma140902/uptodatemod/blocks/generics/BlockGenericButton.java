package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGenericButton extends BlockButtonWood implements IRegisterable, IHasRecipes {
	private int texture_plank_meta;
	private String name;
	
	public BlockGenericButton(int texture_plank_meta, String name) {
		this.texture_plank_meta = texture_plank_meta;
		this.setHardness(0.5F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.name = name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.planks.getIcon(1, texture_plank_meta);
	}

	@Override
	public void register() {
		this.setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		this.setBlockTextureName(StringUtil.getDomainedTextureName(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addShapelessRecipe(
				new ItemStack(this),
				new ItemStack(Blocks.planks, 1, texture_plank_meta)
				);
	}
}
