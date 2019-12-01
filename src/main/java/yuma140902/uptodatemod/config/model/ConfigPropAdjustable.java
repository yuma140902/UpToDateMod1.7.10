package yuma140902.uptodatemod.config.model;

import javax.annotation.Nonnull;
import yuma140902.uptodatemod.config.data.AutoAdjustableData;

public class ConfigPropAdjustable<T> extends ConfigProp<T> {

	ConfigPropAdjustable(@Nonnull IConfigCategory category, @Nonnull String name) {
		super(category, name);
	}
	
	
	
	@Override
	public String tooltipComment() {
		String comment = super.comment();
		String adjustmentComments = this.adjustableData.adjustmentComments();
		
		if(adjustmentComments.isEmpty()) {
			return comment;
		}
		
		StringBuilder sb = new StringBuilder(comment);
		sb.append('\n').append(adjustmentComments);
		return sb.toString();
	}
	
	
	@Override
	public void set(T value) {
		super.set(value);
		this.adjustableData.set(value);
	}
	
	@Override
	public T get() {
		return this.adjustableData.get();
	}
	
	
	
	
	@Nonnull
	private final AutoAdjustableData<T> adjustableData = new AutoAdjustableData<T>();
	
	public AutoAdjustableData<T> adjastable(){
		return this.adjustableData;
	}
	
}
