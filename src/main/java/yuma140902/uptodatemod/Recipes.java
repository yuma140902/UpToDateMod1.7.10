package yuma140902.uptodatemod;

import static yuma140902.uptodatemod.util.Stat.*;
import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import yuma140902.uptodatemod.config.ModConfigCore;
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
		removeRecipesOutputNameList.add("minecraft:wooden_door");
		if(ModConfigCore.recipeRemove_oldFenceRecipe)
			removeRecipesOutputNameList.add("minecraft:fence");
		removeRecipesOutputNameList.add("minecraft:fence_gate");
		removeRecipesOutputNameList.add("minecraft:wooden_button");
		
		removeRecipesByOutputName(removeRecipesOutputNameList);
	}
	
	//クラフト結果のアイテムの内部名称によって削除するレシピを指定します。
	//レシピは最初に見つかった一つだけが削除されます。
	private static void removeRecipesByOutputName(List<String> outputNameList) {
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
		registerStoneRecipes();
		registerDoorRecipes();
		registerTrapDoorRecipes();
		registerFenceRecipes();
		registerFenceGateRecipes();
		registerButtonRecipes();
		registerPressurePlateRecipes();
		registerBoatRecipes();
		registerStarisRecipes();
		registerSlabRecipes();
		registerWallRecipes();
		registerWoodRecipes();
		
		MyBlocks.coarseDirt.registerRecipes();
		
		MyBlocks.redNetherBricks.registerRecipes();
		
		MyBlocks.netherWartBlock.registerRecipes();
		
		MyBlocks.endStoneBricks.registerRecipes();
		
		MyBlocks.boneBlock.registerRecipes();
		
		MyBlocks.magmaBlock.registerRecipes();
		
		MyBlocks.redSandStone.registerRecipes();
		
		registerOceanMonumentRecipes();
		
		MyItems.cookedMutton.registerRecipes();
		MyItems.armorStand.registerRecipes();
	}
	
	private static void registerWoodRecipes() {
		MyBlocks.strippedLogAcacia.registerRecipes();
		MyBlocks.strippedLogBirch.registerRecipes();
		MyBlocks.strippedLogDarkOak.registerRecipes();
		MyBlocks.strippedLogJungle.registerRecipes();
		MyBlocks.strippedLogOak.registerRecipes();
		MyBlocks.strippedLogSpruce.registerRecipes();
	}
	
	private static void registerStoneRecipes() {
		MyBlocks.stone.registerRecipes();
	}

	private static void registerDoorRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(Items.wooden_door, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_OAK
				);
		
		MyItems.itemDoorAcacia.registerRecipes();
		MyItems.itemDoorBirch.registerRecipes();
		MyItems.itemDoorDarkOak.registerRecipes();
		MyItems.itemDoorJungle.registerRecipes();
		MyItems.itemDoorSpruce.registerRecipes();
	}
	
	private static void registerTrapDoorRecipes() {
		MyBlocks.trapDoorAcacia.registerRecipes();
		MyBlocks.trapDoorBirch.registerRecipes();
		MyBlocks.trapDoorDarkOak.registerRecipes();
		MyBlocks.trapDoorJungle.registerRecipes();
		MyBlocks.trapDoorSpruce.registerRecipes();
		MyBlocks.trapDoorIron.registerRecipes();
	}
	
	private static void registerFenceRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(Blocks.fence, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_OAK,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceAcacia, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_ACACIA,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceBirch, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_BIRCH,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceDarkOak, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_DARK_OAK,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceJungle, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_JUNGLE,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceSpruce, 3, 0),
				"#|#",
				"#|#",
				'#', PLANK_SPRUCE,
				'|', Items.stick
				);
		
	}

	private static void registerFenceGateRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(Blocks.fence_gate),
				"|#|",
				"|#|",
				'#', PLANK_OAK,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceGateAcacia),
				"|#|",
				"|#|",
				'#', PLANK_ACACIA,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceGateBirch),
				"|#|",
				"|#|",
				'#', PLANK_BIRCH,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceGateDarkOak),
				"|#|",
				"|#|",
				'#', PLANK_DARK_OAK,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceGateJungle),
				"|#|",
				"|#|",
				'#', PLANK_JUNGLE,
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceGateSpruce),
				"|#|",
				"|#|",
				'#', PLANK_SPRUCE,
				'|', Items.stick
				);
	}
	
	private static void registerButtonRecipes() {
		GameRegistry.addShapelessRecipe(
				new ItemStack(Blocks.wooden_button),
				PLANK_OAK
				);
		
		MyBlocks.buttonAcacia.registerRecipes();
		MyBlocks.buttonBirch.registerRecipes();
		MyBlocks.buttonDarkOak.registerRecipes();
		MyBlocks.buttonJungle.registerRecipes();
		MyBlocks.buttonSpruce.registerRecipes();
	}
	
	private static void registerPressurePlateRecipes() {
		MyBlocks.pressurePlateAcacia.registerRecipes();
		MyBlocks.pressurePlateBirch.registerRecipes();
		MyBlocks.pressurePlateDarkOak.registerRecipes();
		MyBlocks.pressurePlateJungle.registerRecipes();
		MyBlocks.pressurePlateSpruce.registerRecipes();
	}

	private static void registerOceanMonumentRecipes() {
		MyBlocks.prismarineBlock.registerRecipes();
		MyBlocks.prismarineBricks.registerRecipes();
		MyBlocks.prismarineDark.registerRecipes();
		MyBlocks.seaLantern.registerRecipes();
	}
	
	private static void registerBoatRecipes() {
		MyItems.boatAcacia.registerRecipes();
		MyItems.boatBirch.registerRecipes();
		MyItems.boatDarkOak.registerRecipes();
		MyItems.boatJungle.registerRecipes();
		MyItems.boatSpruce.registerRecipes();
	}
	
	private static void registerStarisRecipes() {
		MyBlocks.stairsAndesite.registerRecipe();
		MyBlocks.stairsDarkPrismarine.registerRecipe();
		MyBlocks.stairsDiorite.registerRecipe();
		MyBlocks.stairsEndStoneBricks.registerRecipe();
		MyBlocks.stairsGranite.registerRecipe();
		MyBlocks.stairsMossyCobbleStone.registerRecipe();
		MyBlocks.stairsMossyStoneBricks.registerRecipe();
		MyBlocks.stairsPolishedAndesite.registerRecipe();
		MyBlocks.stairsPolishedDiorite.registerRecipe();
		MyBlocks.stairsPolishedGranite.registerRecipe();
		MyBlocks.stairsPrismarine.registerRecipe();
		MyBlocks.stairsPrismarineBricks.registerRecipe();
		MyBlocks.stairsPurpur.registerRecipe();
		MyBlocks.stairsRedNetherBricks.registerRecipe();
		MyBlocks.stairsRedSandstone.registerRecipe();
		MyBlocks.stairsStone.registerRecipe();
	}
	
	private static void registerSlabRecipes() {
		if(ModConfigCore.addRecipe_stoneSlab) MyBlocks.slabStone.registerRecipe();
		MyBlocks.slabGranite.registerRecipe();
		MyBlocks.slabDiorite.registerRecipe();
		MyBlocks.slabAndesite.registerRecipe();
		MyBlocks.slabPolishedGranite.registerRecipe();
		MyBlocks.slabPolishedDiorite.registerRecipe();
		MyBlocks.slabPolishedAndesite.registerRecipe();
		MyBlocks.slabRedNetherBricks.registerRecipe();
		MyBlocks.slabEndStoneBricks.registerRecipe();
		MyBlocks.slabMossyStoneBricks.registerRecipe();
		MyBlocks.slabMossyCobbleStone.registerRecipe();
		MyBlocks.slabDarkPrismarine.registerRecipe();
		MyBlocks.slabPrismarine.registerRecipe();
		MyBlocks.slabPrismarineBricks.registerRecipe();
		MyBlocks.slabPurpur.registerRecipe();
		MyBlocks.slabRedSandstone.registerRecipe();
	}
	
	private static void registerWallRecipes() {
		MyBlocks.wallBricks.registerRecipes();
		MyBlocks.wallStoneBricks.registerRecipes();
		MyBlocks.wallMossyStoneBricks.registerRecipes();
		MyBlocks.wallSandstone.registerRecipes();
		MyBlocks.wallRedSandstone.registerRecipes();
		MyBlocks.wallNetherBricks.registerRecipes();
		MyBlocks.wallRedNetherBricks.registerRecipes();
		MyBlocks.wallEndStoneBricks.registerRecipes();
		MyBlocks.wallAndesite.registerRecipes();
		MyBlocks.wallDiorite.registerRecipes();
		MyBlocks.wallGranite.registerRecipes();
		MyBlocks.wallPrismarine.registerRecipes();
		MyBlocks.wallPrismarineBrick.registerRecipes();
		MyBlocks.wallDarkPrismarine.registerRecipes();
	}
}
