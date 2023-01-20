package yuma140902.yumalib.api.config;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * コンフィグのカテゴリのビルダー。
 * <p>
 *     ビルダーといいつつ最終的にインスタンスを作るのではなくForgeに情報を登録する。
 * </p>
 */
public class CategoryBuilder {
	
	private final List<PropertyBuilder> properties = new ArrayList<PropertyBuilder>();
	private final List<String> order = new ArrayList<String>();
	private final String name;
	private boolean requireMcRestart = false;
	private boolean requireWorldRestart = false;
	private LangKey langKey;
	private MultiLingualString comment;

	/**
	 * 新しいビルダーを開始する
	 * @param name カテゴリ名
	 */
	public CategoryBuilder(String name) {
		this.name = name;
	}
	
	String name() {
		return this.name;
	}
	
	List<PropertyBuilder> properties() {
		return this.properties;
	}

	/**
	 * プロパティを追加する。正確には、プロパティのビルダーを追加する
	 * @return this
	 */
	public CategoryBuilder add(PropertyBuilder property) {
		property.category(name());
		this.properties.add(property);
		this.order.add(property.name());
		return this;
	}

	/**
	 * このカテゴリ内の項目を変更した後、設定を反映させるためにゲームの再起動が必要であると指定する
	 * @return this
	 */
	public CategoryBuilder requireMcRestart() {
		this.requireMcRestart = true;
		return this;
	}

	/**
	 * このカテゴリ内の項目を変更した後、設定を反映させるためにワールドの再読み込みが必要であると指定する
	 * @return this
	 */
	public CategoryBuilder requireWorldRestart() {
		this.requireWorldRestart = true;
		return this;
	}

	/**
	 * GUIで表示する文字列のlangファイルのキーを指定する
	 * @return this
	 */
	public CategoryBuilder langKey(LangKey langKey) {
		this.langKey = langKey;
		return this;
	}

	/**
	 * ファイルに出力される説明コメントを指定する
	 * @return this
	 */
	public CategoryBuilder comment(MultiLingualString comment) {
		this.comment = comment;
		return this;
	}

	/**
	 * ファイルに出力される説明コメントを指定する
	 * @return this
	 */
	public CategoryBuilder comment(String enMessage, String jaMessage) {
		this.comment = MultiLingualString.en_ja(enMessage, jaMessage);
		return this;
	}

	/**
	 * ファイルに出力される説明コメントを指定する
	 * @return this
	 */
	public CategoryBuilder comment(String comment) {
		this.comment = MultiLingualString.single(comment);
		return this;
	}
	
	// initConfig()から呼び出す
	/**
	 * カテゴリにForgeに登録する。FMLPreInitializationEvent等のハンドラから呼び出されるようにすると良い
	 */
	public void registerToForge(Configuration cfg) {
		if(comment != null) cfg.setCategoryComment(name, comment.toString());
		cfg.setCategoryRequiresMcRestart(name, requireMcRestart);
		cfg.setCategoryRequiresWorldRestart(name, requireWorldRestart);
		if(langKey != null) cfg.setCategoryLanguageKey(name, langKey.toString());
	}
	
	// syncConfig()から呼び出す

	/**
	 * 項目をForgeに登録する。FMLPreInitializationEventのハンドラとConfigChangedEvent.OnConfigChangedEventのハンドラから呼び出されるようにすると良い。
	 */
	public void registerPropertiesToForge(Configuration cfg) {
		for(PropertyBuilder property : properties) {
			property.registerToForge(cfg);
		}
		cfg.setCategoryPropertyOrder(name, order);
	}

	// TODO: やっぱりCategoryクラスとPropertyクラスを定義する必要がある
	public Property get(String propertyName, Configuration cfg) {
		return cfg.getCategory(name).get(propertyName);
	}
}
