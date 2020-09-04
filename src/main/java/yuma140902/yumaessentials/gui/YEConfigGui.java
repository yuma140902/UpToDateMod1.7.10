package yuma140902.yumaessentials.gui;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import yuma140902.yumaessentials.YEConstants;

import java.util.ArrayList;
import java.util.List;

public class YEConfigGui extends GuiConfig {
	
	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<>();
		return list;
	}
	
	public YEConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), YEConstants.MOD_ID, false, false, YEConstants.MOD_NAME);
	}
}
