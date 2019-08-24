package yuma140902.uptodatemod.gui.widgets;

import java.util.Iterator;
import net.minecraft.client.Minecraft;

public class WidgetScreenContainer implements IWidgetContainer, IWidget {

	private int width, height;
	private final WidgetContainerBase container = new WidgetContainerBase();
	
	public WidgetScreenContainer(int screenWidth, int screenHeight) {
		this.width = screenWidth;
		this.height = screenHeight;
	}
	
	@Override
	public void drawWidget(Minecraft mc, int mouseX, int mouseY) {
		Iterator<IWidget> widgets = allWidget();
		while (widgets.hasNext()) {
			IWidget widget = widgets.next();
			widget.drawWidget(mc, mouseX, mouseY);
		}
	}

	@Override
	public int getPosX() {
		return 0;
	}

	@Override
	public int getPosY() {
		return 0;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Iterator<IWidget> allWidget() {
		return container.allWidget();
	}

	@Override
	public void addWidget(IWidget widget) {
		container.addWidget(widget);
	}

	@Override
	public void removeWidget(IWidget widget) {
		container.removeWidget(widget);
	}

	@Override
	public void removeAllWidget() {
		container.removeAllWidget();
	}
	
}
