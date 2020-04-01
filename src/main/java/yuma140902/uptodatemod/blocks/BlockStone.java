package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.items.ItemBlockStone;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockStone extends Block implements IRegisterable, IHasRecipes {
	public static final int META_MAX = 6;
	public static final int META_GRANITE = 1;
	public static final int META_POLISHED_GRANITE = 2;
	public static final int META_DIORITE = 3;
	public static final int META_POLISHED_DIORITE = 4;
	public static final int META_ANDESITE = 5;
	public static final int META_POLISHED_ANDESITE = 6;
	
	public static final String[] names = new String[] {"", "granite", "polished_granite", "diorite", "polished_diorite", "andesite", "polished_andesite"};
	public static final String[] oredicNames = new String[] {"", "Granite", "GranitePolished", "Diorite", "DioritePolished", "Andesite", "AndesitePolished"};
	
	public BlockStone() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightLevel(0.0F);
	}
	
	@Override
	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
		return true;
	}
	
	@Override
	public void register() {
		this.setBlockName(StringUtil.getDomainedUnlocalizedName("stone"));
		GameRegistry.registerBlock(this, ItemBlockStone.class, "stone");
		
		for(int meta = 1; meta <= META_MAX; ++meta) {
			OreDictionary.registerOre("stone" + oredicNames[meta], new ItemStack(this, 1, meta));
		}
	}
	
	private IIcon[] iIcons = new IIcon[META_MAX + 1];
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		iIcons[META_GRANITE] = register.registerIcon(StringUtil.getDomainedTextureName("stone_granite"));
		iIcons[META_POLISHED_GRANITE] = register.registerIcon(StringUtil.getDomainedTextureName("stone_polished_granite"));
		iIcons[META_DIORITE] = register.registerIcon(StringUtil.getDomainedTextureName("stone_diorite"));
		iIcons[META_POLISHED_DIORITE] = register.registerIcon(StringUtil.getDomainedTextureName("stone_polished_diorite"));
		iIcons[META_ANDESITE] = register.registerIcon(StringUtil.getDomainedTextureName("stone_andesite"));
		iIcons[META_POLISHED_ANDESITE] = register.registerIcon(StringUtil.getDomainedTextureName("stone_polished_andesite"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iIcons[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for(int i = 1; i <= META_MAX; ++i) { //メタデータ0は、なし
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public void registerRecipes() {
		//4つ並べて磨かれた〇〇
		
		RecipeRegister.addShapedOre(
			new ItemStack(this, 4, META_POLISHED_ANDESITE),
				"##",
				"##",
				'#', "stoneAndesite"
		);
		
		RecipeRegister.addShapedOre(
			new ItemStack(this, 4, META_POLISHED_DIORITE),
				"##",
				"##",
				'#', "stoneDiorite"
		);
		
		RecipeRegister.addShapedOre(
			new ItemStack(this, 4, META_POLISHED_GRANITE),
				"##",
				"##",
				'#', "stoneGranite"
		);
		
		//丸石+ネザー水晶->閃緑岩
		RecipeRegister.addShapedOre(
			new ItemStack(MyBlocks.stone, 2, BlockStone.META_DIORITE),
				"SN",
				"NS",
				'S', "cobblestone",
				'N', "gemQuartz"
		);
		
		//閃緑岩+丸石->安山岩
		RecipeRegister.addShapelessOre(
			new ItemStack(MyBlocks.stone, 2, BlockStone.META_ANDESITE),
				"cobblestone",
				"stoneDiorite"
		);
		
		//閃緑岩+ネザー水晶->花崗岩
		RecipeRegister.addShapelessOre(
				new ItemStack(MyBlocks.stone, 2, BlockStone.META_GRANITE),
				"gemQuartz",
				"stoneDiorite"
		);
	}
}
