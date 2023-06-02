package yuma140902.uptodatemod.gui.util;

import javax.annotation.Nonnull;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class GuiUtils extends cpw.mods.fml.client.config.GuiUtils {
	public static final RenderItem itemRender = new RenderItem();
	
	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	public static void drawContinuousTexturedBox(IResourceLocationPart res, int x, int y, int width, int height, float zLevel) {
		drawContinuousTexturedBox(res.getResourceLocation(), x, y, res.getU(), res.getV(), width, height, res.getWidth(), res.getHeight(), 0, zLevel);
	}
	
	public static void drawTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height, double zLevel)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double)(x + 0), 			(double)(y + height), 	zLevel, (double)icon.getMinU(), (double)icon.getMaxV());
		tessellator.addVertexWithUV((double)(x + width), 	(double)(y + height), 	zLevel, (double)icon.getMaxU(), (double)icon.getMaxV());
		tessellator.addVertexWithUV((double)(x + width), 	(double)(y + 0), 				zLevel, (double)icon.getMaxU(), (double)icon.getMinV());
		tessellator.addVertexWithUV((double)(x + 0), 			(double)(y + 0), 				zLevel, (double)icon.getMinU(), (double)icon.getMinV());
		tessellator.draw();
	}
	
	public static void startDrawingItem() {
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
    GL11.glDisable(GL11.GL_DEPTH_TEST);
		
    GL11.glPushMatrix();
    RenderHelper.enableGUIStandardItemLighting();
    
    short short1 = 240;
    short short2 = 240;
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)short1 / 1.0F, (float)short2 / 1.0F);
		
		itemRender.zLevel = 100.0f;
	}
	
	public static void stopDrawingItem() {
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
    GL11.glEnable(GL11.GL_DEPTH_TEST);
    RenderHelper.enableGUIStandardItemLighting();
		
		itemRender.zLevel = 0.0F;
	}
	
	public static void drawSingleItem(int x, int y, @Nonnull ItemStack itemToShow, Minecraft mc) {
		FontRenderer fontRendererObj = mc.fontRenderer;
		//Gui.drawRect(x, y, x + 16, y + 16, 4210752);
 		//GL11.glEnable(GL11.GL_DEPTH_TEST);
 		//itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), itemToShow, x, y);
 		itemRender.renderItemIntoGUI(fontRendererObj, mc.getTextureManager(), itemToShow, x, y, true);
 		itemRender.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), itemToShow, x, y);
	}
	
	// 参考: GuiContainer#drawScreen(int, int, float) と GuiContainer#func_146977_a(Slot)
	public static void drawItem(int x, int y, ItemStack itemToShow, Minecraft mc) {
 		if (itemToShow == null)
 		{
 			return;
 		}
 		startDrawingItem();
 		drawSingleItem(x, y, itemToShow, mc);
 		stopDrawingItem();
 	}
}
