package yuma140902.uptodatemod.gui;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.ModConfigCore;

public class ModConfigGui extends GuiConfig {
	
	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		//return (new ConfigElement<Object>(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_GENERAL))).getChildElements();
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		ConfigCategory generalCategory = ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_GENERAL);
		list.add(new ConfigElement(generalCategory.get("doUpdateChecking")));
		list.add(new ConfigElement(generalCategory.get("updateChannel")));
		list.add(new ConfigElement(generalCategory.get("enableDebugMode")));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_WORLDGEN)));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_RECIPE)));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_ENTITY)));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_DISABLE_FEATURES)));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_ALTERNATIVE)));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_EXPERIMENTAL)));
		list.add(new ConfigElement(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_INTEGRATION)));
		return list;
	}
	
	public ModConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), ModUpToDateMod.MOD_ID, false, false, ModUpToDateMod.MOD_NAME);
	}
}
