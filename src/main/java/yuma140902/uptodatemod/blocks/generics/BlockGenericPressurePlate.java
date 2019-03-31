package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGenericPressurePlate extends BlockPressurePlate implements IRegisterable, IHasRecipes {

	private int plankMeta;
	private String name;
	
	public BlockGenericPressurePlate(int plankMeta, String name) {
		super("planks_oak", Material.wood, Sensitivity.everything);
		this.plankMeta = plankMeta;
		this.name = name;
		setHardness(0.5F);
		setStepSound(soundTypeWood);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.planks.getIcon(0, plankMeta);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(this),
				"##",
				'#', new ItemStack(Blocks.planks, 1, plankMeta)
				);
	}
	
}
