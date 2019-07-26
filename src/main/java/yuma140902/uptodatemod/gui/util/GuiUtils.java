package yuma140902.uptodatemod.gui.util;


public class GuiUtils extends cpw.mods.fml.client.config.GuiUtils {
	public static void drawContinuousTexturedBox(IResourceLocationPart res, int x, int y, int width, int height, float zLevel) {
		drawContinuousTexturedBox(res.getResourceLocation(), x, y, res.getU(), res.getV(), width, height, res.getWidth(), res.getHeight(), 0, zLevel);
	}
}
