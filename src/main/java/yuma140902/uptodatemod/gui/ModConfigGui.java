package yuma140902.uptodatemod.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.model.category.CategoryGeneral;

public class ModConfigGui extends GuiConfig {
	public ModConfigGui(GuiScreen parent) {
		super(parent, 
				(new ConfigElement<Object>(ModUpToDateMod.config.forgeConfiguration().getCategory(CategoryGeneral.name))).getChildElements(), 
				ModUpToDateMod.MOD_ID, 
				false, 
				false, 
				ModUpToDateMod.MOD_NAME);
	}
}
