package yuma140902.uptodatemod.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.tileentity.TileEntityStonecutter;

public class StonecutterGuiContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "textures/gui/stonecutter.png");
	private TileEntityStonecutter tileentity;
	
	public StonecutterGuiContainer(EntityPlayer player, TileEntityStonecutter tileentity) {
		super(new StonecutterContainer(player, tileentity));
		this.tileentity = tileentity;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(StatCollector.translateToLocal(tileentity.getInventoryName()), 8, 4, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94 + 1, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(TEXTURE);
		int tmpX = (this.width - this.xSize) / 2;
    int tmpY = (this.height - this.ySize) / 2;
    this.drawTexturedModalRect(tmpX, tmpY, 0, 0, this.xSize, this.ySize);
	}
	
	/*GUIが開いている時にゲームの処理を止めるかどうか。*/
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
