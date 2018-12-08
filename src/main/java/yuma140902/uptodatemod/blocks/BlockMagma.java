package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockMagma extends Block implements IRegisterable {
	
	public BlockMagma() {
		super(Material.rock);
		setLightLevel(0.2F);
		setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".magma_block");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":magma_block");
		GameRegistry.registerBlock(this, "magma_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.netherrackColor;
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (!entity.isImmuneToFire() && entity instanceof EntityLivingBase) {
			entity.attackEntityFrom(DamageSource.generic, 1.0F);
		}
		
		super.onEntityWalking(world, x, y, z, entity);
	}
}
