package yuma140902.uptodatemod.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.model.CategoryBuilder;
import yuma140902.uptodatemod.config.model.LangKey;
import yuma140902.uptodatemod.config.model.PropertyBuilder;
import yuma140902.uptodatemod.integration.IntegrationConfigs;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.UpdateChecker;

public class ModConfigCore {
	public static final String
		CATEGORY_GENERAL = "General",
		CATEGORY_WORLDGEN = getSubCategory("WorldGen"),
		CATEGORY_RECIPE = getSubCategory("Recipe"),
		CATEGORY_ENTITY = getSubCategory("Entity"),
		CATEGORY_DISABLE_FEATURES = getSubCategory("DisableFeatures"),
		CATEGORY_ALTERNATIVE = getSubCategory("Alternative"),
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
	
	public static class Alternative {
		private static boolean altPrismarine = false;
		private static boolean altPurpur = false;
		
		public static boolean altPrismarine() {return altPrismarine;}
		public static boolean altPurpur() {return altPurpur;}
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
	
	private static CategoryBuilder generalCategory;
	private static CategoryBuilder worldGenCategory;
	private static CategoryBuilder recipeCategory;
	private static CategoryBuilder entityCategory;
	private static CategoryBuilder disableFeaturesCategory;
	private static CategoryBuilder alternativeCategory;
	private static CategoryBuilder deprecatedCategory;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), ModUpToDateMod.MOD_VERSION, true);
		initConfig();
		syncConfig();
		wrapConfig();
	}
	
	private static void initConfig() {
		// General
		generalCategory = new CategoryBuilder(CATEGORY_GENERAL)
			.langKey(getCategoryLangkey("general"))
			.requireMcRestart()
			.add(PropertyBuilder.bool("doUpdateChecking")
				.defaultBool(General.doCheckUpdate)
				.comment("If true, the mod will check for updates automatically", "アップデートを自動で確認するかどうか")
				.langKey(getPropertyLangkey("do_check_update"))
				)
			.add(PropertyBuilder.string("updateChannel")
				.defaultString(General.updateChannel)
				.validStrings(new String[] {UpdateChecker.RECOMMENDED_STR, UpdateChecker.LATEST_STR})
				.comment("Channel of updates checking", "アップデートのチャンネル")
				.langKey(getPropertyLangkey("update_channel"))
				)
			.add(PropertyBuilder.bool("enableDebugMode")
				.defaultBool(General.debugMode)
				.comment("Enable debug mode", "デバッグモード。通常はfalseにすることを推奨")
				.langKey(getPropertyLangkey("debug_mode"))
				);
		generalCategory.registerToForge(cfg);
		
		// WorldGen
		Pattern numberPattern = Pattern.compile("-?[0-9]+");
		worldGenCategory = new CategoryBuilder(CATEGORY_WORLDGEN)
			.langKey(getCategoryLangkey("worldgen"))
			.add(PropertyBuilder.bool("genStones")
				.defaultBool(WorldGen.genStones)
				.comment("Generate Granite, Diorite, Andesite in Overworld or not", "花崗岩、閃緑岩、安山岩をワールドに生成するか否か")
				.langKey(getPropertyLangkey("generate_stones"))
				)
			.add(PropertyBuilder.stringList("genStonesDimensionBlackList")
				.defaultStringList(toStringList(WorldGen.stonesBlackList))         // わざわざString[]に変換しているのは過去のconfigファイルとの互換性を保つため
				.langKey(getPropertyLangkey("generate_stones_blacklist"))
				.validationPattern(numberPattern)
				)
			.add(PropertyBuilder.bool("genFossiles")
				.defaultBool(WorldGen.genFossiles)
				.comment("Generate fossiles in Overworld or not", "化石を生成するか否か")
				.langKey(getPropertyLangkey("generate_fossiles"))
				)
			.add(PropertyBuilder.stringList("genFossilesDimensionBlackList")
				.defaultStringList(toStringList(WorldGen.fossilesBlackList))          // わざわざString[]に変換しているのは過去のconfigファイルとの互換性を保つため
				.langKey(getPropertyLangkey("generate_fossiles_blacklist"))
				.validationPattern(numberPattern)
				)
			.add(PropertyBuilder.bool("genCoarseDirt")
				.defaultBool(WorldGen.genCoarseDirt)
				.comment("Generate coarse dirt in Overworld or not", "粗い土を生成するか否か")
				.langKey(getPropertyLangkey("generate_coarse_dirt"))
				)
			.add(PropertyBuilder.stringList("genCoarseDirtDimensionBlackList")
				.defaultStringList(toStringList(WorldGen.coarseDirtBlackList))          // わざわざString[]に変換しているのは過去のconfigファイルとの互換性を保つため
				.langKey(getPropertyLangkey("generate_coarse_dirt_blacklist"))
				.validationPattern(numberPattern)
				)
			.add(PropertyBuilder.bool("genMagmaBlock")
				.defaultBool(WorldGen.genMagmaBlock)
				.comment("Generate magma block or not", "マグマブロックを生成するか否か")
				.langKey(getPropertyLangkey("generate_magma_block"))
				)
			.add(PropertyBuilder.integerList("genMagmaBlockDimensionBlackList")
				.defaultIntList(WorldGen.magmaBlockBlackList)
				.langKey(getPropertyLangkey("generate_magma_block_blacklist"))
				);
		worldGenCategory.registerToForge(cfg);
		
		// Recipe
		recipeCategory = new CategoryBuilder(CATEGORY_RECIPE)
			.langKey(getCategoryLangkey("recipe"))
			.requireMcRestart()
			.add(PropertyBuilder.bool("removeOldFenceRecipe")
				.defaultBool(Recipe.removeOldFenceRecipe)
				.comment("Delete the recipe of 2 fences from 6 sticks", "棒6本からフェンス2個を作るレシピを削除するかどうか(木材4つと棒2本からフェンスを作るレシピは、この設定に関わらず常に追加されます)")
				.langKey(getPropertyLangkey("remove_old_fence_recipe"))
				)
			.add(PropertyBuilder.bool("useOldSmoothStoneSlabRecipe")
				.defaultBool(Recipe.useOldSmoothStoneSlabRecipe)
				.comment("If set to true, old smooth stone slab recipe will be kept", "古い石ハーフブロックのレシピを使うかどうか")
				.langKey(getPropertyLangkey("use_old_smooth_stone_slab_recipe"))
				);
		recipeCategory.registerToForge(cfg);
		
		// Entity
		entityCategory = new CategoryBuilder(CATEGORY_ENTITY)
			.langKey(getCategoryLangkey("entity"))
			.requireMcRestart()
			.add(PropertyBuilder.bool("boatCrashWhenCollide")
				.defaultBool(Entity.boatCrashWhenCollide)
				.comment("Boat added by this mod will crash when collision", "このMODが追加するボートが、衝突時に壊れるかどうか(バニラのボートは衝突時に壊れる)")
				.langKey(getPropertyLangkey("boat_crash_when_collide"))
				);
		entityCategory.registerToForge(cfg);
		
		// DisableFeatures
		disableFeaturesCategory = new CategoryBuilder(CATEGORY_DISABLE_FEATURES)
				.langKey(getCategoryLangkey("disable_features"))
			.requireMcRestart();
		disableFeaturesCategory.registerToForge(cfg);
		
		// Alternative
		alternativeCategory = new CategoryBuilder(CATEGORY_ALTERNATIVE)
			.langKey(getCategoryLangkey("alternative"))
			.add(PropertyBuilder.bool("altPrismarine")
				.defaultBool(Alternative.altPrismarine)
				.comment("Make squid drop prismarine stuff", "イカがプリズマリン系のアイテムをドロップするようになる")
				.langKey(getPropertyLangkey("alt_prismarine"))
				.requireMcRestart())
			.add(PropertyBuilder.bool("altPurpur")
				.defaultBool(Alternative.altPurpur)
				.comment("Make enderman drop purpur stuff", "エンダーマンがプルプァ系のアイテムをドロップするようになる")
				.langKey(getPropertyLangkey("alt_purpur"))
				.requireMcRestart());
		alternativeCategory.registerToForge(cfg);
		
		// Deprecated
		deprecatedCategory = new CategoryBuilder(CATEGORY_DEPRECATED)
			.comment("You do not have to change the configurations in Deprecated section.")
			.requireMcRestart();
		deprecatedCategory.registerToForge(cfg);
		
		IntegrationConfigs.initConfig(cfg);
	}
	
	public static void syncConfig() {
		ModUpToDateMod.LOGGER.info("Loading config");
		
		// General
		generalCategory.registerPropertiesToForge(cfg);
		General.doCheckUpdate = generalCategory.get("doUpdateChecking", cfg).getBoolean();
		General.updateChannel = generalCategory.get("updateChannel", cfg).getString();
		General.debugMode = generalCategory.get("enableDebugMode", cfg).getBoolean();
		
		// WorldGen
		worldGenCategory.registerPropertiesToForge(cfg);
		WorldGen.genStones = worldGenCategory.get("genStones", cfg).getBoolean();
		WorldGen.stonesBlackList = toIntList(worldGenCategory.get("genStonesDimensionBlackList", cfg).getStringList());       // 過去のconfigファイルとの互換性
		WorldGen.genFossiles = worldGenCategory.get("genFossiles", cfg).getBoolean();
		WorldGen.fossilesBlackList = toIntList(worldGenCategory.get("genFossilesDimensionBlackList", cfg).getStringList());       // 過去のconfigファイルとの互換性
		WorldGen.genCoarseDirt = worldGenCategory.get("genCoarseDirt", cfg).getBoolean();
		WorldGen.coarseDirtBlackList = toIntList(worldGenCategory.get("genCoarseDirtDimensionBlackList", cfg).getStringList());       // 過去のconfigファイルとの互換性
		WorldGen.genMagmaBlock = worldGenCategory.get("genMagmaBlock", cfg).getBoolean();
		WorldGen.magmaBlockBlackList = worldGenCategory.get("genMagmaBlockDimensionBlackList", cfg).getIntList();
		
		// Recipe
		recipeCategory.registerPropertiesToForge(cfg);
		Recipe.removeOldFenceRecipe = recipeCategory.get("removeOldFenceRecipe", cfg).getBoolean();
		cfg.getCategory(CATEGORY_RECIPE).remove("addStoneSlabRecipe");      // 過去の廃止されたconfigの項目を削除
		Recipe.useOldSmoothStoneSlabRecipe = recipeCategory.get("useOldSmoothStoneSlabRecipe", cfg).getBoolean();
		
		// Entity
		entityCategory.registerPropertiesToForge(cfg);
		Entity.boatCrashWhenCollide = entityCategory.get("boatCrashWhenCollide", cfg).getBoolean();
		
		// DisableFeatures
		syncDisableableFeaturesConfig(cfg);
		
		// Alternative
		alternativeCategory.registerPropertiesToForge(cfg);
		Alternative.altPrismarine = alternativeCategory.get("altPrismarine", cfg).getBoolean();
		Alternative.altPurpur = alternativeCategory.get("altPurpur", cfg).getBoolean();
		cfg.removeCategory(cfg.getCategory(getSubCategory("Experimental")));  // Experimentalカテゴリーを削除
		
		// Deprecated
		deprecatedCategory.registerPropertiesToForge(cfg);
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
	
	private static int[] toIntList(String[] strList) {
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
	
	private static String[] toStringList(int[] intList) {
		String[] strList = new String[intList.length];
		for(int i=0; i<intList.length; ++i) {
			strList[i] = Integer.toString(intList[i]);
		}
		return strList;
	}
	
	
	public static String getSubCategory(String subCategory) {
		return CATEGORY_GENERAL + "." + subCategory;
	}
	
	public static LangKey getCategoryLangkey(String key) {
		return LangKey.of(CONFIG_CATEGORY_LANGKEY + key);
	}
	
	public static LangKey getPropertyLangkey(String key) {
		return LangKey.of(CONFIG_PROP_LANGKEY + key);
	}
}
