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
import yuma140902.uptodatemod.blocks.BlockStone;
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
		if(ModUpToDateMod.INSTANCE.config_recipeRemove_oldFenceRecipe)
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
		registerBoatRecipes();
		registerStarisRecipes();
		registerSlabRecipes();
		
		MyBlocks.coarseDirt.registerRecipes();
		
		MyBlocks.redNetherBricks.registerRecipes();
		
		MyBlocks.netherWartBlock.registerRecipes();
		
		MyBlocks.endStoneBricks.registerRecipes();
		
		MyBlocks.boneBlock.registerRecipes();
		
		MyBlocks.magmaBlock.registerRecipes();
		
		MyBlocks.redSandStone.registerRecipes();
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.dirt), MyBlocks.coarseDirt);
		
		registerOceanMonumentRecipes();
	}
	
	private static void registerStoneRecipes() {
	//4つ並べて磨かれた〇〇
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, BlockStone.META_POLISHED_GRANITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, BlockStone.META_GRANITE));
			
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, BlockStone.META_POLISHED_DIORITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, BlockStone.META_DIORITE));
			
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, BlockStone.META_POLISHED_ANDESITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, BlockStone.META_ANDESITE));
			
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

	private static void registerDoorRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(Items.wooden_door, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_OAK
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorAcacia, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_ACACIA
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorBirch, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_BIRCH
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorDarkOak, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_DARK_OAK
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorJungle, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_JUNGLE
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorSpruce, 3, 0),
				"##",
				"##",
				"##",
				'#', PLANK_SPRUCE
				);
	}
	
	private static void registerTrapDoorRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorAcacia, 2, 0),
				"###",
				"###",
				'#', PLANK_ACACIA
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorBirch, 2, 0),
				"###",
				"###",
				'#', PLANK_BIRCH
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorDarkOak, 2, 0),
				"###",
				"###",
				'#', PLANK_DARK_OAK
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorJungle, 2, 0),
				"###",
				"###",
				'#', PLANK_JUNGLE
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorSpruce, 2, 0),
				"###",
				"###",
				'#', PLANK_SPRUCE
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorIron, 1, 0),
				"##",
				"##",
				'#', Items.iron_ingot
				);
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

	private static void registerOceanMonumentRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.prismarineBlock),
				"##",
				"##",
				'#', MyItems.prismarineShard
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.prismarineBricks),
				"###",
				"###",
				"###",
				'#', MyItems.prismarineShard
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.prismarineDark),
				"###",
				"#I#",
				"###",
				'#', MyItems.prismarineShard,
				'I', new ItemStack(Items.dye, 1, 0)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.seaLantern),
				"#C#",
				"CCC",
				"#C#",
				'#', MyItems.prismarineShard,
				'C', MyItems.prismarineCrystal
				);
	}
	
	private static void registerBoatRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyItems.boatAcacia),
				"# #",
				"###",
				'#', PLANK_ACACIA
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.boatBirch),
				"# #",
				"###",
				'#', PLANK_BIRCH
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.boatDarkOak),
				"# #",
				"###",
				'#', PLANK_DARK_OAK
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.boatJungle),
				"# #",
				"###",
				'#', PLANK_JUNGLE
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.boatSpruce),
				"# #",
				"###",
				'#', PLANK_SPRUCE
				);
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
		MyBlocks.slabDarkPrismarine.registerRecipe();
		MyBlocks.slabPrismarine.registerRecipe();
		MyBlocks.slabPrismarineBricks.registerRecipe();
		MyBlocks.slabPurpur.registerRecipe();
		MyBlocks.slabRedSandstone.registerRecipe();
	}
}
