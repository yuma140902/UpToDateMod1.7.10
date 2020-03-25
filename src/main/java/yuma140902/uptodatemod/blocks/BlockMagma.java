package yuma140902.uptodatemod.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockMagma extends Block implements IRegisterable, IHasRecipes {
	
	public BlockMagma() {
		super(Material.rock);
		setLightLevel(0.2F);
		setHardness(0.5F);
		setStepSound(soundTypeStone);
		setTickRandomly(true);
		
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(StringUtil.getDomainedUnlocalizedName("magma_block"));
		this.setBlockTextureName(StringUtil.getDomainedTextureName("magma_block"));
		GameRegistry.registerBlock(this, "magma_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.netherrackColor;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + 255f/256f, (double)z + this.maxZ);
  }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (!entity.isImmuneToFire() && !entity.isSneaking() && entity instanceof EntityLivingBase) {
			entity.attackEntityFrom(DamageSource.onFire, 1.0F);
		}
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		
    Block blockAbove = world.getBlock(x, y + 1, z);

    if (blockAbove == Blocks.water || blockAbove == Blocks.flowing_water)
    {
        world.setBlockToAir(x, y + 1, z);
    }
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.magmaBlock),
				"OO",
				"OO",
				'O', Items.magma_cream
				);
	}
}
