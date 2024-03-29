package yuma140902.yumalib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import yuma140902.yumalib.api.registry.UpdateCheckerRegistry;
import yuma140902.yumalib.api.update.IUpdateChecker;
import yuma140902.yumalib.config.YLConfigCore;
import yuma140902.yumalib.proxy.YLCommonProxy;

/**
 * ModとしてのYumaLibのメインクラス
 */
@Mod(modid = YLConstants.MOD_ID, name = YLConstants.MOD_NAME, version = YLConstants.MOD_VERSION, useMetadata = true, guiFactory = YLConstants.CONFIG_GUI_FACTORY)
public class ModYumaLib {
	
	public static final Logger LOGGER = LogManager.getLogger(YLConstants.MOD_NAME);
	
	@Mod.Metadata(YLConstants.MOD_ID)
	public static ModMetadata modMetadata;
	
	@Mod.Instance(YLConstants.MOD_ID)
	public static ModYumaLib INSTANCE;
	
	@SidedProxy(modId = YLConstants.MOD_ID, clientSide = YLConstants.PROXY_CLIENT, serverSide = YLConstants.PROXY_COMMON)
	public static YLCommonProxy proxy;
	
	private void loadModMetadata(ModMetadata modMetadata) {
		modMetadata.modId = YLConstants.MOD_ID;
		modMetadata.name = YLConstants.MOD_NAME;
		modMetadata.version = YLConstants.MOD_VERSION;
		modMetadata.authorList.add("yuma140902");
		modMetadata.description = "Library for UpToDateMod";
		//modMetadata.url = "https://www.curseforge.com/minecraft/mc-mods/yumalib";
		modMetadata.autogenerated = false;
		modMetadata.logoFile = "assets/yumalib/textures/logo.png";
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		loadModMetadata(modMetadata);
		proxy.checkIfIsYuma140902();
		YLConfigCore.loadConfig(event);
		
		YLItems.registerAll();
		
		proxy.registerEventHandlers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for(IUpdateChecker updateChecker : UpdateCheckerRegistry.INSTANCE.list()) {
			try {
				updateChecker.checkForUpdates();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
