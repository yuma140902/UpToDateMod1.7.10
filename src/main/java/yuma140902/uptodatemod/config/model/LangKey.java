package yuma140902.uptodatemod.config.model;

public class LangKey {
	private String value;
	
	private LangKey(String value) {
		this.value = value;
	}
	
	public static LangKey of(String value) {
		return new LangKey(value);
	}
	
	@Override
	public String toString() {
		return value;
	}
}
