package yuma140902.uptodatemod;

import static yuma140902.uptodatemod.util.Stat.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.ListUtils;

public final class Recipes {
	private Recipes() {}
	
	public static final ItemStack
		PLANK_ACACIA = new ItemStack(Blocks.planks, 1, PLANK_META_ACACIA),
		PLANK_BIRCH = new ItemStack(Blocks.planks, 1, PLANK_META_BIRCH),
		PLANK_DARK_OAK = new ItemStack(Blocks.planks, 1, PLANK_META_DARKOAK),
		PLANK_JUNGLE = new ItemStack(Blocks.planks, 1, PLANK_META_JUNGLE),
		PLANK_SPRUCE = new ItemStack(Blocks.planks, 1, PLANK_META_SPRUCE),
		PLANK_OAK = new ItemStack(Blocks.planks, 1, PLANK_META_OAK);
	
	public static void removeVanillaRecipes() {
		List<String> removeRecipesOutputNameList = new ArrayList<>();
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.doors)) {
			removeRecipesOutputNameList.add("minecraft:wooden_door");
		}
		if(ModConfigCore.recipeRemove_oldFenceRecipe || DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.fences)) {
			removeRecipesOutputNameList.add("minecraft:fence");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.fenceGates)) {
			removeRecipesOutputNameList.add("minecraft:fence_gate");
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.buttons)) {
			removeRecipesOutputNameList.add("minecraft:wooden_button");
		}
		
		removeRecipesByOutputName(removeRecipesOutputNameList);
	}
	
	//クラフト結果のアイテムの内部名称によって削除するレシピを指定します。
	//レシピは最初に見つかった一つだけが削除されます。
	@SuppressWarnings("unchecked")
	public static void removeRecipesByOutputName(List<String> outputNameList) {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		List<IRecipe> removeList = new ArrayList<IRecipe>();
		FMLControlledNamespacedRegistry<Item> itemRegistry = GameData.getItemRegistry();
		
		for(IRecipe recipe : recipes) {
			//see: http://forum.minecraftuser.jp/viewtopic.php?f=39&t=33757
			if(recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() != null) {
				Item outputItem = recipe.getRecipeOutput().getItem();
				String name = itemRegistry.getNameForObject(outputItem);
				
				if(ListUtils.contains(outputNameList, name)) {
					// このループ内では削除はせず、い)ったん削除予定リストに入れる
					removeList.add(recipe);

					outputNameList.remove(name);
				}
			}
		}
		
		// 削除する
		for(IRecipe removeRecipe : removeList) {
			recipes.remove(removeRecipe);
		}
	}
	
	public static void register() {
		Iterator<Block> blocks = MyBlocks.iterator();
		while (blocks.hasNext()) {
			Block block = blocks.next();
			if(block instanceof IHasRecipes) {
				((IHasRecipes) block).registerRecipes();
			}
		}
		
		Iterator<Item> items = MyItems.iterator();
		while (items.hasNext()) {
			Item item = items.next();
			if(item instanceof IHasRecipes) {
				((IHasRecipes) item).registerRecipes();
			}
		}
		
		registerDoorRecipes();
		registerFenceRecipes();
		registerFenceGateRecipes();
		registerButtonRecipes();
		registerStarisRecipes();
		registerSlabRecipes();
		registerWallRecipes();
		
	}
	
	private static void registerDoorRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(Items.wooden_door, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_OAK
				);
	}
	
	
	private static void registerFenceRecipes() {
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(Blocks.fence, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_OAK,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceAcacia, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_ACACIA,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceBirch, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_BIRCH,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceDarkOak, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_DARK_OAK,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceJungle, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_JUNGLE,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceSpruce, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_SPRUCE,
				'|', "stickWood"
			)
		);
		
	}

	private static void registerFenceGateRecipes() {
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(Blocks.fence_gate),
				"|#|",
				"|#|",
				'#', PLANK_OAK,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceGateAcacia),
				"|#|",
				"|#|",
				'#', PLANK_ACACIA,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceGateBirch),
				"|#|",
				"|#|",
				'#', PLANK_BIRCH,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceGateDarkOak),
				"|#|",
				"|#|",
				'#', PLANK_DARK_OAK,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceGateJungle),
				"|#|",
				"|#|",
				'#', PLANK_JUNGLE,
				'|', "stickWood"
			)
		);
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MyBlocks.fenceGateSpruce),
				"|#|",
				"|#|",
				'#', PLANK_SPRUCE,
				'|', "stickWood"
			)
		);
	}
	
	private static void registerButtonRecipes() {
		GameRegistry.addShapelessRecipe(
				new ItemStack(Blocks.wooden_button),
				PLANK_OAK
				);
		
	}
	
	private static void registerStarisRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.stairsPolishedAndesite, 4),
				"  #",
				" ##",
				"###",
				'#', "stoneAndesitePolished"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.stairsPolishedDiorite, 4),
				"  #",
				" ##",
				"###",
				'#', "stoneDioritePolished"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.stairsPolishedGranite, 4),
				"  #",
				" ##",
				"###",
				'#', "stoneGranitePolished"
		));
	}
	
	private static void registerSlabRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.slabGranite, 6),
				"###",
				'#', "stoneGranite"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.slabDiorite, 6),
				"###",
				'#', "stoneDiorite"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.slabAndesite, 6),
				"###",
				'#', "stoneAndesite"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.slabPolishedGranite, 6),
				"###",
				'#', "stoneGranitePolished"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.slabPolishedDiorite, 6),
				"###",
				'#', "stoneDioritePolished"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.slabPolishedAndesite, 6),
				"###",
				'#', "stoneAndesitePolished"
		));
	}
	
	private static void registerWallRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.wallAndesite, 6),
				"###",
				"###",
				'#', "stoneAndesite"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.wallDiorite, 6),
				"###",
				"###",
				'#', "stoneDiorite"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(MyBlocks.wallGranite, 6),
				"###",
				"###",
				'#', "stoneGranite"
		));
	}
	
}
