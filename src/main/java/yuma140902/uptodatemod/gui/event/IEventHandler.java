package yuma140902.uptodatemod.gui.event;

@FunctionalInterface
public interface IEventHandler<T extends IEvent> {
	void onFire(T event);
}
