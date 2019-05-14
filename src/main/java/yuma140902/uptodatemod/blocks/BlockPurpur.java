package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockPurpur extends Block implements IRegisterable {
	
	public BlockPurpur() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("purpur_block"));
		setBlockTextureName(StringUtil.getDomainedTextureName("purpur_block"));
		GameRegistry.registerBlock(this, "purpur_block");
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return !(entity instanceof EntityDragon);
	}
}
