package yuma140902.uptodatemod.blocks;

import java.util.List;
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
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.util.BlockPos;
import yuma140902.yumalib.api.util.WorldUtils;

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
	
	/**
	 * 詳細は{@link BlockGrassPath}を参照
	 */
	private static boolean isEdge(World world, BlockPos pos, ForgeDirection direction) {
		Block block = WorldUtils.getBlock(world, pos.offset(direction));
		if(block != MyBlocks.magmaBlock && block != Blocks.air) return true;
		return false;
	}
	
	/**
	 * 詳細は{@link BlockGrassPath}を参照
	 */
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {

		BlockPos pos = new BlockPos(x, y, z);
		
		// ベースとなる当たり判定
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 255f/256f, 1.0f);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		
		if(isEdge(world, pos, ForgeDirection.EAST)) {  // EAST: +x
			this.setBlockBounds(255f/256f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		if(isEdge(world, pos, ForgeDirection.WEST)) {  // WEST: -x
			this.setBlockBounds(0.0f, 0.0f, 0.0f, 1f/256f, 1.0f, 1.0f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		if(isEdge(world, pos, ForgeDirection.SOUTH)) {  // SOUTH: +z
			this.setBlockBounds(0.0f, 0.0f, 255f/256f, 1.0f, 1.0f, 1.0f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		if(isEdge(world, pos, ForgeDirection.NORTH)) {
			this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1f/256f);
			super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		}
		
		// もとに戻す。戻さないとSelectionBoxの表示がおかしくなる
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
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
