package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class ItemIronNugget extends Item implements IRegisterable, IHasRecipes {
	
	public ItemIronNugget() {
		// TODO 自動生成されたコンストラクター・スタブ
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName("iron_nugget"));
		this.setTextureName(StringUtil.getDomainedTextureName("iron_nugget"));
		GameRegistry.registerItem(this, "iron_nugget");
		OreDictionary.registerOre("nuggetIron", this);
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShapelessOre(
				new ItemStack(this, 9),
				"ingotIron"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(Items.iron_ingot),
				"###",
				"###",
				"###",
				'#', "nuggetIron"
		);
		
		final float xp = 1.0F;
		RecipeRegister.addSmelting(Items.iron_door, new ItemStack(this), xp);
		RecipeRegister.addSmelting(MyBlocks.trapDoorIron, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_horse_armor, new ItemStack(this), xp);
		
		RecipeRegister.addSmelting(Items.iron_axe, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_hoe, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_pickaxe, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_shovel, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_sword, new ItemStack(this), xp);
		
		RecipeRegister.addSmelting(Items.iron_helmet, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_chestplate, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_leggings, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.iron_boots, new ItemStack(this), xp);
		
		RecipeRegister.addSmelting(Items.chainmail_helmet, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.chainmail_chestplate, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.chainmail_leggings, new ItemStack(this), xp);
		RecipeRegister.addSmelting(Items.chainmail_boots, new ItemStack(this), xp);
	}
	
}
