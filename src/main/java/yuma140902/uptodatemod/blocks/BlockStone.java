package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.items.ItemBlockStone;
import yuma140902.uptodatemod.util.StringUtil;

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
		this.setBlockTextureName(StringUtil.getDomainedTextureName("stone"));
		GameRegistry.registerBlock(this, ItemBlockStone.class, "stone");
		
		for(int meta = 1; meta <= META_MAX; ++meta) {
			OreDictionary.registerOre("stone" + oredicNames[meta], new ItemStack(this, 1, meta));
		}
	}
	
	private IIcon[] iIcons = new IIcon[META_MAX + 1];
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for(int i = 1; i <= META_MAX; ++i) { //メタデータ0は、ない
			iIcons[i] = register.registerIcon(getTextureName() + StringUtil.surfix("_", names[i]));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iIcons[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"unchecked", "rawtypes"})
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
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(new ItemStack(this, 4, META_POLISHED_ANDESITE),
				"##",
				"##",
				'#', "stoneAndesite"
			)
		);
		
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(this, 4, META_POLISHED_DIORITE),
					"##",
					"##",
					'#', "stoneDiorite"
				)
			);
		
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(this, 4, META_POLISHED_GRANITE),
					"##",
					"##",
					'#', "stoneGranite"
				)
			);
		
		//丸石+ネザー水晶->閃緑岩
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.stone, 2, BlockStone.META_DIORITE),
				"SN",
				"NS",
				'S', Blocks.cobblestone,
				'N', Items.quartz
				);
		
		//閃緑岩+丸石->安山岩
		GameRegistry.addShapelessRecipe(
				new ItemStack(MyBlocks.stone, 2, BlockStone.META_ANDESITE),
				Blocks.cobblestone,
				new ItemStack(MyBlocks.stone, 1, BlockStone.META_DIORITE)
				);
		
		//閃緑岩+ネザー水晶->花崗岩
		GameRegistry.addShapelessRecipe(
				new ItemStack(MyBlocks.stone, 2, BlockStone.META_GRANITE),
				Items.quartz,
				new ItemStack(MyBlocks.stone, 1, BlockStone.META_DIORITE)
				);
	}
}
