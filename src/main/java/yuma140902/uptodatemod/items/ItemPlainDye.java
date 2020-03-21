package yuma140902.uptodatemod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.items.generics.ItemMultiMeta;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;

public class ItemPlainDye extends ItemMultiMeta implements IRegisterable, IHasRecipes {

	public static final int BLUE=0, WHITE=1, BLACK=2, BROWN=3;
	
	public ItemPlainDye() {
		super("dye", new String[] {"blue", "white", "black", "brown"}, StringUtil.domainedTextureNames("blue_dye", "white_dye", "black_dye", "brown_dye"));
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public void register() {
		super.register();
		for(int i=0; i<4; ++i) {
			OreDictionary.registerOre("dye", new ItemStack(this, 1, i));
		}
		OreDictionary.registerOre("dyeBlue", new ItemStack(this, 1, BLUE));
		OreDictionary.registerOre("dyeWhite", new ItemStack(this, 1, WHITE));
		OreDictionary.registerOre("dyeBlack", new ItemStack(this, 1, BLACK));
		OreDictionary.registerOre("dyeBrown", new ItemStack(this, 1, BROWN));
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapelessOre(new ItemStack(this, 1, BLUE), "dyeBlue");
		RecipeRegister.addShapelessOre(new ItemStack(this, 1, WHITE), "dyeWhite");
		RecipeRegister.addShapelessOre(new ItemStack(this, 1, BLACK), "dyeBlack");
		RecipeRegister.addShapelessOre(new ItemStack(this, 1, BROWN), "dyeBrown");
	}
	
}
