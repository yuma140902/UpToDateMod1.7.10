package yuma140902.uptodatemod.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockSeaLantern extends Block implements IRegisterable, IHasRecipes {
	public BlockSeaLantern() {
		super(Material.glass);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(0.3F);
		setLightLevel(1.0F);
		setStepSound(soundTypeGlass);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("sea_lantern"));
		setBlockTextureName(StringUtil.getDomainedTextureName("sea_lantern"));
		GameRegistry.registerBlock(this, "sea_lantern");
	}
	
	@Override
	public int quantityDropped(Random rand) {
		return 2 + rand.nextInt(2);
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random rand) {
		return MathHelper.clamp_int(quantityDropped(rand) + rand.nextInt(fortune + 1), 1, 5);
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return MyItems.prismarineCrystal;
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.quartzColor;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.seaLantern),
				"#C#",
				"CCC",
				"#C#",
				'#', MyItems.prismarineShard,
				'C', MyItems.prismarineCrystal
				);
	}
}
