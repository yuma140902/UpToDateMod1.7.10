package yuma140902.uptodatemod.gui.widgets;

import net.minecraft.client.Minecraft;

public interface IWidget {
	void drawWidget(Minecraft mc, int mouseX, int mouseY);
	int getPosX();
	int getPosY();
	int getWidth();
	int getHeight();
}
