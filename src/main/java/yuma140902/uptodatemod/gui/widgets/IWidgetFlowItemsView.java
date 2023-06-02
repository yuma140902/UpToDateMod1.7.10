package yuma140902.uptodatemod.gui.widgets;

import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.gui.event.IEventHandler;
import yuma140902.uptodatemod.gui.widgets.WidgetFlowItemsView.GuiFlowContainer;
import yuma140902.uptodatemod.gui.widgets.WidgetFlowItemsView.SelectionChangeEvent;


public interface IWidgetFlowItemsView {
	
	IWidgetFlowItemsView setNumRow(int numRow);
	IWidgetFlowItemsView setNumColumn(int numColumn);
	IWidgetFlowItemsView setScrollBar(GuiScrollBar scrollBar, int margin);
	
	void drawWidget(Minecraft mc, int mouseX, int mouseY);
	
	void subscribe(IEventHandler<SelectionChangeEvent> handler);
	
	void unregister(IEventHandler<SelectionChangeEvent> handler);
	
	/**
	 * @param id
	 * @param x GuiScreenのX座標 (WidgetはGuiScreenの左上が原点だが、Guiはウィンドウの左上が原点なので、GuiScreenの左上の座標が必要)
	 * @param y GuiScreenのX座標 (WidgetはGuiScreenの左上が原点だが、Guiはウィンドウの左上が原点なので、GuiScreenの左上の座標が必要)
	 */
	GuiFlowContainer asGui(int id, int x, int y);
	
	int getPosX();
	int getPosY();
	int getWidth();
	int getHeight();
	
	void addItem(@Nonnull ItemStack item);
	
	void removeAllItem();
	
}
