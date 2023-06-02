package yuma140902.uptodatemod.gui.widgets;

import net.minecraft.client.Minecraft;

public interface IWidget {
	void drawWidget(int posX, int posY, Minecraft mc, int mouseX, int mouseY);
	int getWidth();
	int getHeight();
}
