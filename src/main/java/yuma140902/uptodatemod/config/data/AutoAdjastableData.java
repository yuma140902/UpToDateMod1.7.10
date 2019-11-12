package yuma140902.uptodatemod.config.data;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class AutoAdjastableData<T> implements IConfigData<T> {
	
	private final List<Adjastment> adjastments = new LinkedList<>();
	
	@Getter @Setter
	private boolean auto = true;
	
	private T value;

	@Override
	public T get() {
		return value;
	}

	@Override
	public void set(T value) {
		this.value = value;
	}
	
	
	public void tryAdjast(T value, @Nonnull Adjastment adjastment) {
		if(auto) {
			forceAdjast(value, adjastment);
		}
	}
	
	public void forceAdjast(T value, @Nonnull Adjastment adjastment) {
		auto = false;
		this.value = value;
		adjastments.add(adjastment);
	}
	
	
	@RequiredArgsConstructor
	public static class Adjastment {
		@Getter
		@Nonnull
		private String adjasterName;
		@Getter
		@Nullable
		private String message;
	}
	
}
