package yuma140902.uptodatemod.integration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Plugins {
	private Plugins() {}
	
	private static List<IPlugin> plugins = new ArrayList<>();
	
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
