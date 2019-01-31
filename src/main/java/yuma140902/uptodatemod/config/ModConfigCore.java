package yuma140902.uptodatemod.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.util.UpdateChecker;

public class ModConfigCore {
	public static final String
		CATEGORY_GENERAL = "General",
		CATEGORY_WORLDGEN = CATEGORY_GENERAL + ".WorldGen",
		CATEGORY_RECIPE = CATEGORY_GENERAL + ".Recipe",
		CATEGORY_EXPERIMENTAL = CATEGORY_GENERAL + ".Experimental",
		CATEGORY_ENTITY = CATEGORY_GENERAL + ".Entity";
	
	public static final String
		CONFIG_PROP_LANGKEY = "config.uptodate.prop.",
		CONFIG_CATEGORY_LANGKEY = "config.uptodate.category.";
	
	public static Configuration cfg;
	
	public static boolean worldGen_genStones;
	public static int[] worldGen_genStones_blackList;
	public static boolean worldGen_genFossiles;
	public static int[] worldGen_genFossiles_blackList;
	public static boolean worldGen_genCoarseDirt;
	public static int[] worldGen_genCoarseDirt_blackList;
	public static boolean recipeRemove_oldFenceRecipe;
	public static boolean addRecipe_stoneSlab;
	public static boolean enable_observer;
	public static boolean debug_mode;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
		// General
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "Settings of UpToDateMod");
		cfg.setCategoryLanguageKey(CATEGORY_GENERAL, CONFIG_CATEGORY_LANGKEY + "general");
		cfg.setCategoryRequiresMcRestart(CATEGORY_GENERAL, true);
		
		// WorldGen
		cfg.addCustomCategoryComment(CATEGORY_WORLDGEN, "Settings about world generation");
		cfg.setCategoryLanguageKey(CATEGORY_WORLDGEN, CONFIG_CATEGORY_LANGKEY + "worldgen");
		
		// Recipe
		cfg.addCustomCategoryComment(CATEGORY_RECIPE, "Settings about recipes");
		cfg.setCategoryLanguageKey(CATEGORY_RECIPE, CONFIG_CATEGORY_LANGKEY + "recipe");
		cfg.setCategoryRequiresMcRestart(CATEGORY_RECIPE, true);
		
		// Entity
		cfg.addCustomCategoryComment(CATEGORY_ENTITY, "Settings about entities and mobs");
		cfg.setCategoryLanguageKey(CATEGORY_ENTITY, CONFIG_CATEGORY_LANGKEY + "entity");
		
		// Experimental
		cfg.addCustomCategoryComment(CATEGORY_EXPERIMENTAL, "Settings about experimental features. They may have a serious bug.");
		cfg.setCategoryLanguageKey(CATEGORY_EXPERIMENTAL, CONFIG_CATEGORY_LANGKEY + "experimental");
		cfg.setCategoryRequiresMcRestart(CATEGORY_EXPERIMENTAL, true);
	}
	
	public static void syncConfig() {
		// General
		UpdateChecker.INSTANCE.config_doCheckUpdate = cfg.getBoolean("doUpdateChecking", CATEGORY_GENERAL, 
				UpdateChecker.INSTANCE.config_doCheckUpdate, 
				"If true, the mod will check for updates automatically | アップデートを自動で確認するかどうか",
				CONFIG_PROP_LANGKEY + "do_check_update");
		UpdateChecker.INSTANCE.config_updateChannel = cfg.getString("updateChannel", CATEGORY_GENERAL, 
				UpdateChecker.INSTANCE.config_updateChannel, 
				"Channel of update checking | アップデートのチャンネル", new String[] {UpdateChecker.RECOMMENDED_STR, UpdateChecker.LATEST_STR},
				CONFIG_PROP_LANGKEY + "update_channel"
				);
		debug_mode = cfg.getBoolean("enableDebugMode", CATEGORY_GENERAL, false, "", CONFIG_PROP_LANGKEY + "debug_mode");
		
		// WorldGen
		worldGen_genStones = cfg.getBoolean("genStones", CATEGORY_WORLDGEN, true, 
				"Generate Granite, Diorite, Andesite in Overworld or not | 花崗岩、閃緑岩、安山岩をワールドに生成するか否か",
				CONFIG_PROP_LANGKEY + "generate_stones");
		worldGen_genStones_blackList = stringListToIntList(cfg.getStringList("genStonesDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"1", "-1"}, 
				"Granite, Diorite, and Andesite generation dimension black list", (String[])null,
				CONFIG_PROP_LANGKEY + "generate_stones_blacklist"));
		worldGen_genFossiles = cfg.getBoolean("genFossiles", CATEGORY_WORLDGEN, true, 
				"Generate fossiles in Overworld or not | 化石を生成するか否か",
				CONFIG_PROP_LANGKEY + "generate_fossiles");
		worldGen_genFossiles_blackList = stringListToIntList(cfg.getStringList("genFossilesDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"1", "-1"}, 
				"Fossile generation dimension black list", (String[])null,
				CONFIG_PROP_LANGKEY + "generate_fossiles_blacklist"));
		worldGen_genCoarseDirt = cfg.getBoolean("genCoarseDirt", CATEGORY_WORLDGEN, true, 
				"Generate coarse dirt in Overworld or not | 粗い土を生成するか否か",
				CONFIG_PROP_LANGKEY + "generate_coarse_dirt");
		worldGen_genCoarseDirt_blackList = stringListToIntList(cfg.getStringList("genCoarseDirtDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"1", "-1"}, 
				"Coarse Dirt generation dimension black list", (String[])null,
				CONFIG_PROP_LANGKEY + "generate_coarse_dirt_blacklist"));
		
		// Recipe
		recipeRemove_oldFenceRecipe = cfg.getBoolean("removeOldFenceRecipe", CATEGORY_RECIPE, false, 
				"Delete the recipe from 6 sticks to 2 fences | 棒6本からフェンス2個を作るレシピを削除するかどうか(木材4つと棒2本からフェンスを作るレシピは、この設定に関わらず常に追加されます)",
				CONFIG_PROP_LANGKEY + "remove_old_fence_recipe");
		addRecipe_stoneSlab = cfg.getBoolean("addStoneSlabRecipe", CATEGORY_RECIPE, false, 
				"add Stone Slab recipe | 石のハーフブロックのレシピを追加するかどうか",
				CONFIG_PROP_LANGKEY + "add_stoneslab_recipe");
		
		// Entity
		EntityModBoatBase.boatCrashWhenCollide = cfg.getBoolean("boatCrashWhenCollide", CATEGORY_ENTITY, false, 
				"Boat added by this mod will crash when collision | このMODが追加するボートが、衝突時に壊れるかどうか(バニラのボートは衝突時に壊れる)",
				CONFIG_PROP_LANGKEY + "boat_crash_when_collide");
		
		// Experimental
		enable_observer = cfg.getBoolean("enableObserver", CATEGORY_EXPERIMENTAL, false, 
				"Enable observer(note: Observer has bugs) | オブザーバーを有効にするか否か【オブザーバーは未実装機能・バグ多数につき無効にしておくことを推奨】",
				CONFIG_PROP_LANGKEY + "observer");
		
		cfg.save();
	}
	
	private static int[] stringListToIntList(String[] strList) {
		int[] list = new int[strList.length];
		for(int srcIdx = 0, dstIdx = 0; srcIdx < strList.length; ++srcIdx) {
			try{
				list[dstIdx] = Integer.parseInt(strList[srcIdx]);
				++dstIdx;
			}
			catch (NumberFormatException e) {
				System.out.println("Config error: The value '" + strList[srcIdx] + "' cannot be parsed into integer.");
				continue;
			}
		}
		return list;
	}
}
