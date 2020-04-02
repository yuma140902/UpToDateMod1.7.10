package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockEndStoneBricks extends Block implements IRegisterable, IHasRecipes {
	
	public BlockEndStoneBricks() {
		super(Material.rock);
		setHardness(3.0F);
		setResistance(15.0F);
		setStepSound(soundTypePiston);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(StringUtil.name.domainedUnlocalized("end_stone_bricks"));
		this.setBlockTextureName(StringUtil.name.domainedTexture("end_stone_bricks"));
		GameRegistry.registerBlock(this, "end_stone_bricks");
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return !(entity instanceof EntityDragon);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.endStoneBricks, 4),
				"##",
				"##",
				'#', Blocks.end_stone
				);
	}
}
