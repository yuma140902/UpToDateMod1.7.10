package yuma140902.uptodatemod.config;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.model.CategoryBuilder;
import yuma140902.uptodatemod.config.model.MultiLingualString;
import yuma140902.uptodatemod.integration.IntegrationConfigs;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.UpdateChecker;

public class ModConfigCore {
	public static final String
		CATEGORY_GENERAL = "General",
		CATEGORY_WORLDGEN = CATEGORY_GENERAL + ".WorldGen",
		CATEGORY_RECIPE = CATEGORY_GENERAL + ".Recipe",
		CATEGORY_ENTITY = CATEGORY_GENERAL + ".Entity",
		CATEGORY_DISABLE_FEATURES = CATEGORY_GENERAL + ".DisableFeatures",
		CATEGORY_ALTERNATIVE = CATEGORY_GENERAL + ".Alternative",
		CATEGORY_EXPERIMENTAL = CATEGORY_GENERAL + ".Experimental",
		CATEGORY_DEPRECATED = "Deprecated"; // GeneralのサブカテゴリではないのでGUIには表示されない
	
	public static final String
		CONFIG_PROP_LANGKEY = "config.uptodate.prop.",
		CONFIG_CATEGORY_LANGKEY = "config.uptodate.category.";
	
	public static Configuration cfg;
	
	private static final Logger logger = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-Config");
	
	public static class General {
		private static boolean doCheckUpdate = true;
		private static String updateChannel = UpdateChecker.RECOMMENDED_STR;
		private static boolean debugMode = false;
		
		public static boolean doCheckUpdate() {return doCheckUpdate;}
		public static String updateChannel() {return updateChannel;}
		public static boolean debugMode() {return debugMode;}
	}
	
	public static class WorldGen {
		private static boolean genStones = true;
		private static int[] stonesBlackList = new int[] {1, -1};
		private static boolean genFossiles = true;
		private static int[] fossilesBlackList = new int[] {1, -1};
		private static boolean genCoarseDirt = true;
		private static int[] coarseDirtBlackList = new int[] {1, -1};
		private static boolean genMagmaBlock = true;
		private static int[] magmaBlockBlackList = new int[] {0, 1};
		
		public static boolean genStones() {return genStones;}
		public static int[] stonesBlackList() {return stonesBlackList;}
		public static boolean genFossiles() {return genFossiles;}
		public static int[] fossilesBlackList() {return fossilesBlackList;}
		public static boolean genCoarseDirt() {return genCoarseDirt;}
		public static int[] coarseDirtBlackList() {return coarseDirtBlackList;}
		public static boolean genMagmaBlock() {return genMagmaBlock;}
		public static int[] magmaBlockBlackList() {return magmaBlockBlackList;}
	}
	
	public static class Recipe {
		private static boolean removeOldFenceRecipe = false;
		private static boolean useOldSmoothStoneSlabRecipe = false;
		
		public static boolean removeOldFenceRecipe() {return removeOldFenceRecipe;}
		public static boolean useOldSmoothStoneSlabRecipe() {return useOldSmoothStoneSlabRecipe;}
	}
	
	public static class Entity {
		private static boolean boatCrashWhenCollide = false;
		
		public static boolean boatCrashWhenCollide() {return boatCrashWhenCollide;}
	}
	
	public static class Experimental {
		private static boolean enableObserver = false;
		
		public static boolean enableObserver() {return enableObserver;}
	}
	
	public static class Deprecated {
		private static int idBoatAcacia = 0;
		private static int idBoatBirch = 1;
		private static int idBoatDarkOak = 2;
		private static int idBoatJungle = 3;
		private static int idBoatSpruce = 4;
		private static int idArmorStand = 5;
		
		public static int idBoatAcacia() {return idBoatAcacia;}
		public static int idBoatBirch() {return idBoatBirch;}
		public static int idBoatDarkOak() {return idBoatDarkOak;}
		public static int idBoatJungle() {return idBoatJungle;}
		public static int idBoatSpruce() {return idBoatSpruce;}
		public static int idArmorStand() {return idArmorStand;}
	}
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
		wrapConfig();
	}
	
	private static void initConfig() {
		// General
		CategoryBuilder generalCategory = new CategoryBuilder(CATEGORY_GENERAL)
			.comment(MultiLingualString.single("Settings of UpToDateMod"))
			.langKey(getCategoryLangkey("general"))
			.requireMcRestart();
		generalCategory.registerToForge(cfg);
		
		// WorldGen
		CategoryBuilder worldGenCategory = new CategoryBuilder(CATEGORY_WORLDGEN)
			.comment(MultiLingualString.single("Settings about world generation"))
			.langKey(getCategoryLangkey("worldgen"));
		worldGenCategory.registerToForge(cfg);
		
		// Recipe
		CategoryBuilder recipeCategory = new CategoryBuilder(CATEGORY_RECIPE)
			.comment(MultiLingualString.single("Settings about recipes"))
			.langKey(getCategoryLangkey("recipe"))
			.requireMcRestart();
		recipeCategory.registerToForge(cfg);
		
		// Entity
		CategoryBuilder entityCategory = new CategoryBuilder(CATEGORY_ENTITY)
			.comment(MultiLingualString.single("Settings about entities and mobs"))
			.langKey(getCategoryLangkey("entity"))
			.requireMcRestart();
		entityCategory.registerToForge(cfg);
		
		// DisableFeatures
		CategoryBuilder disableFeaturesCategory = new CategoryBuilder(CATEGORY_DISABLE_FEATURES)
			.requireMcRestart();
		disableFeaturesCategory.registerToForge(cfg);
		
		// Alternative
		CategoryBuilder alternativeCategory = new CategoryBuilder(CATEGORY_ALTERNATIVE)
			.comment(MultiLingualString.single("Alternative ways to get items"));
		alternativeCategory.registerToForge(cfg);
		
		// Experimental
		CategoryBuilder experimentalCategory = new CategoryBuilder(CATEGORY_EXPERIMENTAL)
			.comment(MultiLingualString.single("Settings about experimental features. They may have a serious bug."))
			.langKey(getCategoryLangkey("experimental"))
			.requireMcRestart();
		experimentalCategory.registerToForge(cfg);
		
		// Deprecated
		CategoryBuilder deprecatedCategory = new CategoryBuilder(CATEGORY_DEPRECATED)
			.comment(MultiLingualString.single("You do not have to change the configurations in Deprecated section."))
			.requireMcRestart();
		deprecatedCategory.registerToForge(cfg);
		
		IntegrationConfigs.initConfig(cfg);
	}
	
	public static void syncConfig() {
		ModUpToDateMod.LOGGER.info("Loading config");
		// TODO ここの書き換え及びテスト
		
		// General
		General.doCheckUpdate = cfg.getBoolean("doUpdateChecking", CATEGORY_GENERAL, 
				General.doCheckUpdate, 
				"If true, the mod will check for updates automatically | アップデートを自動で確認するかどうか",
				CONFIG_PROP_LANGKEY + "do_check_update");
		General.updateChannel = cfg.getString("updateChannel", CATEGORY_GENERAL, 
				General.updateChannel, 
				"Channel of update checking | アップデートのチャンネル", new String[] {UpdateChecker.RECOMMENDED_STR, UpdateChecker.LATEST_STR},
				CONFIG_PROP_LANGKEY + "update_channel"
				);
		General.debugMode = cfg.getBoolean("enableDebugMode", CATEGORY_GENERAL, General.debugMode, "", CONFIG_PROP_LANGKEY + "debug_mode");
		
		// WorldGen
		WorldGen.genStones = cfg.getBoolean("genStones", CATEGORY_WORLDGEN, WorldGen.genStones, 
				"Generate Granite, Diorite, Andesite in Overworld or not | 花崗岩、閃緑岩、安山岩をワールドに生成するか否か",
				CONFIG_PROP_LANGKEY + "generate_stones");
		WorldGen.stonesBlackList = stringListToIntList(cfg.getStringList("genStonesDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"1", "-1"}, 
				"Granite, Diorite, and Andesite generation dimension black list", (String[])null,
				CONFIG_PROP_LANGKEY + "generate_stones_blacklist"));
		WorldGen.genFossiles = cfg.getBoolean("genFossiles", CATEGORY_WORLDGEN, WorldGen.genFossiles, 
				"Generate fossiles in Overworld or not | 化石を生成するか否か",
				CONFIG_PROP_LANGKEY + "generate_fossiles");
		WorldGen.fossilesBlackList = stringListToIntList(cfg.getStringList("genFossilesDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"1", "-1"}, 
				"Fossile generation dimension black list", (String[])null,
				CONFIG_PROP_LANGKEY + "generate_fossiles_blacklist"));
		WorldGen.genCoarseDirt = cfg.getBoolean("genCoarseDirt", CATEGORY_WORLDGEN, WorldGen.genCoarseDirt, 
				"Generate coarse dirt in Overworld or not | 粗い土を生成するか否か",
				CONFIG_PROP_LANGKEY + "generate_coarse_dirt");
		WorldGen.coarseDirtBlackList = stringListToIntList(cfg.getStringList("genCoarseDirtDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"1", "-1"}, 
				"Coarse Dirt generation dimension black list", (String[])null,
				CONFIG_PROP_LANGKEY + "generate_coarse_dirt_blacklist"));
		WorldGen.genMagmaBlock = cfg.getBoolean("genMagmaBlock", CATEGORY_WORLDGEN, WorldGen.genMagmaBlock, "");
		WorldGen.magmaBlockBlackList = stringListToIntList(cfg.getStringList("genMagmaBlockDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"0", "1"}, ""));
		
		// Recipe
		Recipe.removeOldFenceRecipe = cfg.getBoolean("removeOldFenceRecipe", CATEGORY_RECIPE, Recipe.removeOldFenceRecipe, 
				"Delete the recipe from 6 sticks to 2 fences | 棒6本からフェンス2個を作るレシピを削除するかどうか(木材4つと棒2本からフェンスを作るレシピは、この設定に関わらず常に追加されます)",
				CONFIG_PROP_LANGKEY + "remove_old_fence_recipe");
		cfg.getCategory(CATEGORY_RECIPE).remove("addStoneSlabRecipe");
		Recipe.useOldSmoothStoneSlabRecipe = cfg.getBoolean("useOldSmoothStoneSlabRecipe", CATEGORY_RECIPE, Recipe.useOldSmoothStoneSlabRecipe, "If set to true, adds old smooth stone slab recipe.");
		
		// Entity
		Entity.boatCrashWhenCollide = cfg.getBoolean("boatCrashWhenCollide", CATEGORY_ENTITY, Entity.boatCrashWhenCollide, 
				"Boat added by this mod will crash when collision | このMODが追加するボートが、衝突時に壊れるかどうか(バニラのボートは衝突時に壊れる)",
				CONFIG_PROP_LANGKEY + "boat_crash_when_collide");
		
		// DisableFeatures
		syncDisableableFeaturesConfig(cfg);
		
		// Alternative
		
		
		// Experimental
		Experimental.enableObserver = cfg.getBoolean("enableObserver", CATEGORY_EXPERIMENTAL, Experimental.enableObserver, 
				"Enable observer(note: Observer has bugs) | オブザーバーを有効にするか否か【オブザーバーは未実装機能・バグ多数につき無効にしておくことを推奨】",
				CONFIG_PROP_LANGKEY + "observer");
		
		// Deprecated
		Deprecated.idBoatAcacia = 		cfg.getInt("idBoatAcacia", 		CATEGORY_DEPRECATED, Deprecated.idBoatAcacia, 	0, Integer.MAX_VALUE, "Entity ID for Acacia Boat");
		Deprecated.idBoatBirch = 			cfg.getInt("idBoatBirch", 		CATEGORY_DEPRECATED, Deprecated.idBoatBirch, 		0, Integer.MAX_VALUE, "Entity ID for Birch Boat");
		Deprecated.idBoatDarkOak = 		cfg.getInt("idBoatDarkOak", 	CATEGORY_DEPRECATED, Deprecated.idBoatDarkOak, 	0, Integer.MAX_VALUE, "Entity ID for Dark Oak Boat");
		Deprecated.idBoatJungle = 		cfg.getInt("idBoatJungle", 		CATEGORY_DEPRECATED, Deprecated.idBoatJungle, 	0, Integer.MAX_VALUE, "Entity ID for Jungle Boat");
		Deprecated.idBoatSpruce = 		cfg.getInt("idBoatSpruce", 		CATEGORY_DEPRECATED, Deprecated.idBoatSpruce, 	0, Integer.MAX_VALUE, "Entity ID for Spruce Boat");
		Deprecated.idArmorStand = 		cfg.getInt("idArmorStand", 		CATEGORY_DEPRECATED, Deprecated.idArmorStand, 	0, Integer.MAX_VALUE, "Entity ID for Armorstand");
		
		IntegrationConfigs.syncConfig(cfg);
		
		cfg.save();
	}
	
	private static void syncDisableableFeaturesConfig(Configuration cfg) {
		logger.info("== Features Status ==");
		
		List<String> orderedPropNameList = new ArrayList<String>();
		
		for(EnumDisableableFeatures feature : EnumDisableableFeatures.values()) {
			String propName = "enable " + feature.toString();
			orderedPropNameList.add(propName);
			
			String comment = feature.getComment();
			Property prop = (comment == null) 
					? cfg.get(CATEGORY_DISABLE_FEATURES, propName, true) 
					: cfg.get(CATEGORY_DISABLE_FEATURES, propName, true, comment);
			if(!prop.getBoolean()) {
				DisabledFeaturesRegistry.INSTANCE.setDisabled(feature);
			}
			
			logger.info(feature + " : " + (prop.getBoolean() ? "Enabled" : "Disabled"));
		}
		
		cfg.setCategoryPropertyOrder(CATEGORY_DISABLE_FEATURES, orderedPropNameList); //カテゴリ内での並び順を設定
		
	}
	
	private static void wrapConfig() {
		IntegrationConfigs.wrapConfig(cfg);
	}
	
	private static int[] stringListToIntList(String[] strList) {
		int[] list = new int[strList.length];
		for(int srcIdx = 0, dstIdx = 0; srcIdx < strList.length; ++srcIdx) {
			try{
				list[dstIdx] = Integer.parseInt(strList[srcIdx]);
				++dstIdx;
			}
			catch (NumberFormatException e) {
				logger.warn("Config error: The value '" + strList[srcIdx] + "' cannot be parsed into integer.");
				continue;
			}
		}
		return list;
	}
	
	
	public static String getSubCategory(String subCategory) {
		return CATEGORY_GENERAL + "." + subCategory;
	}
	
	public static String getCategoryLangkey(String key) {
		return CONFIG_CATEGORY_LANGKEY + key;
	}
	
	public static String getPropertyLangkey(String key) {
		return CONFIG_PROP_LANGKEY + key;
	}
}
