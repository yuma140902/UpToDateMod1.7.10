package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.context.Contexts;

public class BlockGenericButton extends BlockButtonWood implements IRegisterable, IHasRecipes {
	private int texture_plank_meta;
	private String name;
	
	public BlockGenericButton(int texture_plank_meta, String name) {
		this.texture_plank_meta = texture_plank_meta;
		this.name = name;
		this.setHardness(0.5F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.planks.getIcon(1, texture_plank_meta);
	}

	@Override
	public void register() {
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapeless(
				new ItemStack(this),
				new ItemStack(Blocks.planks, 1, texture_plank_meta)
				);
	}
}
