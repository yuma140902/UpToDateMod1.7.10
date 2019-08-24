package yuma140902.uptodatemod.gui.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WidgetContainerBase implements IWidgetContainer {

	private final List<IWidget> list = new ArrayList<>();
	
	@Override
	public Iterator<IWidget> allWidget() {
		return list.iterator();
	}

	@Override
	public void addWidget(IWidget widget) {
		list.add(widget);
	}

	@Override
	public void removeWidget(IWidget widget) {
		list.remove(widget);
	}

	@Override
	public void removeAllWidget() {
		list.clear();
	}
	
}
