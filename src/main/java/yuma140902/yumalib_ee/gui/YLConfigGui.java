package yuma140902.yumalib_ee.gui;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.yumalib_ee.YTConstants;
import yuma140902.yumalib_ee.config.YLConfigCore;

public class YLConfigGui extends GuiConfig {
	
	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new ConfigElement<Object>(YLConfigCore.cfg.getCategory(YLConfigCore.Tooltip.CAT_NAME)));
		
		return list;
	}
	
	public YLConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), YTConstants.MOD_ID, false, false, YTConstants.MOD_NAME);
	}
}
