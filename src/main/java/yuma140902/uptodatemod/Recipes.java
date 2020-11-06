package yuma140902.uptodatemod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.ListUtils;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.McConst;

public final class Recipes {
	private Recipes() {}
	
	public static final ItemStack
		PLANK_ACACIA = 		new ItemStack(Blocks.planks, 1, McConst.Meta.PLANK_ACACIA),
		PLANK_BIRCH = 		new ItemStack(Blocks.planks, 1, McConst.Meta.PLANK_BIRCH),
		PLANK_DARK_OAK = 	new ItemStack(Blocks.planks, 1, McConst.Meta.PLANK_DARKOAK),
		PLANK_JUNGLE = 		new ItemStack(Blocks.planks, 1, McConst.Meta.PLANK_JUNGLE),
		PLANK_SPRUCE = 		new ItemStack(Blocks.planks, 1, McConst.Meta.PLANK_SPRUCE),
		PLANK_OAK = 			new ItemStack(Blocks.planks, 1, McConst.Meta.PLANK_OAK);
	
	public static void removeVanillaRecipes() {
		List<String> removeRecipesOutputNameList = new ArrayList<>();
		if(EnumDisableableFeatures.doors.featureEnabled()) {
			removeRecipesOutputNameList.add("minecraft:wooden_door");
		}
		if(ModConfigCore.Recipe.removeOldFenceRecipe() || EnumDisableableFeatures.fences.featureEnabled()) {
			removeRecipesOutputNameList.add("minecraft:fence");
		}
		if(EnumDisableableFeatures.fenceGates.featureEnabled()) {
			removeRecipesOutputNameList.add("minecraft:fence_gate");
		}
		if(EnumDisableableFeatures.buttons.featureEnabled()) {
			removeRecipesOutputNameList.add("minecraft:wooden_button");
		}
		if(EnumDisableableFeatures.allKindsOfWalls.featureEnabled()) {
			removeRecipesOutputNameList.add("minecraft:nether_brick_fence");
		}
		
		removeRecipesByOutputName(removeRecipesOutputNameList);
	}
	
	//クラフト結果のアイテムの内部名称によって削除するレシピを指定します。
	//レシピは最初に見つかった一つだけが削除されます。
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
	
	public static void replaceRecipe(Predicate<IRecipe> predicator, Runnable recipeAdder) {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		IRecipe recipeToRemove = null;
		
		for(final IRecipe recipe : recipes) {
			if(predicator.test(recipe)) {
				recipeToRemove = recipe;
				break;
			}
		}
		
		if(recipeToRemove != null) {
			recipes.remove(recipeToRemove);
		}
		
		recipeAdder.run();
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
		
		
		RecipeRegister.addSmelting(new ItemStack(Blocks.stonebrick, 1, McConst.Meta.STONEBRICK_NORMAL), new ItemStack(Blocks.stonebrick, 1, McConst.Meta.STONEBRICK_CRACKED), McConst.EXP_BRICK);
		
		RecipeRegister.addShaped(new ItemStack(Blocks.stonebrick, 1, McConst.Meta.STONEBRICK_CHISELED), 
				"#",
				"#",
				'#', new ItemStack(Blocks.stone_slab, 1, McConst.Meta.SLAB_STONEBRICKS)
				);
	}
	
	private static void registerDoorRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(Items.wooden_door, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_OAK
				);
	}
	
	
	private static void registerFenceRecipes() {
		RecipeRegister.addShapedOre(
			new ItemStack(Blocks.fence, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_OAK,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
			new ItemStack(MyBlocks.fenceAcacia, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_ACACIA,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceBirch, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_BIRCH,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceDarkOak, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_DARK_OAK,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceJungle, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_JUNGLE,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceSpruce, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_SPRUCE,
				'|', "stickWood"
		);
		
	}

	private static void registerFenceGateRecipes() {
		RecipeRegister.addShapedOre(
				new ItemStack(Blocks.fence_gate),
				"|#|",
				"|#|",
				'#', PLANK_OAK,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceGateAcacia),
				"|#|",
				"|#|",
				'#', PLANK_ACACIA,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceGateBirch),
				"|#|",
				"|#|",
				'#', PLANK_BIRCH,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceGateDarkOak),
				"|#|",
				"|#|",
				'#', PLANK_DARK_OAK,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceGateJungle),
				"|#|",
				"|#|",
				'#', PLANK_JUNGLE,
				'|', "stickWood"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.fenceGateSpruce),
				"|#|",
				"|#|",
				'#', PLANK_SPRUCE,
				'|', "stickWood"
		);
	}
	
	private static void registerButtonRecipes() {
		RecipeRegister.addShapeless(
				new ItemStack(Blocks.wooden_button),
				PLANK_OAK
				);
		
	}
	
	private static void registerStarisRecipes() {
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.stairsPolishedAndesite, 4),
				"  #",
				" ##",
				"###",
				'#', "stoneAndesitePolished"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.stairsPolishedDiorite, 4),
				"  #",
				" ##",
				"###",
				'#', "stoneDioritePolished"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.stairsPolishedGranite, 4),
				"  #",
				" ##",
				"###",
				'#', "stoneGranitePolished"
		);
	}
	
	private static void registerSlabRecipes() {
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.slabGranite, 6),
				"###",
				'#', "stoneGranite"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.slabDiorite, 6),
				"###",
				'#', "stoneDiorite"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.slabAndesite, 6),
				"###",
				'#', "stoneAndesite"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.slabPolishedGranite, 6),
				"###",
				'#', "stoneGranitePolished"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.slabPolishedDiorite, 6),
				"###",
				'#', "stoneDioritePolished"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.slabPolishedAndesite, 6),
				"###",
				'#', "stoneAndesitePolished"
		);
		
		replaceRecipe(
			recipe -> {
				ItemStack output = recipe.getRecipeOutput();
				if(output == null) return false;
				return output.stackSize == 6 && output.getItemDamage() == McConst.Meta.SLAB_SANDSTONE && output.getItem() == Item.getItemFromBlock(Blocks.stone_slab);
			},
			() -> {
				RecipeRegister.addShaped(
						new ItemStack(Blocks.stone_slab, 6, 1),
						"###",
						'#', new ItemStack(Blocks.sandstone, 1, McConst.Meta.SANDSTONE_NORMAL)
						);
		});
	}
	
	private static void registerWallRecipes() {
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.wallAndesite, 6),
				"###",
				"###",
				'#', "stoneAndesite"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.wallDiorite, 6),
				"###",
				"###",
				'#', "stoneDiorite"
		);
		RecipeRegister.addShapedOre(
				new ItemStack(MyBlocks.wallGranite, 6),
				"###",
				"###",
				'#', "stoneGranite"
		);
		
		RecipeRegister.addShapedOre(
				new ItemStack(Blocks.nether_brick_fence, 6),
				"#-#",
				"#-#",
				'#', Blocks.nether_brick,
				'-', "ingotBrickNether"
				);
	}
	
}
