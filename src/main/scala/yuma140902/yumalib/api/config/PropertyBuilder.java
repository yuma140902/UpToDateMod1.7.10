package yuma140902.yumalib.api.config;

import java.util.Arrays;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * コンフィグの項目のビルダー。
 * <p>
 *     ビルダーといいつつ最終的にインスタンスを作るのではなくForgeに情報を登録する。
 * </p>
 */
public class PropertyBuilder {
	
	private final String name;
	private String category;
	
	private Type valueType;
	private boolean defaultBool;
	private String defaultString;
	private int defaultInt;
	private String[] defaultStringList;
	private int[] defaultIntList;
	
	private String[] validStrings;
	private Pattern validationPattern;
	
	private boolean requireMcRestart = false;
	private boolean requireWorldRestart = false;
	
	private LangKey langKey;
	private MultiLingualString comment;
	
	private PropertyBuilder(String name) {
		this.name = name;
	}
	
	// ================= アクセサ ここから =================
	
	String name() {
		return this.name;
	}
	
	// ================= アクセサ ここまで =================
	
	// ================= コンストラクタ ここから =================

	/**
	 * bool用のビルダーを新しく開始する
	 * @param name 項目名
	 */
	public static PropertyBuilder bool(String name) {
		PropertyBuilder property = new PropertyBuilder(name);
		property.valueType = Type.BOOL;
		return property;
	}

	/**
	 * string用のビルダーを新しく開始する
	 * @param name 項目名
	 */
	public static PropertyBuilder string(String name) {
		PropertyBuilder property = new PropertyBuilder(name);
		property.valueType = Type.STRING;
		return property;
	}

	/**
	 * int用のビルダーを新しく開始する
	 * @param name 項目名
	 */
	public static PropertyBuilder integer(String name) {
		PropertyBuilder property = new PropertyBuilder(name);
		property.valueType = Type.INT;
		return property;
	}

	/**
	 * 文字列のリスト用のビルダーを新しく開始する
	 * @param name 項目名
	 */
	public static PropertyBuilder stringList(String name) {
		PropertyBuilder property = new PropertyBuilder(name);
		property.valueType = Type.STRING_LIST;
		return property;
	}

	/**
	 * intのリスト用のビルダーを新しく開始する
	 * @param name 項目名
	 */
	public static PropertyBuilder integerList(String name) {
		PropertyBuilder property = new PropertyBuilder(name);
		property.valueType = Type.INT_LIST;
		return property;
	}
	
	// ================= コンストラクタ ここまで =================
	
	// ================= 組み立て用メソッド ここから =================

	/**
	 * カテゴリ名を指定する
	 * @return this
	 */
	protected PropertyBuilder category(String category) {
		this.category = category;
		return this;
	}

	/**
	 * boolとしてのデフォルト値を指定する
	 * @return this
	 */
	public PropertyBuilder defaultBool(boolean value) {
		assert this.valueType == Type.BOOL;
		this.defaultBool = value;
		return this;
	}

	/**
	 * stringとしてのデフォルト値を指定する
	 * @return this
	 */
	public PropertyBuilder defaultString(String value) {
		assert this.valueType == Type.STRING;
		this.defaultString = value;
		return this;
	}

	/**
	 * intとしてのデフォルト値を指定する
	 * @return this
	 */
	public PropertyBuilder defaultInt(int value) {
		assert this.valueType == Type.INT;
		this.defaultInt = value;
		return this;
	}

	/**
	 * 文字列のリストとしてのデフォルト値を指定する
	 * @return this
	 */
	public PropertyBuilder defaultStringList(String[] values) {
		assert this.valueType == Type.STRING_LIST;
		this.defaultStringList = values;
		return this;
	}

	/**
	 * intのリストとしてのデフォルト値を指定する
	 * @return this
	 */
	public PropertyBuilder defaultIntList(int[] values) {
		assert this.valueType == Type.INT_LIST;
		this.defaultIntList = values;
		return this;
	}

	/**
	 * 指定可能な文字列のリストを指定する
	 * @return this
	 */
	public PropertyBuilder validStrings(String[] strings) {
		assert this.valueType == Type.STRING;
		this.validStrings = strings;
		return this;
	}

	/**
	 * 指定可能な文字列のパターンを指定する
	 * @return this
	 */
	public PropertyBuilder validationPattern(Pattern pattern) {
		this.validationPattern = pattern;
		return this;
	}

	/**
	 * この項目を変更した後、設定を反映させるためにゲームの再起動が必要であると指定する
	 * @return this
	 */
	public PropertyBuilder requireMcRestart() {
		this.requireMcRestart = true;
		return this;
	}

	/**
	 * この項目を変更した後、設定を反映させるためにワールドの再読み込みが必要であると指定する
	 * @return this
	 */
	public PropertyBuilder requireWorldRestart() {
		this.requireWorldRestart = true;
		return this;
	}

	/**
	 * GUIで表示する文字列のlangファイルのキーを指定する
	 * @return this
	 */
	public PropertyBuilder langKey(LangKey langKey) {
		this.langKey = langKey;
		return this;
	}

	/**
	 * ファイルに出力される説明コメントを指定する
	 * @return this
	 */
	public PropertyBuilder comment(MultiLingualString comment) {
		this.comment = comment;
		return this;
	}

	/**
	 * ファイルに出力される説明コメントを指定する
	 * @return this
	 */
	public PropertyBuilder comment(String enMessage, String jaMessage) {
		this.comment = MultiLingualString.en_ja(enMessage, jaMessage);
		return this;
	}

	/**
	 * ファイルに出力される説明コメントを指定する
	 * @return this
	 */
	public PropertyBuilder comment(String comment) {
		this.comment = MultiLingualString.single(comment);
		return this;
	}
	
	// ================= 組み立て用メソッド ここまで =================

	/**
	 * Forgeに登録する
	 */
	public void registerToForge(Configuration cfg) {
		Property prop = null;
		switch(this.valueType) {
			case BOOL:
				prop = cfg.get(category, name, defaultBool);
				prop.comment = toString(comment) + " [default: " + defaultBool + "]";
				break;
			case STRING:
				prop = cfg.get(category, name, defaultString);
				prop.comment = toString(comment) + " [default: " + defaultString + "]";
				break;
			case INT:
				prop = cfg.get(category, name, defaultInt);
				prop.comment = toString(comment) + " [default: " + defaultInt + "]";
				break;
			case STRING_LIST:
				prop = cfg.get(category, name, defaultStringList);
				prop.comment = toString(comment) + " [default: " + String.join(", ", defaultStringList) + "]";
				break;
			case INT_LIST:
				prop = cfg.get(category, name, defaultIntList);
				prop.comment = toString(comment) + " [default: " + Arrays.toString(defaultIntList) + "]";
				break;
		}
		
		if(prop == null) {
			System.out.println("Skipped '" + name + "' because of unknown property type.");
			return;
		}
		else {
			prop.setRequiresMcRestart(requireMcRestart);
			prop.setRequiresWorldRestart(requireWorldRestart);
			if(langKey != null) prop.setLanguageKey(langKey.toString());
			if(validStrings != null) prop.setValidValues(validStrings);
			if(validationPattern != null) prop.setValidationPattern(validationPattern);
		}
	}
	
	private static enum Type {
		BOOL, STRING, INT, STRING_LIST, INT_LIST
	}
	
	private static String toString(@Nullable MultiLingualString mls) {
		if(mls == null) return "";
		return mls.toString();
	}
}
