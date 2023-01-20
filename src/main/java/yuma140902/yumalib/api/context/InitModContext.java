package yuma140902.yumalib.api.context;

import yuma140902.yumalib.api.util.NameProvider;

/**
 * Mod初期化時の文脈(YumaLibのAPIを呼び出しているのはどのModかなどの情報)を表す。<br>
 * これにより、いちいちメソッドにMod名などを渡す手間が省ける。
 * @author yuma1
 */
public class InitModContext {
	private final String addonModName;
	private final NameProvider nameProvider;

	/**
	 * @param addonModName YumaLibのAPIを呼び出しているModのID。YumaLibに依存するModのことをAddonModと呼んでいる
	 */
	public InitModContext(String addonModName, NameProvider nameProvider) {
		this.addonModName = addonModName;
		this.nameProvider = nameProvider;
	}
	
	/**
	 * @return このコンテキストを使っているModの名前
	 */
	public String addonModName() {return addonModName;}
	public NameProvider nameProvider() {return nameProvider;}
}
