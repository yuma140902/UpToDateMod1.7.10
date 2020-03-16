package yuma140902.yumalib_ee.gui;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.yumalib_ee.YumaLibConstants;
import yuma140902.yumalib_ee.config.YumaLibConfigCore;

public class YumaLibConfigGui extends GuiConfig {
	
	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new ConfigElement<Object>(YumaLibConfigCore.cfg.getCategory(YumaLibConfigCore.Tooltip.CAT_NAME)));
		
		return list;
	}
	
	public YumaLibConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), YumaLibConstants.MOD_ID, false, false, YumaLibConstants.MOD_NAME);
	}
}
