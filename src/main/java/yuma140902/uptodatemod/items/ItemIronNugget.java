package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;

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
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(this, 9),
				"ingotIron"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				Items.iron_ingot,
				"###",
				"###",
				"###",
				'#', "nuggetIron"
		));
		
		final float xp = 1.0F;
		GameRegistry.addSmelting(Items.iron_door, new ItemStack(this), xp);
		GameRegistry.addSmelting(MyBlocks.trapDoorIron, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_horse_armor, new ItemStack(this), xp);
		
		GameRegistry.addSmelting(Items.iron_axe, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_hoe, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_pickaxe, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_shovel, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_sword, new ItemStack(this), xp);
		
		GameRegistry.addSmelting(Items.iron_helmet, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_chestplate, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_leggings, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.iron_boots, new ItemStack(this), xp);
		
		GameRegistry.addSmelting(Items.chainmail_helmet, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.chainmail_chestplate, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.chainmail_leggings, new ItemStack(this), xp);
		GameRegistry.addSmelting(Items.chainmail_boots, new ItemStack(this), xp);
	}
	
}
