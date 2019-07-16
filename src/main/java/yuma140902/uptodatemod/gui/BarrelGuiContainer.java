package yuma140902.uptodatemod.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class BarrelGuiContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "textures/gui/barrel.png");
	private TileEntityBarrel tileEntity;

	public BarrelGuiContainer(EntityPlayer player, TileEntityBarrel tileEntity) {
		super(new BarrelContainer(player, tileEntity));
		this.tileEntity = tileEntity;
		this.ySize = 168;
	}
	
	/*GUIの文字等の描画処理*/
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 1, 4210752);
	}

	/*GUIの背景の描画処理*/
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
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
