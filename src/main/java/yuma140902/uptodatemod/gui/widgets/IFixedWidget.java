package yuma140902.uptodatemod.gui.widgets;

import net.minecraft.client.Minecraft;

public interface IFixedWidget extends IWidget {
	void drawWidget(Minecraft mc, int mouseX, int mouseY);
	public default void drawWidget(int posX, int posY, Minecraft mc, int mouseX, int mouseY) {
		drawWidget(mc, mouseX, mouseY);
	}
	int getPosX();
	int getPosY();
}
