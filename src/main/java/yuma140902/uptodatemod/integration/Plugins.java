package yuma140902.uptodatemod.integration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.Recipes;

public final class Plugins {
	private Plugins() {}
	
	private static List<IPlugin> plugins = new ArrayList<>();
	
	private static final Logger logger = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-Plugins");
	
	static {
		registerPlugin(PluginEtFuturum.INSTANCE);
		registerPlugin(PluginProjectE.INSTANCE);
		registerPlugin(PluginThaumcraft.INSTANCE);
	}
	
	private static void registerPlugin(IPlugin plugin) {
		plugins.add(plugin);
	}
	
	public static Iterator<IPlugin> iterator(){
		return plugins.iterator();
	}
	
	
	public static void logPluginStats() {
		for(IPlugin plugin : plugins) {
			logger.info(plugin.getModName() + " : " + (plugin.isIntegrationEnabled() ? "Enabled" : "Disabled"));
		}
	}
	
	public static void tweakMods() {
		removeOtherModsRecipes();
		
		for (IPlugin plugin : plugins) {
			if(plugin.isIntegrationEnabled() && plugin instanceof ITweakingPlugin) {
				((ITweakingPlugin)plugin).tweakMod();
			}
		}
	}
	
	public static void tweakModsPost() {
		for (IPlugin plugin : plugins) {
			if(plugin.isIntegrationEnabled() && plugin instanceof ITweakingPostPlugin) {
				((ITweakingPostPlugin)plugin).tweakModPost();
			}
		}
	}

	static void initConfig(Configuration cfg) {
		plugins.stream()
						.filter(IPlugin::isIntegrationEnabled)
						.filter(p -> p instanceof IConfiguratingPlugin)
						.map(p -> (IConfiguratingPlugin)p)
						.forEach(p -> p.initConfig(cfg));
	}

	static void syncConfig(Configuration cfg) {
		plugins.stream()
						.filter(IPlugin::isIntegrationEnabled)
						.filter(p -> p instanceof IConfiguratingPlugin)
						.map(p -> (IConfiguratingPlugin)p)
						.forEach(p -> p.syncConfig(cfg));
	}

	static void wrapConfig(Configuration cfg){
		plugins.stream()
						.filter(IPlugin::isIntegrationEnabled)
						.filter(p -> p instanceof IConfiguratingPlugin)
						.map(p -> (IConfiguratingPlugin)p)
						.forEach(p -> p.wrapConfig(cfg));
	}
	
	private static void removeOtherModsRecipes() {
		List<String> list = new ArrayList<>();
		if(PluginEtFuturum.INSTANCE.isIntegrationEnabled()) PluginEtFuturum.INSTANCE.getNamesToRemoveFromRecipe(list);
		Recipes.removeRecipesByOutputName(list);
	}
	
}
