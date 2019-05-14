package yuma140902.uptodatemod.integration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuma140902.uptodatemod.ModUpToDateMod;

public final class Plugins {
	private Plugins() {}
	
	private static List<IPlugin> plugins = new ArrayList<>();
	
	private static final Logger logger = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-Plugins");
	
	static {
		registerPlugin(PluginEtFuturum.INSTANCE);
		registerPlugin(PluginProjectE.INSTANCE);
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
	
	
	public static List<String> getNamesToRemoveFromRecipe() {
		List<String> list = new ArrayList<>();
		if(PluginEtFuturum.INSTANCE.isIntegrationEnabled()) PluginEtFuturum.INSTANCE.getNamesToRemoveFromRecipe(list);
		return list;
	}
}
