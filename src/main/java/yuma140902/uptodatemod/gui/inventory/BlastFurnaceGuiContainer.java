package yuma140902.uptodatemod.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.tileentity.TileEntityBlastFurnace;

public class BlastFurnaceGuiContainer extends GuiContainer {
	
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "textures/gui/container/blast_furnace.png");
	
	private TileEntityBlastFurnace tileentity;
	
	public BlastFurnaceGuiContainer(EntityPlayer player, TileEntityBlastFurnace tilentity) {
		super(new BlastFurnaceContainer(player, tilentity));
		this.tileentity = tilentity;
		this.xSize = 176;
		this.ySize = 195;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(StatCollector.translateToLocal(tileentity.getInventoryName()), 8, 5, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, 72, 4210752);
		
		fontRendererObj.drawString("procTick: " + tileentity.procTick, 0, 0, 4210752);
		fontRendererObj.drawString("fuelTick: " + tileentity.fuelTick, 0, 20, 4210752);
		fontRendererObj.drawString("status: " + tileentity.getStatus(), 0, 40, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float tick, int mouseX, int mouseY) {
		this.mc.renderEngine.bindTexture(GUI_TEXTURE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
