package yuma140902.uptodatemod.gui.widgets;

import java.util.Iterator;

public interface IWidgetContainer {
	Iterator<IWidget> allWidget();
	void addWidget(IWidget widget);
	void removeWidget(IWidget widget);
	void removeAllWidget();
}
