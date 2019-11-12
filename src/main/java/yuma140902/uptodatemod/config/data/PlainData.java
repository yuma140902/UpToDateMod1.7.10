package yuma140902.uptodatemod.config.data;

public class PlainData<T> implements IConfigData<T> {

	T value;
	
	@Override
	public T get() {
		return value;
	}

	@Override
	public void set(T value) {
		this.value = value;
	}
	
}
