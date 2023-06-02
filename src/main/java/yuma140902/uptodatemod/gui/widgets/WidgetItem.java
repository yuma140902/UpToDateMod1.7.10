package yuma140902.uptodatemod.gui.widgets;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.gui.util.GuiUtils;

public class WidgetItem implements IWidget {
	
	private boolean hover = false;
	
	public WidgetItem() {}
	
	public WidgetItem(ItemStack itemToShow) {
		this.itemToShow = itemToShow;
	}
	
	@Nullable
	public ItemStack itemToShow = new ItemStack(Blocks.bookshelf, 64);
	
	@Override
	public void drawWidget(int posX, int posY, Minecraft mc, int mouseX, int mouseY) {
		GuiUtils.drawItem(posX, posY, itemToShow, mc);
	}
	
	public void hover(boolean hover) {
		this.hover = hover;
	}
	
	public boolean hover() {
		return this.hover;
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
