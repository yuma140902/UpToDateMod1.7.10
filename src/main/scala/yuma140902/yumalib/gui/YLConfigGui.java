package yuma140902.yumalib.gui;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.yumalib.YLConstants;
import yuma140902.yumalib.config.YLConfigCore;

public class YLConfigGui extends GuiConfig {
	
	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<>();
		list.add(new ConfigElement<>(YLConfigCore.cfg.getCategory("General")));
		list.add(new ConfigElement<>(YLConfigCore.cfg.getCategory(YLConfigCore.Tooltip.CAT_NAME)));
		
		return list;
	}
	
	public YLConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), YLConstants.MOD_ID, false, false, YLConstants.MOD_NAME);
	}
}
