package yuma140902.uptodatemod.config;

import java.util.Iterator;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.model.IConfigEntry;
import yuma140902.uptodatemod.config.model.category.CategoryEntity;
import yuma140902.uptodatemod.config.model.category.CategoryExperimental;
import yuma140902.uptodatemod.config.model.category.CategoryFeatures;
import yuma140902.uptodatemod.config.model.category.CategoryFeatures.ConfigPropFeature;
import yuma140902.uptodatemod.config.model.category.CategoryGeneral;
import yuma140902.uptodatemod.config.model.category.CategoryRecipe;
import yuma140902.uptodatemod.config.model.category.CategoryWorldgen;
import yuma140902.uptodatemod.integration.IntegrationConfigs;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;

public class NewCore {
	
	@Nonnull public final CategoryGeneral general;
	@Nonnull public final CategoryWorldgen worldgen;
	@Nonnull public final CategoryRecipe recipe;
	@Nonnull public final CategoryExperimental experimental;
	@Nonnull public final CategoryEntity entity;
	@Nonnull public final CategoryFeatures features;
	
	private final Logger logger = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-Config");
	
	private ForgeConfigBridge cfg;
	
	public NewCore() {
		general = new CategoryGeneral();
		worldgen = 	(CategoryWorldgen) 	general.addSubCategory(CategoryWorldgen::new);
		recipe = 		(CategoryRecipe) 		general.addSubCategory(CategoryRecipe::new);
		experimental = (CategoryExperimental) general.addSubCategory(CategoryExperimental::new);
		entity = 		(CategoryEntity) 		general.addSubCategory(CategoryEntity::new);
		features = 	(CategoryFeatures) 	general.addSubCategory(CategoryFeatures::new);
	}
	
	public void loadConfig(FMLPreInitializationEvent event) {
		Configuration configFile = new Configuration(event.getSuggestedConfigurationFile(), true);
		this.cfg = new ForgeConfigBridge(general, configFile);
		initConfig();
		syncConfig();
		wrapConfig();
	}
	
	private void initConfig() {
		cfg.initCategories();
		
		IntegrationConfigs.initConfig(cfg);
	}
	
	public void syncConfig() {
		ModUpToDateMod.LOGGER.info("Loading config");
		
		cfg.getBoolean(general.doUpdateChecking);
		cfg.getStringSelection(general.updateChannel);
		cfg.getBoolean(general.debugMode);
		
		cfg.getBoolean(worldgen.genStones);
		cfg.getIntList(worldgen.stoneDimBlackList);
		cfg.getBoolean(worldgen.genFossiles);
		cfg.getIntList(worldgen.fossilesDimBlackList);
		cfg.getBoolean(worldgen.genCoarseDirt);
		cfg.getIntList(worldgen.coarseDirtDimBlackList);
		
		cfg.getBoolean(recipe.oldFence);
		cfg.getBoolean(recipe.oldSmoothStoneSlab);
		
		cfg.getBoolean(entity.boatCrashWhenCollide);
		
		cfg.getBoolean(experimental.enableObserver);
		
		syncDisableableFeaturesConfig();
		
		
		cfg.setCategoryPropertyOrder(general);
		cfg.setCategoryPropertyOrder(worldgen);
		cfg.setCategoryPropertyOrder(recipe);
		cfg.setCategoryPropertyOrder(experimental);
		cfg.setCategoryPropertyOrder(entity);
		cfg.setCategoryPropertyOrder(features);
		
		
		IntegrationConfigs.syncConfig(cfg);
		
		cfg.save();
	}
	
	private void syncDisableableFeaturesConfig() {
		logger.info("== Features Status ==");
		
		Iterator<IConfigEntry> iterator = features.subEntries();
		while (iterator.hasNext()) {
			ConfigPropFeature prop = (ConfigPropFeature) iterator.next();
			
			boolean enabled = cfg.getBoolean(prop);
			if(!enabled) {
				DisabledFeaturesRegistry.INSTANCE.setDisabled(prop.feature());
			}
			
			logger.info(prop.feature() + " : " + (enabled ? "Enabled" : "Disabled"));
		}
		
		cfg.setCategoryPropertyOrder(features);
		
	}
	
	private void wrapConfig() {
		IntegrationConfigs.wrapConfig(cfg);
	}
}
