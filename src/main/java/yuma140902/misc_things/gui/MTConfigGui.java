package yuma140902.misc_things.gui;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.misc_things.MTConstants;
import yuma140902.misc_things.config.MTConfigCore;

public class MTConfigGui extends GuiConfig {
	
	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new ConfigElement<Object>(MTConfigCore.cfg.getCategory("General")));
		
		return list;
	}
	
	public MTConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), MTConstants.MOD_ID, false, false, MTConstants.MOD_NAME);
	}
}
