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
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
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
	
	public static boolean worldGen_genStones;
	public static int[] worldGen_genStones_blackList;
	public static boolean worldGen_genFossiles;
	public static int[] worldGen_genFossiles_blackList;
	public static boolean worldGen_genCoarseDirt;
	public static int[] worldGen_genCoarseDirt_blackList;
	public static boolean worldGen_genMagmaBlock;
	public static int[] worldGen_genMagmaBlock_blackList;
	public static boolean recipeRemove_oldFenceRecipe;
	public static boolean useOldSmoothStoneSlabRecipe;
	public static int idBoatAcacia;
	public static int idBoatBirch;
	public static int idBoatDarkOak;
	public static int idBoatJungle;
	public static int idBoatSpruce;
	public static int idArmorStand;
	public static boolean enable_observer;
	public static boolean debug_mode;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
		wrapConfig();
	}
	
	private static void initConfig() {
		// General
		new CategoryBuilder(CATEGORY_GENERAL)
			.comment(MultiLingualString.single("Settings of UpToDateMod"))
			.langKey(getCategoryLangkey("general"))
			.requireMcRestart()
			.registerToForge(cfg);
		
		// WorldGen
		new CategoryBuilder(CATEGORY_WORLDGEN)
			.comment(MultiLingualString.single("Settings about world generation"))
			.langKey(getCategoryLangkey("worldgen"))
			.registerToForge(cfg);
		
		// Recipe
		new CategoryBuilder(CATEGORY_RECIPE)
			.comment(MultiLingualString.single("Settings about recipes"))
			.langKey(getCategoryLangkey("recipe"))
			.requireMcRestart()
			.registerToForge(cfg);
		
		// Entity
		new CategoryBuilder(CATEGORY_ENTITY)
			.comment(MultiLingualString.single("Settings about entities and mobs"))
			.langKey(getCategoryLangkey("entity"))
			.requireMcRestart()
			.registerToForge(cfg);
		
		// DisableFeatures
		new CategoryBuilder(CATEGORY_DISABLE_FEATURES)
			.requireMcRestart()
			.registerToForge(cfg);
		
		// Alternative
		new CategoryBuilder(CATEGORY_ALTERNATIVE)
			.comment(MultiLingualString.single("Alternative ways to get items"))
			.registerToForge(cfg);
		
		// Experimental
		new CategoryBuilder(CATEGORY_EXPERIMENTAL)
			.comment(MultiLingualString.single("Settings about experimental features. They may have a serious bug."))
			.langKey(getCategoryLangkey("experimental"))
			.requireMcRestart()
			.registerToForge(cfg);
		
		// Deprecated
		new CategoryBuilder(CATEGORY_DEPRECATED)
			.comment(MultiLingualString.single("You do not have to change the configurations in Deprecated section."))
			.requireMcRestart()
			.registerToForge(cfg);
		
		IntegrationConfigs.initConfig(cfg);
	}
	
	public static void syncConfig() {
		ModUpToDateMod.LOGGER.info("Loading config");
		// TODO ここの書き換え及びテスト
		
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
		worldGen_genMagmaBlock = cfg.getBoolean("genMagmaBlock", CATEGORY_WORLDGEN, true, "");
		worldGen_genMagmaBlock_blackList = stringListToIntList(cfg.getStringList("genMagmaBlockDimensionBlackList", CATEGORY_WORLDGEN, new String[] {"0", "1"}, ""));
		
		// Recipe
		recipeRemove_oldFenceRecipe = cfg.getBoolean("removeOldFenceRecipe", CATEGORY_RECIPE, false, 
				"Delete the recipe from 6 sticks to 2 fences | 棒6本からフェンス2個を作るレシピを削除するかどうか(木材4つと棒2本からフェンスを作るレシピは、この設定に関わらず常に追加されます)",
				CONFIG_PROP_LANGKEY + "remove_old_fence_recipe");
		cfg.getCategory(CATEGORY_RECIPE).remove("addStoneSlabRecipe");
		useOldSmoothStoneSlabRecipe = cfg.getBoolean("useOldSmoothStoneSlabRecipe", CATEGORY_RECIPE, false, "If set to true, adds old smooth stone slab recipe.");
		
		// Entity
		EntityModBoatBase.boatCrashWhenCollide = cfg.getBoolean("boatCrashWhenCollide", CATEGORY_ENTITY, false, 
				"Boat added by this mod will crash when collision | このMODが追加するボートが、衝突時に壊れるかどうか(バニラのボートは衝突時に壊れる)",
				CONFIG_PROP_LANGKEY + "boat_crash_when_collide");
		
		// DisableFeatures
		syncDisableableFeaturesConfig(cfg);
		
		// Alternative
		
		
		// Experimental
		enable_observer = cfg.getBoolean("enableObserver", CATEGORY_EXPERIMENTAL, false, 
				"Enable observer(note: Observer has bugs) | オブザーバーを有効にするか否か【オブザーバーは未実装機能・バグ多数につき無効にしておくことを推奨】",
				CONFIG_PROP_LANGKEY + "observer");
		
		// Deprecated
		idBoatAcacia = 		cfg.getInt("idBoatAcacia", 		CATEGORY_DEPRECATED, 0, 0, Integer.MAX_VALUE, "Entity ID for Acacia Boat");
		idBoatBirch = 		cfg.getInt("idBoatBirch", 		CATEGORY_DEPRECATED, 1, 0, Integer.MAX_VALUE, "Entity ID for Birch Boat");
		idBoatDarkOak = 	cfg.getInt("idBoatDarkOak", 	CATEGORY_DEPRECATED, 2, 0, Integer.MAX_VALUE, "Entity ID for Dark Oak Boat");
		idBoatJungle = 		cfg.getInt("idBoatJungle", 		CATEGORY_DEPRECATED, 3, 0, Integer.MAX_VALUE, "Entity ID for Jungle Boat");
		idBoatSpruce = 		cfg.getInt("idBoatSpruce", 		CATEGORY_DEPRECATED, 4, 0, Integer.MAX_VALUE, "Entity ID for Spruce Boat");
		idArmorStand = 		cfg.getInt("idArmorStand", 		CATEGORY_DEPRECATED, 5, 0, Integer.MAX_VALUE, "Entity ID for Armorstand");
		
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
