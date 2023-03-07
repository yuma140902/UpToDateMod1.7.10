package yuma140902.yumalib.api.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 言語コードとテキストの組
 */
public class MultiLingualString {
	public Map<String, String> map = new HashMap<String, String>();
	
	public void add(String locale, String message) {
		this.map.put(locale, message);
	}
	
	public String get(String locale) {
		return this.map.get(locale);
	}
	
	@Override
	public String toString() {
		return String.join(" | ", this.map.values());
	}

	public static MultiLingualString en_ja(String enMessage, String jaMessage) {
		MultiLingualString mls = new MultiLingualString();
		mls.add("en_US", enMessage);
		mls.add("ja_JP", jaMessage);
		return mls;
	}
	
	public static MultiLingualString single(String message) {
		MultiLingualString mls = new MultiLingualString();
		mls.add("en_US", message);
		return mls;
	}
}
