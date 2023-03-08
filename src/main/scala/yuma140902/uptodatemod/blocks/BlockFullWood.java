package yuma140902.uptodatemod.blocks;

import static yuma140902.yumalib.api.McConst.*;
import java.util.List;
import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStrippedLog;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.items.ItemBlockMultiName;

public class BlockFullWood extends Block implements IRegisterable, IHasRecipes {
	
	@Nonnull
	public static final String[] names = new String[] {			"oak", 					"spruce", 				"birch", 					"jungle", 				"acacia", 					"dark_oak"};
	
	private static final Block[] metaToLog = new Block[] {	Blocks.log, 		Blocks.log, 			Blocks.log, 			Blocks.log, 			Blocks.log2, 				Blocks.log2};
	private static final int[] metaToLogMeta = new int[] {Meta$.MODULE$.LOG_OAK(), 	Meta$.MODULE$.LOG_SPRUCE(), 	Meta$.MODULE$.LOG_BIRCH(), 	Meta$.MODULE$.LOG_JUNGLE(), 	Meta$.MODULE$.LOG2_ACACIA(), 	Meta$.MODULE$.LOG2_DARK_OAK()};
	private static final int[] metaToPlankMeta = new int[] {	Meta$.MODULE$.PLANK_OAK(),	Meta$.MODULE$.PLANK_SPRUCE(), Meta$.MODULE$.PLANK_BIRCH(), Meta$.MODULE$.PLANK_JUNGLE(), Meta$.MODULE$.PLANK_ACACIA(), Meta$.MODULE$.PLANK_DARKOAK()};
				
	public static final int
			OAK = 0,
			SPRUCE = 1,
			BIRCH = 2,
			JUNGLE = 3,
			ACACIA = 4,
			DARK_OAK = 5;
	
	public static final int META_MAX = 5;
	
	public static BlockGenericStrippedLog getStrippedLog(int meta) {
		meta = MathHelper.clamp_int(meta, 0, META_MAX);
		switch(meta) {
			case OAK:
				return MyBlocks.strippedLogOak;
			case SPRUCE:
				return MyBlocks.strippedLogSpruce;
			case BIRCH:
				return MyBlocks.strippedLogBirch;
			case JUNGLE:
				return MyBlocks.strippedLogJungle;
			case ACACIA:
				return MyBlocks.strippedLogAcacia;
			case DARK_OAK:
				return MyBlocks.strippedLogDarkOak;
			default:
				assert false;
				return MyBlocks.strippedLogOak;
		}
	}
	
	public BlockFullWood() {
		super(Material.wood);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(2.0F);
    this.setStepSound(soundTypeWood);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		meta = MathHelper.clamp_int(meta, 0, 5);
		return metaToLog[meta].getIcon(SIDE_NORTH(), metaToLogMeta[meta]);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int meta=0; meta<=META_MAX; ++meta) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("wood"));
		setBlockTextureName(StringUtil.name.domainedTexture("wood"));
		GameRegistry.registerBlock(this, CompanionItemBlock.class, "wood");
		for(int meta=0; meta<=META_MAX; ++meta) {
			OreDictionary.registerOre("logWood", new ItemStack(this, 1, meta));
		}
	}
	
	@Override
	public void registerRecipes() {
		for(int meta=0; meta<=META_MAX; ++meta) {
			ItemStack materialLog = new ItemStack(metaToLog[meta], 1, metaToLogMeta[meta]);
			RecipeRegister.addShaped(
					new ItemStack(this, 3, meta),
					"##",
					"##",
					'#', materialLog
					);
			
			ItemStack productPlank = new ItemStack(Blocks.planks, 4, metaToPlankMeta[meta]);
			RecipeRegister.addShapeless(productPlank, new ItemStack(this, 1, meta));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	public static class CompanionItemBlock extends ItemBlockMultiName {
		public CompanionItemBlock(Block block) {
			super(block, names);
		}
	}
}
