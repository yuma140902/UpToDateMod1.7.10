package yuma140902.uptodatemod.api;

import cpw.mods.fml.common.FMLLog;
import yuma140902.uptodatemod.api.register.IStonecutterRecipeRegistry;

public class UpToDateModAPI {
	private static IStonecutterRecipeRegistry stonecutterRecipeRegistry;
	
	public static IStonecutterRecipeRegistry getStonecutterRecipeRegistry() {
		if(stonecutterRecipeRegistry == null) {
			try {
				Class cls = Class.forName("yuma140902.uptodatemod.registry.StonecutterRecipeRegistry");
				stonecutterRecipeRegistry = (IStonecutterRecipeRegistry)cls.getField("INSTANCE").get((Object)null);
			} catch (ReflectiveOperationException e) {
				FMLLog.warning("[UpToDateModAPI] Error retrieving StonecutterRecipeRegister, UpToDateMod may be absent, broken, or outdated.", new Object[0]);
			}
		}
		
		return stonecutterRecipeRegistry;
  }
}
