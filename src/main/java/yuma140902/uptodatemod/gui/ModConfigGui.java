package yuma140902.uptodatemod.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.ModConfigCore;

public class ModConfigGui extends GuiConfig {
	public ModConfigGui(GuiScreen parent) {
		super(parent, (new ConfigElement<Object>(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_GENERAL))).getChildElements(), ModUpToDateMod.MOD_ID, false, false, ModUpToDateMod.MOD_NAME);
	}
}
