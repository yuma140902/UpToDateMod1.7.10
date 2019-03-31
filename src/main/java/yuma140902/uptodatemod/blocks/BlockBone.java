package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockBone extends BlockRotatedPillar implements IRegisterable, IHasRecipes {
	
	public BlockBone() {
		super(Material.rock);
    setHardness(2.0F);
    setStepSound(soundTypeStone);
    setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("bone_block"));
		setBlockTextureName(StringUtil.getDomainedTextureName("bone_block"));
		GameRegistry.registerBlock(this, "bone_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.sandColor;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_) {
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(getTextureName() + "_side");
		field_150164_N = register.registerIcon(getTextureName() + "_top");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.boneBlock),
				"###",
				"###",
				"###",
				'#', new ItemStack(Items.dye, 1, 15) //骨粉
				);
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(Items.dye, 9, 15),
				MyBlocks.boneBlock
				);
	}
}
