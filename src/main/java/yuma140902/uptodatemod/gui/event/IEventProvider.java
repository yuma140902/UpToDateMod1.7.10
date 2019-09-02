package yuma140902.uptodatemod.gui.event;

public interface IEventProvider<T extends IEvent> {
	void subscribe(IEventHandler<T> handler);
	void unregister(IEventHandler<T> handler);
	
	void fireEvent(T event);
}
