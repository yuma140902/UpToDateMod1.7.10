package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.blocks.CustomSoundType;

public class BlockNetherWart extends Block implements IRegisterable, IHasRecipes {

	private Block.SoundType soundTypeNetherWart = new CustomSoundType(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "netherwart");
	
	public BlockNetherWart() {
		super(Material.grass);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeNetherWart);
		this.setCreativeTab(CreativeTabs.tabBlock);
		/* 本当はクワを適正ツールにしたいが、
		 * ItemHoeがItemToolを参照しないせいで、
		 * Item#getDigSpeed()がBlock#harvestTool[]を参照しない。
		 * ASMでItemHoe#getDigSpeed()を上書きする必要がありそう。 */
		this.setHarvestLevel("axe", 0);
	}
	
	@Override
	public void register() {
		this.setBlockName(StringUtil.name.domainedUnlocalized("nether_wart_block"));
		this.setBlockTextureName(StringUtil.name.domainedTexture("nether_wart_block"));
		GameRegistry.registerBlock(this, "nether_wart_block");
	}
	
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.redColor;
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.netherWartBlock),
				"###",
				"###",
				"###",
				'#', Items.nether_wart
				);
	}
	
}
