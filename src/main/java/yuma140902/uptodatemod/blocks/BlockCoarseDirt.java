package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockCoarseDirt extends Block implements IRegisterable, IHasRecipes {

	public static void onUseHoeEvent(UseHoeEvent event) {
		if(event.world.getBlock(event.x, event.y, event.z) == MyBlocks.coarseDirt) {
			event.world.setBlock(event.x, event.y, event.z, Blocks.dirt);
			event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath(), 1.0F, 0.8F);
			event.setResult(Result.ALLOW);
		}
	}
	
	
	public BlockCoarseDirt() {
		super(Material.ground);
		setHardness(0.5F);
		setStepSound(soundTypeGravel);
		setHarvestLevel("shovel", 0);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(StringUtil.getDomainedUnlocalizedName("coarse_dirt"));
		this.setBlockTextureName(StringUtil.getDomainedTextureName("coarse_dirt"));
		GameRegistry.registerBlock(this, "coarse_dirt");
	}
	
	@Override
	public boolean
			canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return Blocks.dirt.canSustainPlant(world, x, y, z, direction, plantable);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int p_149673_5_) {
		Material material = world.getBlock(x, y + 1, z).getMaterial();

    if (material == Material.snow || material == Material.craftedSnow)
    {
        return Blocks.grass.getIcon(world, x, y, z, p_149673_5_);
    }
		
		return super.getIcon(world, x, y, z, p_149673_5_);
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.coarseDirt, 4, 0),
				"GD",
				"DG",
				'D', Blocks.dirt,
				'G', Blocks.gravel
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.coarseDirt, 4, 0),
				"DG",
				"GD",
				'D', Blocks.dirt,
				'G', Blocks.gravel
				);
	}
	
}
