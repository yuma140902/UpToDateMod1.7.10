package yuma140902.uptodatemod.config.data;

public interface IConfigData<T> {
	T get();
	void set(T value);
}
