package yuma140902.uptodatemod.gui.widgets;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.gui.util.GuiUtils;

public class WidgetItem implements IWidget {
	private int posX, posY;
	
	@Nullable
	public ItemStack itemToShow = new ItemStack(Blocks.bookshelf, 64);
	
	public WidgetItem(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	public void drawWidget(Minecraft mc, int mouseX, int mouseY) {
		GuiUtils.drawItem(posX, posY, itemToShow, mc);
	}

	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}
	
	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 16;
	}
	
}
