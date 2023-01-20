package yuma140902.yumalib.api.config;

/**
 * ほぼString。langファイルのキーを表すドメイン固有型
 */
public class LangKey {
	private final String value;
	
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
