package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockPurpurPillar extends BlockRotatedPillar implements IRegisterable {
	
	public BlockPurpurPillar() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("purpur_pillar"));
		setBlockTextureName(StringUtil.getDomainedTextureName("purpur_pillar"));
		GameRegistry.registerBlock(this, "purpur_pillar");
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return !(entity instanceof EntityDragon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_) {
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		field_150164_N = register.registerIcon(getTextureName() + "_top");
	}
	
}
